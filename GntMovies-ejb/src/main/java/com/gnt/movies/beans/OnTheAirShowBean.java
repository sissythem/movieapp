package com.gnt.movies.beans;

import java.util.ArrayList;
import java.util.HashSet;

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
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

/**
 * Session Bean implementation class OnTheAirShowBean
 */
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

	private static HashSet<Integer> allIdTmdb;

	@EJB
	ShowBean showBean;

	public OnTheAirShowBean() {
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	public void addOnTheAirShow(OnTheAirShow onTheAirShow) {
		onTheAirShowDao.createOnTheAirShow(this, onTheAirShow);
	}

	public OnTheAirShow findOnTheAirShowByIdTmdb(int idTmdb) {
		return onTheAirShowDao.findOnTheAirShowByIdTmdb(this, idTmdb);
	}

	public OnTheAirShow createOnTheAirShowFromAPI(ApiNewShow onTheAirShow) {
		return new OnTheAirShow(onTheAirShow.getId());
	}

	public ArrayList<OnTheAirShow> getAllOnTheAirShows() {
		return (ArrayList<OnTheAirShow>) onTheAirShowDao.findAll(this);
	}

	public static HashSet<Integer> getAllIdTmdb() {
		return allIdTmdb;
	}

	public void findAllIdTmdb() {
		allIdTmdb = (HashSet<Integer>) onTheAirShowDao.getAllIdTmdb(this);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void checkOnTheAirShow(ApiNewShow apiNewShow) {
		if (allIdTmdb.contains(apiNewShow.getId()))
			return;
		logger.info("Adding show with tmdbId=" + apiNewShow.getId());
		OnTheAirShow onTheAirShow = createOnTheAirShowFromAPI(apiNewShow);
		Show show = showBean.getShow(apiNewShow);
		onTheAirShow.setShow(show);
		addOnTheAirShow(onTheAirShow);
		allIdTmdb.add(onTheAirShow.getIdTmdb());
	}

	public boolean addOnTheAir(OnTheAirShow onTheAirShow) {
		try {
			onTheAirShowDao.createOnTheAirShow(this, onTheAirShow);
			logger.info(" onTheAirShow id:" + onTheAirShow.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void removeOldNotOnTheAirShows(ArrayList<ApiNewShow> onTheAirShowsAPI) {
		for (ApiNewShow apiNewShow : onTheAirShowsAPI) {
			allIdTmdb.remove(apiNewShow.getId());
		}

		for (Integer idtmdb : allIdTmdb) {
			logger.info("removing On the air show with tmdbId=" + idtmdb);
			onTheAirShowDao.deleteOnTheAirShowByIdTmdb(this, idtmdb);
		}
	}
}
