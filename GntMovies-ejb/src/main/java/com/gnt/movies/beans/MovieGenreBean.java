package com.gnt.movies.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.MovieGenreDao;
import com.gnt.movies.entities.MovieGenre;

/**
 * Session Bean implementation class MovieGenreBean
 */
@Stateless
@LocalBean
public class MovieGenreBean implements DataProviderHolder {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("GenreDaoImpl")
	MovieGenreDao movieGenreDao;
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
    public MovieGenreBean() {
    }
    
    public void addMovieGenre(MovieGenre movieGenre) {
    	movieGenreDao.createMovieGenre(this, movieGenre);
    }

}
