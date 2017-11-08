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
import com.gnt.movies.dao.NowPlayingMovieDao;
import com.gnt.movies.entities.NowPlayingMovie;
import com.gnt.movies.theMovieDB.UpcomingNowPlayingMovieAPI;

@Stateless
@LocalBean
public class NowPlayingMovieBean implements DataProviderHolder{
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("NowPlayingMovieDaoImpl")
	NowPlayingMovieDao nowPlayingMovieDao;
	
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
    
    public NowPlayingMovie createNowPlayingMovieFromAPI(UpcomingNowPlayingMovieAPI upcomingMovie) {
    	return new NowPlayingMovie(upcomingMovie.getId());
	}
    
    public ArrayList<NowPlayingMovie> getAllNowPlayingMovies(){
    	return (ArrayList<NowPlayingMovie>) nowPlayingMovieDao.findAll(this);
    }
}
