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
import com.gnt.movies.dao.NowPlayingMovieDao;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.NowPlayingMovie;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
public class NowPlayingMovieBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(NowPlayingMovieBean.class);
	@PersistenceContext
	private EntityManager em;

	@Inject
	@JpaDao
	@Named("NowPlayingMovieDaoImpl")
	NowPlayingMovieDao nowPlayingMovieDao;

	private static ConcurrentHashMap<Integer, Boolean> allIdTmdb;

	@EJB
	private MovieBean movieBean;

	public NowPlayingMovieBean() {
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	public static void init() {
		allIdTmdb = new ConcurrentHashMap<>();
	}
	public void addNowPlayingMovie(NowPlayingMovie nowPlayingMovie) {
		nowPlayingMovieDao.createNowPlayingMovie(this, nowPlayingMovie);
	}

	public NowPlayingMovie findMovieByIdTmdb(Integer id) {
		return nowPlayingMovieDao.findNowPlayingMovieByIdTmdb(this, id);
	}

	public NowPlayingMovie createNowPlayingMovieFromAPI(Movie movie) {
		return new NowPlayingMovie(movie.getIdTmdb());
	}

	public ArrayList<NowPlayingMovie> getAllNowPlayingMovies() {
		return (ArrayList<NowPlayingMovie>) nowPlayingMovieDao.findAll(this);
	}

	public void findAllIdTmdb() {
		for (Object o : nowPlayingMovieDao.getAllIdTmdb(this)) {
			allIdTmdb.put((Integer) o, true);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void checkNowPlayingMovie(Movie movie) {
		
		if (allIdTmdb.containsKey(movie.getIdTmdb()))
			return;
		logger.info("Adding movie with tmdbId=" + movie.getIdTmdb());
		NowPlayingMovie nowPlayingMovie = createNowPlayingMovieFromAPI(movie);
		movie = movieBean.getMovie(movie);
		nowPlayingMovie.setMovie(movie);
		addNowPlaying(nowPlayingMovie);
		allIdTmdb.put(nowPlayingMovie.getIdTmdb(),true);
	}

	public boolean addNowPlaying(NowPlayingMovie nowPlayingMovie) {
		try {
			nowPlayingMovieDao.createNowPlayingMovie(this, nowPlayingMovie);
			logger.info(" nowPlayingMovie id:" + nowPlayingMovie.getIdTmdb());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void removeOldNotNowPlayingMovies(HashSet<Movie> movies) {
		for (Movie movie : movies) {
			allIdTmdb.remove(movie.getIdTmdb());
		}

		Set<Integer>allidtmd = allIdTmdb.keySet();
		allidtmd.stream().forEach(e->{
			logger.info("removing movie with tmdbId=" + e);
			nowPlayingMovieDao.deleteNowPlayingMovieByIdTmdb(this, e);
		});
	}
}
