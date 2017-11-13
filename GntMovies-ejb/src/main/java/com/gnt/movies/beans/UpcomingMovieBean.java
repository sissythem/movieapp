package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.UpcomingMovieDao;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

/**
 * Session Bean implementation class UpcomingMovieBean
 */
@Stateless
@LocalBean
public class UpcomingMovieBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(UpcomingMovieBean.class);
	@PersistenceContext
	private EntityManager em;

	@Inject
	@JpaDao
	@Named("UpcomingMovieDaoImpl")
	UpcomingMovieDao upcomingMovieDao;
	
	@EJB
	MovieBean movieBean;

	public UpcomingMovieBean() {
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	public boolean addUpcomingMovie(UpcomingMovie upcomingMovie) {
		try {
			upcomingMovieDao.createUpcomingMovie(this, upcomingMovie);
			logger.info(" upcommingMovie id:"+upcomingMovie.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void checkUpcomingMoviesToBeDeleted(ArrayList<Integer> newIdTmdb) {
		try {
			ArrayList<Integer> allIdTmdb = (ArrayList<Integer>) upcomingMovieDao.getAllIdTmdb(this);
			for(int i=0; i<allIdTmdb.size();i++) {
				if(!newIdTmdb.contains(allIdTmdb.get(i))) {
					UpcomingMovie upcomingMovie = upcomingMovieDao.findByIdTmdb(this, allIdTmdb.get(i));
					upcomingMovieDao.deleteUpcomingMovie(this, upcomingMovie);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public UpcomingMovie findMovieByIdTmdb(Integer id) {
		return upcomingMovieDao.findByIdTmdb(this, id);
	}
	
	public UpcomingMovie createUpcomingMovieFromAPI(ApiNewMovie upcomingMovie) {
    	return new UpcomingMovie(upcomingMovie.getId());
	}
	
	public ArrayList<UpcomingMovie> getAllUpcomingMovies(){
		return (ArrayList<UpcomingMovie>) upcomingMovieDao.findAll(this);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void checkUpcomingMovie(ApiNewMovie movieAPI) {
		logger.info("Adding movie with tmdbId=" + movieAPI.getId());
		// check if movie exists....
		// check if delete upcomming....
		UpcomingMovie upcomingMovie = createUpcomingMovieFromAPI(movieAPI);

		Movie movie = movieBean.addNewMovie(movieAPI);

		upcomingMovie.setMovie(movie);
		addUpcomingMovie(upcomingMovie);

		// UpcomingMovie.setMovie(movie);
		// upcomingMovieBean.addUpcomingMovie(UpcomingMovie);
	}
}
