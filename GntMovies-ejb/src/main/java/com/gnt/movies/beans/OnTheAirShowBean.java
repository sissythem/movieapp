package com.gnt.movies.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.OnTheAirShowDao;
import com.gnt.movies.entities.OnTheAirShow;
import com.gnt.movies.entities.Show;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
public class OnTheAirShowBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(OnTheAirShowBean.class);

	@PersistenceContext
	private EntityManager em;

	@Inject
	@JpaDao
	@Named("OnTheAirShowDaoImpl")
	OnTheAirShowDao onTheAirShowDao;

	private static ConcurrentHashMap<Integer, Boolean> allIdTmdb;

	@EJB
	ShowBean showBean;

	public OnTheAirShowBean() {
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	public static void init() {
		allIdTmdb = new ConcurrentHashMap<>();
		
	}

	public void addOnTheAirShow(OnTheAirShow onTheAirShow) {
		onTheAirShowDao.createOnTheAirShow(this, onTheAirShow);
	}

	public OnTheAirShow findOnTheAirShowByIdTmdb(int idTmdb) {
		return onTheAirShowDao.findOnTheAirShowByIdTmdb(this, idTmdb);
	}

	public OnTheAirShow createOnTheAirShowFromAPI(Show show) {
		return new OnTheAirShow(show.getIdTmdb());
	}

	public ArrayList<OnTheAirShow> getAllOnTheAirShows() {
		return (ArrayList<OnTheAirShow>) onTheAirShowDao.findAll(this);
	}

	public void findAllIdTmdb() {
		for (Object o : onTheAirShowDao.getAllIdTmdb(this)) {
			allIdTmdb.put((Integer) o, true);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void checkOnTheAirShow(Show show) {
		if (allIdTmdb.containsKey(show.getIdTmdb()))
			return;
		logger.info("Adding show with tmdbId=" + show.getIdTmdb());
		OnTheAirShow onTheAirShow = createOnTheAirShowFromAPI(show);
		show = showBean.getShow(show);
		onTheAirShow.setShow(show);
		addOnTheAirShow(onTheAirShow);
		allIdTmdb.put(onTheAirShow.getIdTmdb(), true);
	}

	public boolean addOnTheAir(OnTheAirShow onTheAirShow) {
		try {
			onTheAirShowDao.createOnTheAirShow(this, onTheAirShow);
			logger.info(" onTheAirShow id:" + onTheAirShow.getIdTmdb());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void removeOldNotOnTheAirShows(HashSet<Show> onTheAirShows) {
		for (Show show : onTheAirShows) {
			allIdTmdb.remove(show.getIdTmdb());
		}

		Set<Integer>allidtmd = allIdTmdb.keySet();
		allidtmd.stream().forEach(e->{
			logger.info("removing movie with tmdbId=" + e);
			onTheAirShowDao.deleteOnTheAirShowByIdTmdb(this, e);
		});
	}
}
