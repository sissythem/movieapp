package com.gnt.movies.beans;

import java.util.ArrayList;
import java.util.HashSet;

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
	private UpcomingMovieDao upcomingMovieDao;

	@EJB
	private MovieBean movieBean;

	static volatile HashSet<Integer> allIdTmdb;

	public UpcomingMovieBean() {
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	public boolean addUpcomingMovie(UpcomingMovie upcomingMovie) {
		try {
			upcomingMovieDao.createUpcomingMovie(this, upcomingMovie);
			logger.info(" upcommingMovie id:" + upcomingMovie.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void findAllIdTmdb() {
		allIdTmdb = (HashSet<Integer>) upcomingMovieDao.getAllIdTmdb(this);
	}

	public UpcomingMovie findMovieByIdTmdb(Integer id) {
		return upcomingMovieDao.findByIdTmdb(this, id);
	}

	public UpcomingMovie createUpcomingMovieFromAPI(ApiNewMovie upcomingMovie) {
		return new UpcomingMovie(upcomingMovie.getId());
	}

	public ArrayList<UpcomingMovie> getAllUpcomingMovies() {
		return (ArrayList<UpcomingMovie>) upcomingMovieDao.findAll(this);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void checkUpcomingMovie(ApiNewMovie movieAPI) {
		findAllIdTmdb();
		if (allIdTmdb.contains(movieAPI.getId()))
			return;
		logger.info("Adding movie with tmdbId=" + movieAPI.getId());
		UpcomingMovie upcomingMovie = createUpcomingMovieFromAPI(movieAPI);
		Movie movie =null;
		synchronized (this) {
			movie = movieBean.getMovie(movieAPI);
		}
		upcomingMovie.setMovie(movie);
		addUpcomingMovie(upcomingMovie);
		allIdTmdb.add(upcomingMovie.getIdTmdb());
	}

	public void removeOldNotUpMovies(HashSet<ApiNewMovie> apiNewMovieArrayList) {

		for (ApiNewMovie apiNewMovie : apiNewMovieArrayList) {
			allIdTmdb.remove(apiNewMovie.getId());
		}

		for (Integer idtmdb : allIdTmdb) {
			logger.info("removing movie with tmdbId=" + idtmdb);
			upcomingMovieDao.deleteUpcomingMovieByIdTmdb(this, idtmdb);
		}
	}

	public static HashSet<Integer> getAllIdTmdb() {
		return allIdTmdb;
	}
}
