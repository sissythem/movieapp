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

import com.gnt.movies.dao.Air2dayShowDao;
import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.entities.Air2dayShow;

/**
 * Session Bean implementation class Air2dayShowBean
 */
@Stateless
@LocalBean
public class Air2dayShowBean implements DataProviderHolder{
	
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
    
    public void addAir2DayShow(Air2dayShow air2dayShow) {
    	air2dayShowDao.createAir2dayShow(this, air2dayShow);
    }
    
    public Air2dayShow findAir2dayShowByIdTmdb(int idTmdb) {
    	return air2dayShowDao.findByIdTmdb(this, idTmdb);
    }
    
    public Air2dayShow createAir2dayShowFromAPI(int idTmdb) {
    	return new Air2dayShow(idTmdb);
    }
    
    public ArrayList<Air2dayShow> getAllAir2dayShows() {
    	return (ArrayList<Air2dayShow>) air2dayShowDao.findAll(this);
    }
    public static HashSet<Integer> getAllIdTmdb() {
		return allIdTmdb;
	}

	public void checkAir2dayShowsToBeDeleted(ArrayList<Integer> newIdTmdb) {

		try {
			for (int i = 0; i < allIdTmdb.size(); i++) {
//				if (!newIdTmdb.contains(allIdTmdb.get(i))){
//					Air2dayShow air2dayShow = air2dayShowDao.findByIdTmdb(this, allIdTmdb.get(i));
//					air2dayShowDao.deleteAir2dayShow(this, air2dayShow);
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
