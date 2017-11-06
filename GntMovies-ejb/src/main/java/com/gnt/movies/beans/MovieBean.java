package com.gnt.movies.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.MovieDao;
import com.gnt.movies.entities.Movie;

@Stateless
@LocalBean
public class MovieBean implements DataProviderHolder{
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	@JpaDao
	@Named("MovieDaoImpl")
	MovieDao movieDao;
	
    public MovieBean() {
    	
    }
    
    public boolean addMovie(Movie movie) {
    	return true;
    }

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
