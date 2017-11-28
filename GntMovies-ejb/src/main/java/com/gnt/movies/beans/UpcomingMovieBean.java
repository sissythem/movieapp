package com.gnt.movies.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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

	private static ConcurrentHashMap<Integer, Boolean> allIdTmdb;

	public UpcomingMovieBean() {
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	public static void init() {
		allIdTmdb = new ConcurrentHashMap<>();
		
	}

	

	public void findAllIdTmdb() {
		for (Object o : upcomingMovieDao.getAllIdTmdb(this)) {
			allIdTmdb.put((Integer) o, true);
		}
	}

	public UpcomingMovie findMovieByIdTmdb(Integer id) {
		return upcomingMovieDao.findByIdTmdb(this, id);
	}

	public UpcomingMovie createUpcomingMovieFromAPI(Movie upcomingMovie) {
		return new UpcomingMovie(upcomingMovie.getIdTmdb());
	}

	public ArrayList<UpcomingMovie> getAllUpcomingMovies() {
		return (ArrayList<UpcomingMovie>) upcomingMovieDao.findAll(this);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void checkUpcomingMovie(Movie movie) {
		if (allIdTmdb.containsKey(movie.getIdTmdb()))
			return;
		logger.info("Adding movie with tmdbId=" + movie.getIdTmdb());
		UpcomingMovie upcomingMovie = createUpcomingMovieFromAPI(movie);
		movie = movieBean.getMovie(movie);
		upcomingMovie.setMovie(movie);
		addUpcomingMovie(upcomingMovie);
		allIdTmdb.put(upcomingMovie.getIdTmdb(), true);
	}

	public Boolean addUpcomingMovie(UpcomingMovie upcomingMovie) {
		logger.info("addUpcomingMovie movie with tmdbId=" + upcomingMovie.getIdTmdb());
		try {
			upcomingMovieDao.createUpcomingMovie(this, upcomingMovie);
			logger.info(" upcommingMovie id:" + upcomingMovie.getIdTmdb());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void removeOldNotUpMovies(HashSet<Movie> movies) {

		for (Movie movie : movies) {
			allIdTmdb.remove(movie.getIdTmdb());
		}
		Set<Integer>allidtmd = allIdTmdb.keySet();
		allidtmd.stream().forEach(e->{
			logger.info("removing movie with tmdbId=" + e);
			upcomingMovieDao.deleteUpcomingMovieByIdTmdb(this, e);
		});
	}
}
