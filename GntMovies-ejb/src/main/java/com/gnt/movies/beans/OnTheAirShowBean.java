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
public class OnTheAirShowBean implements DataProviderHolder{
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
    
    public ArrayList<OnTheAirShow> getAllOnTheAirShows(){
    	return (ArrayList<OnTheAirShow>) onTheAirShowDao.findAll(this);
    }
    
    public static HashSet<Integer> getAllIdTmdb() {
		return allIdTmdb;
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void checkOnTheAirShow(ApiNewShow newShowAPI) {
    	logger.info("Adding movie with tmdbId=" + newShowAPI.getId());
    	Show show;
    	if(allIdTmdb.contains(newShowAPI.getId()))
    			return;
    	//check if the show is new
    	OnTheAirShow newOnTheAirShow = createOnTheAirShowFromAPI(newShowAPI);
    	//first time getting on the air shows, we also need to add the show
    	if (showBean.findShowByIdTmdb(newShowAPI.getId()) == null) {
    		show = showBean.addNewShow(newShowAPI);
    	} else {
    		show = showBean.findShowByIdTmdb(newShowAPI.getId());
    	}
    	
    	newOnTheAirShow.setShow(show);
    	addOnTheAirShow(newOnTheAirShow);
    	allIdTmdb.add(newOnTheAirShow.getIdTmdb());
    }


	public void removeOldNotOnTheAirShows(ArrayList<ApiNewShow> onTheAirShowsAPI) {

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
    
    public void removeOldNotOnTheAirShow(ArrayList<ApiNewShow> onTheAirShowsAPI) {
    	for (ApiNewShow apiNewShow : onTheAirShowsAPI) {
    		allIdTmdb.remove(apiNewShow.getId());
    	}
    	
    	for (Integer idtmdb : allIdTmdb) {
    		logger.info("removing air today show with tmdbId=" + idtmdb);
    		onTheAirShowDao.deleteOnTheAirShowByIdTmdb(this, idtmdb);
    	} 
    }

	public void findAllIdTmdb() {
		allIdTmdb = (HashSet<Integer>) onTheAirShowDao.getAllIdTmdb(this);
		
	}


}
