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
import com.gnt.movies.theMovieDB.ApiNewMovie;

@Stateless
@LocalBean
public class NowPlayingMovieBean implements DataProviderHolder{
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("NowPlayingMovieDaoImpl")
	NowPlayingMovieDao nowPlayingMovieDao;
	
	HashSet<Integer> allIdTmdb;
	
	@EJB
	MovieBean movieBean;
	
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
    	Movie movie;
    	if (nowPlayingMovieDao.findNowPlayingMovieByIdTmdb(this, newMovieAPI.getId()) == null) {
    		NowPlayingMovie newNowPlayingMovie = createNowPlayingMovieFromAPI(newMovieAPI);
    		if (movieBean.findMovieByIdTmdb(newMovieAPI.getId()) == null) {
    			movie = movieBean.addNewMovie(newMovieAPI);
    		}
    		else {
    			movie = movieBean.findMovieByIdTmdb(newMovieAPI.getId());
    		}
    		newNowPlayingMovie.setMovie(movie);
    	 	addNowPlayingMovie(newNowPlayingMovie);
    	}
    }
    
    public void checkNowPlayingMoviesToBeDeleted(ArrayList<ApiNewMovie> newNowPlaying) {
//		try {
//			ArrayList<Integer> allIdTmdb = (ArrayList<Integer>) nowPlayingMovieDao.getAllIdTmdb(this);
//			for(int i=0; i<allIdTmdb.size();i++) {
//				if(!newIdTmdb.contains(allIdTmdb.get(i))) {
//					NowPlayingMovie nowPlayingMovie = nowPlayingMovieDao.findNowPlayingMovieByIdTmdb(this, allIdTmdb.get(i));
//					nowPlayingMovieDao.deleteNowPlayingMovie(this, nowPlayingMovie);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
    
    
}
