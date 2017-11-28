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
import com.gnt.movies.dao.MovieFavoriteDao;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.MovieFavorite;
import com.gnt.movies.entities.User;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
public class MovieFavoriteBean implements DataProviderHolder{
	private static final Logger logger = LoggerFactory.getLogger(MovieFavoriteBean.class);
	@PersistenceContext EntityManager em;
	
	@Inject
	@JpaDao
	@Named("MovieFavoriteDaoImpl")
	MovieFavoriteDao movieFavoriteDao;
	
    public MovieFavoriteBean() {
    	
    }
    
    @Override
	public EntityManager getEntityManager() {
		return em;
	}
    
    public void addMovieFavorite(User user, Movie movie) {
		logger.info("Adding movie favorite in the database for movie: " + movie.getId() + " and for user: " + user.getId());
    	movieFavoriteDao.createMovieFavorite(this, new MovieFavorite(user, movie));
    }
    
    public ArrayList<MovieFavorite> getAllMovieFavoritesForUser(User user){
    	return (ArrayList<MovieFavorite>) movieFavoriteDao.findMovieFavoriteByUserId(this, user.getId());
    }
    
    public void removeMovieFavorite(User user, Movie movie) {
    	movieFavoriteDao.deleteMovieFavorite(this, new MovieFavorite(user, movie));
    }

}
