package com.gnt.movies.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.OnTheAirShowDao;

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

}
