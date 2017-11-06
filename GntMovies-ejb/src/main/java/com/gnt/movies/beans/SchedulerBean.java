package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.UpcomingMovieAPI;


@Stateless
@LocalBean
public class SchedulerBean implements DataProviderHolder {
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	UpcomingMovieBean upcomingMovieBean;
 
	@EJB
	NowPlayingMovieBean nowPlayingMovieBean;
	
	@EJB
	OnTheAirShowBean onTheAirShowBean;
	
	@EJB
	Air2dayShowBean air2dayShowBean;
	
	@EJB
	UpcomingMoviesAPIBean upcomingMoviesAPIBean;
	
    public SchedulerBean() {
        
    }
    
    @Override
	public EntityManager getEntityManager() {
		return em;
	}
    
    public void checkUpcomingMovies(ArrayList<UpcomingMovieAPI> upcomingMovies) {
    	for(UpcomingMovieAPI upcomingMovie : upcomingMovies) {
    		UpcomingMovie existingMovie = upcomingMovieBean.findMovie(upcomingMovie.getId());
    		if(existingMovie == null) {
    			existingMovie = upcomingMoviesAPIBean.createUpcomingMovieFromAPI(upcomingMovie);
    			Movie newMovie = upcomingMoviesAPIBean.createMovieFromAPI(upcomingMovie);
    			upcomingMovieBean.addUpcomingMovie(newMovie, existingMovie);
    		}
    	}
    }
}
