package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.OnTheAirShowDao;
import com.gnt.movies.entities.OnTheAirShow;

/**
 * Session Bean implementation class OnTheAirShowBean
 */
@Stateless
@LocalBean
public class OnTheAirShowBean implements DataProviderHolder{

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("OnTheAirShowDaoImpl")
	OnTheAirShowDao onTheAirShowDao;
	
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
    
    public OnTheAirShow createOnTheAirShowFromAPI(int idTmdb) {
    	return new OnTheAirShow(idTmdb);
    }
    
    public ArrayList<OnTheAirShow> getAllOnTheAirShows(){
    	return (ArrayList<OnTheAirShow>) onTheAirShowDao.findAll(this);
    }

}
