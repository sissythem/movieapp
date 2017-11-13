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
import com.gnt.movies.dao.NowPlayingMovieDao;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.NowPlayingMovie;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
public class NowPlayingMovieBean implements DataProviderHolder{
	private static final Logger logger = LoggerFactory.getLogger(NowPlayingMovieBean.class);	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("NowPlayingMovieDaoImpl")
	NowPlayingMovieDao nowPlayingMovieDao;
	
	private static HashSet<Integer> allIdTmdb;
	
	@EJB
	private MovieBean movieBean;
	
	@EJB
	private UpcomingMovieBean upcomingMovieBean;
	
    public NowPlayingMovieBean() {
    }
    
    @Override
	public EntityManager getEntityManager() {
		return em;
	}
    public void addNowPlayingMovie(NowPlayingMovie nowPlayingMovie) {
    	nowPlayingMovieDao.createNowPlayingMovie(this, nowPlayingMovie);
    }
    
    public NowPlayingMovie findMovieByIdTmdb(Integer id) {
		return nowPlayingMovieDao.findNowPlayingMovieByIdTmdb(this, id);
	}
    
    public NowPlayingMovie createNowPlayingMovieFromAPI(ApiNewMovie upcomingMovie) {
    	return new NowPlayingMovie(upcomingMovie.getId());
	}
    
    public ArrayList<NowPlayingMovie> getAllNowPlayingMovies(){
    	return (ArrayList<NowPlayingMovie>) nowPlayingMovieDao.findAll(this);
    }
    
    public void findAllIdTmdb (){
		allIdTmdb =(HashSet<Integer>) nowPlayingMovieDao.getAllIdTmdb(this);
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void checkNowPlayingMovie(ApiNewMovie newMovieAPI) {
    	logger.info("Adding movie with tmdbId=" + newMovieAPI.getId());
    	if(allIdTmdb.contains(newMovieAPI.getId()))
			return;
    	NowPlayingMovie nowPlayingMovie = createNowPlayingMovieFromAPI(newMovieAPI);
		Movie movie = movieBean.addNewMovie(newMovieAPI);
		nowPlayingMovie.setMovie(movie);
		addNowPlaying(nowPlayingMovie);
		allIdTmdb.add(nowPlayingMovie.getIdTmdb());
//    	Movie movie;
//    	if (nowPlayingMovieDao.findNowPlayingMovieByIdTmdb(this, newMovieAPI.getId()) == null) {
//    		NowPlayingMovie newNowPlayingMovie = createNowPlayingMovieFromAPI(newMovieAPI);
//    		if (movieBean.findMovieByIdTmdb(newMovieAPI.getId()) == null) {
//    			movie = movieBean.addNewMovie(newMovieAPI);
//    		}
//    		else {
//    			movie = movieBean.findMovieByIdTmdb(newMovieAPI.getId());
//    		}
//    		newNowPlayingMovie.setMovie(movie);
//    	 	addNowPlayingMovie(newNowPlayingMovie);
//    	}
    }
    
    public boolean addNowPlaying(NowPlayingMovie nowPlayingMovie) {
		try {
			nowPlayingMovieDao.createNowPlayingMovie(this, nowPlayingMovie);
			logger.info(" nowPlayingMovie id:"+nowPlayingMovie.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    public void removeOldNotNowPlayingMovies(ArrayList<ApiNewMovie> nowPlayingMoviesAPI) {
    	for (ApiNewMovie apiNewMovie: nowPlayingMoviesAPI) {
			allIdTmdb.remove(apiNewMovie.getId());
		}
		
		for (Integer idtmdb : allIdTmdb) {
			logger.info("removing now playing movie with tmdbId=" + idtmdb);
			nowPlayingMovieDao.deleteNowPlayingMovieByIdTmdb(this, idtmdb);
		}
    }

	public void removeNowPlayingMoviesFromUpComing(ArrayList<ApiNewMovie> nowPlayingMoviesAPI) {
		//TODO: remove from upcomming list
		
		
	}
    
    
}
