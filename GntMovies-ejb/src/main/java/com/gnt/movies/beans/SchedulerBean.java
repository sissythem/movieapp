package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.NowPlayingMovie;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.CastCrewResultsAPI;
import com.gnt.movies.theMovieDB.MovieDetailsAPI;
import com.gnt.movies.theMovieDB.NewShowsAPI;
import com.gnt.movies.theMovieDB.ShowResultsAPI;
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
    
    //TODO set timer
    
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
    	return newUpcomingMovies;
    }
    
    public ArrayList<UpcomingNowPlayingMovieAPI> getNowPlayingMoviesFromAPI(){
    	ArrayList<UpcomingNowPlayingMovieAPI> newNowPlayingMovies = new ArrayList<>();
    	String nowPlayingMoviesURL = Utils.NOW_PLAYING_MOVIES_URL + Utils.API_KEY + Utils.LANGUAGE_FOR_URL + Utils.NUMBER_PAGE_FOR_URL +"1";
    	String json = APIClient.getResultFromTMDB(nowPlayingMoviesURL);
    	UpcomingNowPlayingMovieResultsAPI resultNowPlaying = new Gson().fromJson(json, UpcomingNowPlayingMovieResultsAPI.class);
    	newNowPlayingMovies.addAll(resultNowPlaying.getResults());
    	int pages = resultNowPlaying.getTotalPages();
    	for(int i=2; i<=pages;i++) {
    		nowPlayingMoviesURL = Utils.NOW_PLAYING_MOVIES_URL + Utils.API_KEY + Utils.LANGUAGE_FOR_URL + Utils.NUMBER_PAGE_FOR_URL + Integer.toString(i);
    		json = APIClient.getResultFromTMDB(nowPlayingMoviesURL);
    		resultNowPlaying = new Gson().fromJson(json, UpcomingNowPlayingMovieResultsAPI.class);
    		newNowPlayingMovies.addAll(resultNowPlaying.getResults());
    	}
    	return newNowPlayingMovies;
    }
    
    public ArrayList<NewShowsAPI> getAir2dayShowsFromAPI(){
    	ArrayList<NewShowsAPI> air2dayShows = new ArrayList<>();
    	//TODO get results
    	return air2dayShows;
    }
    
    public ArrayList<NewShowsAPI> getOnTheAirShowsFromAPI(){
    	ArrayList<NewShowsAPI> onTheAirShows = new ArrayList<>();
    	String onTheAirShowsURL = Utils.ON_THE_AIR_SHOWS_URL + Utils.API_KEY + Utils.LANGUAGE_FOR_URL + Utils.NUMBER_PAGE_FOR_URL + "1";
    	String json = APIClient.getResultFromTMDB(onTheAirShowsURL);
    	ShowResultsAPI showResultsAPI = new Gson().fromJson(json, ShowResultsAPI.class);
    	onTheAirShows.addAll(showResultsAPI.getResults());
    	int pages = showResultsAPI.getTotalPages();
    	for(int i=2; i<=pages;i++) {
    		onTheAirShowsURL = Utils.ON_THE_AIR_SHOWS_URL + Utils.API_KEY + Utils.LANGUAGE_FOR_URL + Utils.NUMBER_PAGE_FOR_URL + Integer.toString(i);
    		json = APIClient.getResultFromTMDB(onTheAirShowsURL);
    		showResultsAPI = new Gson().fromJson(json, ShowResultsAPI.class);
    		onTheAirShows.addAll(showResultsAPI.getResults());
    	}
    	return onTheAirShows;
    }
    
    public MovieDetailsAPI getMovieDetailsFromAPI(int id) {
    	String movieDetailsURL = Utils.GENERAL_MOVIE_URL + Integer.toString(id) + Utils.API_KEY;
    	String json = APIClient.getResultFromTMDB(movieDetailsURL);
    	MovieDetailsAPI movieDetails = new Gson().fromJson(json, MovieDetailsAPI.class);
    	String castCrewURL = Utils.GENERAL_MOVIE_URL + Integer.toString(id) + Utils.CREW_CAST_MOVIES_URL + Utils.API_KEY;
    	json = APIClient.getResultFromTMDB(castCrewURL);
    	CastCrewResultsAPI castCrewResults = new Gson().fromJson(json, CastCrewResultsAPI.class);
    	movieDetails.setCast(castCrewResults.getCastResults());
    	movieDetails.setCrew(castCrewResults.getCrewResults());
    	return movieDetails;
    }
    
}
