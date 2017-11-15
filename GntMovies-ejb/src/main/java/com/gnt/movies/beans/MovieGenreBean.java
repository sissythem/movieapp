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
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

/**
 * Session Bean implementation class MovieGenreBean
 */
@Stateless
@LocalBean
public class MovieGenreBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(MovieGenreBean.class);	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("MovieGenreDaoImpl")
	MovieGenreDao movieGenreDao;
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
    public MovieGenreBean() {
    }
    
    public synchronized void addMovieGenre(MovieGenre movieGenre) {
    	movieGenreDao.createMovieGenre(this, movieGenre);
//    	logger.info("addMovieGenre "+movieGenre.getGenre().getName()+" with movieId="+movieGenre.getMovie().getIdTmdb());
    }

}
