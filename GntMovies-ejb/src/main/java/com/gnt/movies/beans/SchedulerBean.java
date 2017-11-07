package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.NowPlayingMovie;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.MovieDetailsAPI;
import com.gnt.movies.theMovieDB.NewShowsAPI;
import com.gnt.movies.theMovieDB.UpcomingNowPlayingMovieAPI;
import com.gnt.movies.theMovieDB.UpcomingNowPlayingMovieResultsAPI;
import com.gnt.movies.utilities.APIClient;
import com.gnt.movies.utilities.Utils;
import com.google.gson.Gson;


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
    		if(upcomingMovieBean.findMovieByIdTmdb(upcomingMovieAPI.getId()) == null) {
    			UpcomingMovie newUpcomingMovie = upcomingMovieBean.createUpcomingMovieFromAPI(upcomingMovieAPI);
    			Movie newMovie = movieBean.createMovieFromAPI(upcomingMovieAPI);
    			updateMovieWithDetailsFromAPI(newMovie);
    			upcomingMovieBean.addUpcomingMovie(newMovie, newUpcomingMovie);
    		}
    	}
    }
    
    public void checkNowPlayingMoviesToBeStored() {
    	ArrayList<UpcomingNowPlayingMovieAPI> nowPlayingMoviesAPI = getNowPlayingMoviesFromAPI();
    	for(UpcomingNowPlayingMovieAPI upcomingMovieAPI : nowPlayingMoviesAPI) {
    		if(nowPlayingMovieBean.findMovieByIdTmdb(upcomingMovieAPI.getId()) == null) {
    			NowPlayingMovie newNowPlayingMovie = nowPlayingMovieBean.createNowPlayingMovieFromAPI(upcomingMovieAPI);
    			if(movieBean.findMovieByIdTmdb(upcomingMovieAPI.getId()) == null) {
    				Movie newMovie = movieBean.createMovieFromAPI(upcomingMovieAPI);
    				updateMovieWithDetailsFromAPI(newMovie);
    				movieBean.addMovie(newMovie);
    				nowPlayingMovieBean.addNowPlayingMovie(newNowPlayingMovie);
    			}
    			else {
    				Movie movie = movieBean.findMovieByIdTmdb(upcomingMovieAPI.getId());
    				newNowPlayingMovie.setMovie(movie);
    				nowPlayingMovieBean.addNowPlayingMovie(newNowPlayingMovie);
    			}
    		}
    	}
    }
    
    public void updateMovieWithDetailsFromAPI(Movie movie) {
    	MovieDetailsAPI movieDetails = getMovieDetailsFromAPI(movie.getIdTmdb());
    	movieBean.updateMovieWithDetails(movie, movieDetails);
    }
    
    /** Get new Movies and Shows from API **/
    public ArrayList<UpcomingNowPlayingMovieAPI> getUpcomingMoviesFromAPI(){
    	ArrayList<UpcomingNowPlayingMovieAPI> newUpcomingMovies = new ArrayList<>();
    	
    	StringBuilder url = new StringBuilder(Utils.UPCOMING_MOVIES_URL)
    			.append(Utils.API_KEY)
    			.append(Utils.LANGUAGE_FOR_URL)
    			.append(Utils.NUMBER_PAGE_FOR_URL)
    			.append("1");
    	
    	String result = APIClient.getResultFromTMDB(url.toString());
    	
    	UpcomingNowPlayingMovieResultsAPI upcomingNowPlayingMovieResultsApi = new Gson().fromJson(result, UpcomingNowPlayingMovieResultsAPI.class);
    	
    	newUpcomingMovies.addAll(upcomingNowPlayingMovieResultsApi.getResults());
    	
    	for (int page = 2; page <= upcomingNowPlayingMovieResultsApi.getTotalPages(); page++) {
    			url.replace(url.length()-1, url.length(), Integer.toString(page));
    			
    			result = APIClient.getResultFromTMDB(url.toString());
    			upcomingNowPlayingMovieResultsApi = new Gson().fromJson(result, UpcomingNowPlayingMovieResultsAPI.class);
    			
    			newUpcomingMovies.addAll(upcomingNowPlayingMovieResultsApi.getResults());
    	}
    	
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
    
    public MovieDetailsAPI getMovieDetailsFromAPI(int id) {
    	MovieDetailsAPI movieDetails = new MovieDetailsAPI();
    	//TODO call from API movie details and crew/cast
    	return movieDetails;
    }
    
}
