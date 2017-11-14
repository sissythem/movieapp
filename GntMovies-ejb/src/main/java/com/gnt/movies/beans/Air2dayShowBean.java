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

import com.gnt.movies.dao.Air2dayShowDao;
import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.entities.Air2dayShow;
import com.gnt.movies.entities.Show;
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
	
	static HashSet<Integer> allIdTmdb;

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
    public static HashSet<Integer> getAllIdTmdb() {
		return allIdTmdb;
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void checkAir2dayShow(ApiNewShow newShowAPI) {
    	logger.info("Adding movie with tmdbId=" + newShowAPI.getId());
    	Show show;
    	if(allIdTmdb.contains(newShowAPI.getId()))
    			return;
    	//check if the show is new
    	Air2dayShow newAir2dayShow = createAir2dayShowFromAPI(newShowAPI);
    	//first time getting on the air shows, we also need to add the show
    	if (showBean.findShowByIdTmdb(newShowAPI.getId()) == null) {
    		show = showBean.addNewShow(newShowAPI);
    	} else {
    		show = showBean.findShowByIdTmdb(newShowAPI.getId());
    	}
    	
    	newAir2dayShow.setShow(show);
    	addAir2dayShow(newAir2dayShow);
    	allIdTmdb.add(newAir2dayShow.getIdTmdb());
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
    
    public void removeOldNotAir2dayShow(ArrayList<ApiNewShow> air2dayShowsAPI) {
    	for (ApiNewShow apiNewShow : air2dayShowsAPI) {
    		allIdTmdb.remove(apiNewShow.getId());
    	}
    	
    	for (Integer idtmdb : allIdTmdb) {
    		logger.info("removing air today show with tmdbId=" + idtmdb);
    		air2dayShowDao.deleteAir2dayShowByIdTmdb(this, idtmdb);
    	} 
    }

	public void findAllIdTmdb() {
		allIdTmdb = (HashSet<Integer>) air2dayShowDao.getAllIdTmdb(this);
		
	}

}
