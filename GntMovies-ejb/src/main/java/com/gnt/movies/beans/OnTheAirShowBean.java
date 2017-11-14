package com.gnt.movies.beans;

import java.util.ArrayList;
import java.util.HashSet;

import javax.ejb.EJB;
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
	
	static HashSet<Integer> allIdTmdb;
	
	@Inject
	@JpaDao
	@Named("OnTheAirShowDaoImpl")
	OnTheAirShowDao onTheAirShowDao;
	
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
    
    public OnTheAirShow createOnTheAirShowFromAPI(int idTmdb) {
    	return new OnTheAirShow(idTmdb);
    }
    
    public ArrayList<OnTheAirShow> getAllOnTheAirShows(){
    	return (ArrayList<OnTheAirShow>) onTheAirShowDao.findAll(this);
    }
    
    public static HashSet<Integer> getAllIdTmdb() {
		return allIdTmdb;
	}

	public void checkOnTheAirShowsToBeDeleted(ArrayList<Integer> newIdTmdb) {

		try {
			for (int i = 0; i < allIdTmdb.size(); i++) {
//				if (!newIdTmdb.contains(allIdTmdb.get(i))) {
//					OnTheAirShow onTheAirShow = onTheAirShowDao.findOnTheAirShowByIdTmdb(this, allIdTmdb.get(i));
//					onTheAirShowDao.deleteOnTheAirShow(this, onTheAirShow);
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
