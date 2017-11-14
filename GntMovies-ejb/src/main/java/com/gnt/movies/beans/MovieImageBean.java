package com.gnt.movies.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.MovieImageDao;
import com.gnt.movies.entities.MovieImage;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
public class MovieImageBean implements DataProviderHolder{
	private static final Logger logger = LoggerFactory.getLogger(MovieImageBean.class);

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("MovieImageDaoImpl")
	MovieImageDao movieImageDao;
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
    public MovieImageBean() {
    }
    
public void addMovieImage(MovieImage movieImage) {
    	
    	movieImageDao.createMovieImage(this, movieImage);
    	logger.info("addMovieGenre with movieId="+movieImage.getMovie().getIdTmdb());
    }

}
