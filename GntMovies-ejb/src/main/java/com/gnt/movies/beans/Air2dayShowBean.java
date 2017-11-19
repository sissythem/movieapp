package com.gnt.movies.beans;

import java.util.ArrayList;
import java.util.HashSet;
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
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

/**
 * Session Bean implementation class Air2dayShowBean
 */
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
    
    public Air2dayShow createAir2dayShowFromAPI(ApiNewShow newShowAPI) {
    	return new Air2dayShow(newShowAPI.getId());
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
    public void checkAir2dayShow(ApiNewShow newShowAPI) {
		if (allIdTmdb.contains(newShowAPI.getId()))
			return;
		logger.info("Adding show with tmdbId=" + newShowAPI.getId());
		Air2dayShow air2dayShow = createAir2dayShowFromAPI(newShowAPI);
		Show show = showBean.getShow(newShowAPI);
		air2dayShow.setShow(show);
		addAir2day(air2dayShow);
		allIdTmdb.put(air2dayShow.getIdTmdb(), true);
    }
    
    public boolean addAir2day(Air2dayShow air2dayShow) {
    	try {
    		air2dayShowDao.createAir2dayShow(this, air2dayShow);
    		logger.info(" air2dayShow id:" + air2dayShow.getId());
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public void removeOldNotAir2dayShow(HashSet<ApiNewShow> air2dayShowsAPI) {
    	for (ApiNewShow apiNewShow : air2dayShowsAPI) {
			allIdTmdb.remove(apiNewShow.getId());
		}

		allIdTmdb.forEachKey(10, e -> {
			logger.info("removing movie with tmdbId=" + e);
			air2dayShowDao.deleteAir2dayShowByIdTmdb(this, e);
		}); 
    }
}
