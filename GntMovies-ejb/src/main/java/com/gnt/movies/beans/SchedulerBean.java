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
import com.gnt.movies.theMovieDB.NewShowsAPI;
import com.gnt.movies.theMovieDB.UpcomingNowPlayingMovieAPI;


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
	MovieBean movieBean;
	
    public SchedulerBean() {
        
    }
    
    @Override
	public EntityManager getEntityManager() {
		return em;
	}
    
    public void checkUpcomingMoviesToBeStored() {
    	ArrayList<UpcomingNowPlayingMovieAPI> upcomingMoviesAPI = getUpcomingMoviesFromAPI();
    	for(UpcomingNowPlayingMovieAPI upcomingMovieAPI : upcomingMoviesAPI) {
    		if(upcomingMovieBean.findMovie(upcomingMovieAPI.getId()) == null) {
    			UpcomingMovie newUpcomingMovie = upcomingMovieBean.createUpcomingMovieFromAPI(upcomingMovieAPI);
    			Movie newMovie = movieBean.createMovieFromAPI(upcomingMovieAPI);
    			upcomingMovieBean.addUpcomingMovie(newMovie, newUpcomingMovie);
    		}
    	}
    }
    
    /** Get new Movies and Shows from API **/
    public ArrayList<UpcomingNowPlayingMovieAPI> getUpcomingMoviesFromAPI(){
    	ArrayList<UpcomingNowPlayingMovieAPI> newUpcomingMovies = new ArrayList<>();
    	//TODO get results and set timer
    	return newUpcomingMovies;
    }
    
    public ArrayList<UpcomingNowPlayingMovieAPI> getNowPlayingMoviesFromAPI(){
    	ArrayList<UpcomingNowPlayingMovieAPI> newNowPlayingMovies = new ArrayList<>();
    	//TODO get results and set timer
    	return newNowPlayingMovies;
    }
    
    public ArrayList<NewShowsAPI> getAir2dayShowsFromAPI(){
    	ArrayList<NewShowsAPI> air2dayShows = new ArrayList<>();
    	//TODO get results and set timer
    	return air2dayShows;
    }
    
    public ArrayList<NewShowsAPI> getOnTheAirShowsFromAPI(){
    	ArrayList<NewShowsAPI> onTheAirShows = new ArrayList<>();
    	//TODO get results and set timer
    	return onTheAirShows;
    }
}
