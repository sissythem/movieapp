package com.gnt.movies.beans;

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

	@Inject
	@JpaDao
	@Named("Air2dayShowDaoImpl")
	Air2dayShowDao air2dayShowDao;
	
    public Air2dayShowBean() {
    }
    
    @Override
	public EntityManager getEntityManager() {
		return em;
	}
    
    public void addShow(Air2dayShow air2dayShow) {
    	air2dayShowDao.createAir2dayShow(this, air2dayShow);
    }

}
