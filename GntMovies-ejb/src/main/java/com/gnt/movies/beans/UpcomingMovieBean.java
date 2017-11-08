package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.UpcomingMovieDao;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.UpcomingNowPlayingMovieAPI;

/**
 * Session Bean implementation class UpcomingMovieBean
 */
@Stateless
@LocalBean
public class UpcomingMovieBean implements DataProviderHolder {
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

	public boolean addUpcomingMovie(Movie movie, UpcomingMovie upcomingMovie) {
		try {
			movieBean.addMovie(movie);
			upcomingMovieDao.createUpcomingMovie(this, upcomingMovie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteUpcomingMovie(ArrayList<Integer> newIdTmdb) {
		try {
			ArrayList<Integer> allIdTmdb = (ArrayList<Integer>) upcomingMovieDao.getAllIdTmdb(this);
			for(int i=0; i<allIdTmdb.size();i++) {
				if(!newIdTmdb.contains(allIdTmdb.get(i))) {
					UpcomingMovie upcomingMovie = upcomingMovieDao.findByIdTmdb(this, allIdTmdb.get(i));
					upcomingMovieDao.deleteUpcomingMovie(this, upcomingMovie);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public UpcomingMovie findMovieByIdTmdb(Integer id) {
		return upcomingMovieDao.findByIdTmdb(this, id);
	}
	
	public UpcomingMovie createUpcomingMovieFromAPI(UpcomingNowPlayingMovieAPI upcomingMovie) {
    	return new UpcomingMovie(upcomingMovie.getId());
	}
	
	public ArrayList<UpcomingMovie> getAllUpcomingMovies(){
		return (ArrayList<UpcomingMovie>) upcomingMovieDao.findAll(this);
	}
	
}
