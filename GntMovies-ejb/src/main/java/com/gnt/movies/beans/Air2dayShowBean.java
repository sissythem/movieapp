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

import com.gnt.movies.dao.Air2dayShowDao;
import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.entities.Air2dayShow;
import com.gnt.movies.entities.Show;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
public class Air2dayShowBean implements DataProviderHolder{
	private static final Logger logger = LoggerFactory.getLogger(Air2dayShowBean.class);

	@PersistenceContext
	private EntityManager em;
	
	private static ConcurrentHashMap<Integer, Boolean> allIdTmdb;

	@Inject
	@JpaDao
	@Named("Air2dayShowDaoImpl")
	Air2dayShowDao air2dayShowDao;
	
	@EJB
	ShowBean showBean;
	
    public Air2dayShowBean() {
    }
    
    @Override
	public EntityManager getEntityManager() {
		return em;
	}
    
    public static void init() {
		allIdTmdb = new ConcurrentHashMap<>();
		
	}
    
    public void addAir2dayShow(Air2dayShow air2dayShow) {
    	air2dayShowDao.createAir2dayShow(this, air2dayShow);
    }
    
    public Air2dayShow findAir2dayShowByIdTmdb(int idTmdb) {
    	return air2dayShowDao.findByIdTmdb(this, idTmdb);
    }
    
    public Air2dayShow createAir2dayShowFromAPI(Show show) {
    	return new Air2dayShow(show.getIdTmdb());
    }
    
    public ArrayList<Air2dayShow> getAllAir2dayShows() {
    	return (ArrayList<Air2dayShow>) air2dayShowDao.findAll(this);
    }
    public void findAllIdTmdb() {
		for (Object o : air2dayShowDao.getAllIdTmdb(this)) {
			allIdTmdb.put((Integer) o, true);
		}
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void checkAir2dayShow(Show show) {
		if (allIdTmdb.containsKey(show.getIdTmdb()))
			return;
		logger.info("Adding show with tmdbId=" + show.getIdTmdb());
		Air2dayShow air2dayShow = createAir2dayShowFromAPI(show);
		show = showBean.getShow(show);
		air2dayShow.setShow(show);
		addAir2day(air2dayShow);
		allIdTmdb.put(air2dayShow.getIdTmdb(), true);
    }
    
    public boolean addAir2day(Air2dayShow air2dayShow) {
    	try {
    		air2dayShowDao.createAir2dayShow(this, air2dayShow);
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public void removeOldNotAir2dayShow(HashSet<Show> shows) {
    	for (Show show : shows)
			allIdTmdb.remove(show.getIdTmdb());
    	Set<Integer>allidtmd = allIdTmdb.keySet();
		allidtmd.stream().forEach(e->air2dayShowDao.deleteAir2dayShowByIdTmdb(this, e));
    }
}
