package com.gnt.movies.utilities;

import java.io.IOException;
import java.util.ArrayList;

import com.gnt.movies.theMovieDB.CastCrewResultsAPI;
import com.gnt.movies.theMovieDB.MovieDetailsAPI;
import com.gnt.movies.theMovieDB.NewShowsAPI;
import com.gnt.movies.theMovieDB.ShowDetailsAPI;
import com.gnt.movies.theMovieDB.ShowResultsAPI;
import com.gnt.movies.theMovieDB.UpcomingNowPlayingMovieAPI;
import com.gnt.movies.theMovieDB.UpcomingNowPlayingMovieResultsAPI;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIClient {

	public static String getResultFromTMDB(String url) 
	{
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url(url)
		  .get()
		  .build();

		Response response;
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	/** Get new Movies and Shows from API **/
    public static ArrayList<UpcomingNowPlayingMovieAPI> getUpcomingMoviesFromAPI(){
    	
    	ArrayList<UpcomingNowPlayingMovieAPI> newUpcomingMovies = new ArrayList<>();
    	
    	UpcomingNowPlayingMovieResultsAPI upcomingNowPlayingMovieResults = getUpcomingMoviePage(1);
    	newUpcomingMovies.addAll(upcomingNowPlayingMovieResults.getResults());
    	
    	for (int page = 2; page <= upcomingNowPlayingMovieResults.getTotalPages(); page++) {
   			upcomingNowPlayingMovieResults = getUpcomingMoviePage(page);
    			
   			newUpcomingMovies.addAll(upcomingNowPlayingMovieResults.getResults());
    	}
    	
    	return newUpcomingMovies;
    }
    
    public static ArrayList<UpcomingNowPlayingMovieAPI> getNowPlayingMoviesFromAPI(){
    	
    	ArrayList<UpcomingNowPlayingMovieAPI> newNowPlayingMovies = new ArrayList<>();
    	
    	UpcomingNowPlayingMovieResultsAPI resultNowPlaying = getNowPlayingMoviePage(1);
    	newNowPlayingMovies.addAll(resultNowPlaying.getResults());
    	
    	for (int page = 2; page <= resultNowPlaying.getTotalPages(); page++) {
    		
    		resultNowPlaying = getNowPlayingMoviePage(page);
    		
    		newNowPlayingMovies.addAll(resultNowPlaying.getResults());
    	}
    	
    	return newNowPlayingMovies;
    }
    
    public static ArrayList<NewShowsAPI> getAir2dayShowsFromAPI(){
    	ArrayList<NewShowsAPI> air2dayShows = new ArrayList<>();
    	
    	ShowResultsAPI showResults = getAir2dayShowPage(1);
    	air2dayShows.addAll(showResults.getResults());
    	
    	for (int page = 2; page <= showResults.getTotalPages(); page++) {
    		
    		showResults = getAir2dayShowPage(page);
    		
    		air2dayShows.addAll(showResults.getResults());
    	}

    	return air2dayShows;
    }
    
    public static ArrayList<NewShowsAPI> getOnTheAirShowsFromAPI(){
    	ArrayList<NewShowsAPI> onTheAirShows = new ArrayList<>();
    	
    	ShowResultsAPI showResultsAPI = getShowOnTheAirPage(1);
    	onTheAirShows.addAll(showResultsAPI.getResults());
    	
    	for (int page = 2; page <= showResultsAPI.getTotalPages(); page++) {
    		
    		showResultsAPI = getShowOnTheAirPage(page);
    		
    		onTheAirShows.addAll(showResultsAPI.getResults());
    	}
    	
    	return onTheAirShows;
    }
    
    public static MovieDetailsAPI getMovieDetailsFromAPI(int id) {
    	
    	StringBuilder url = new StringBuilder(Utils.GENERAL_MOVIE_URL)
    			.append(Integer.toString(id))
    			.append(Utils.API_KEY);
    	
    	String json = getResultFromTMDB(url.toString());
    	
    	MovieDetailsAPI movieDetails = new Gson().fromJson(json, MovieDetailsAPI.class);
    	String castCrewURL = Utils.GENERAL_MOVIE_URL + Integer.toString(id) + Utils.CREW_CAST_MOVIES_URL + Utils.API_KEY;
    	
    	json = getResultFromTMDB(castCrewURL);
    	CastCrewResultsAPI castCrewResults = new Gson().fromJson(json, CastCrewResultsAPI.class);
    	
    	movieDetails.setCast(castCrewResults.getCastResults());
    	movieDetails.setCrew(castCrewResults.getCrewResults());
    	
    	return movieDetails;
    }
    
    public static ShowDetailsAPI getShowDetailsFromAPI(int id) {
    	StringBuilder url = new StringBuilder(Utils.GENERAL_SHOW_URL)
    			.append(Integer.toString(id))
    			.append(Utils.API_KEY);
    	
    	String json = getResultFromTMDB(url.toString());
    	
    	ShowDetailsAPI showDetails = new Gson().fromJson(json, ShowDetailsAPI.class);
    	
    	return showDetails;
    }
    
    public static UpcomingNowPlayingMovieResultsAPI getUpcomingMoviePage(int page) {    	
    	StringBuilder url = new StringBuilder(Utils.UPCOMING_MOVIES_URL)
    			.append(Utils.API_KEY)
    			.append(Utils.LANGUAGE_FOR_URL)
    			.append(Utils.NUMBER_PAGE_FOR_URL)
    			.append(Integer.toString(page));
    	
    	String resultJson = getResultFromTMDB(url.toString());
    	
    	UpcomingNowPlayingMovieResultsAPI upcomingNowPlayingMovieResults = new Gson()
    			.fromJson(resultJson, UpcomingNowPlayingMovieResultsAPI.class);
  
    	return upcomingNowPlayingMovieResults;
    }
	
    public static UpcomingNowPlayingMovieResultsAPI getNowPlayingMoviePage(int page) {    	
    	StringBuilder url = new StringBuilder(Utils.NOW_PLAYING_MOVIES_URL)
    			.append(Utils.API_KEY)
    			.append(Utils.LANGUAGE_FOR_URL)
    			.append(Utils.NUMBER_PAGE_FOR_URL)
    			.append(Integer.toString(page));
    	
    	String resultJson = getResultFromTMDB(url.toString());
    	
    	UpcomingNowPlayingMovieResultsAPI upcomingNowPlayingMovieResults = new Gson()
    			.fromJson(resultJson, UpcomingNowPlayingMovieResultsAPI.class);
  
    	return upcomingNowPlayingMovieResults;
    }
    
    public static ShowResultsAPI getAir2dayShowPage(int page) {    	
    	StringBuilder url = new StringBuilder(Utils.AIR2DAY_SHOWS_URL)
    			.append(Utils.API_KEY)
    			.append(Utils.LANGUAGE_FOR_URL)
    			.append(Utils.NUMBER_PAGE_FOR_URL)
    			.append(Integer.toString(page));
    	
    	String resultJson = getResultFromTMDB(url.toString());
    	
    	ShowResultsAPI showResults = new Gson()
    			.fromJson(resultJson, ShowResultsAPI.class);
  
    	return showResults;
    }
    
    public static ShowResultsAPI getShowOnTheAirPage(int page) {    	
    	StringBuilder url = new StringBuilder(Utils.ON_THE_AIR_SHOWS_URL)
    			.append(Utils.API_KEY)
    			.append(Utils.LANGUAGE_FOR_URL)
    			.append(Utils.NUMBER_PAGE_FOR_URL)
    			.append(Integer.toString(page));
    	
    	String resultJson = getResultFromTMDB(url.toString());
    	
    	ShowResultsAPI showResults = new Gson()
    			.fromJson(resultJson, ShowResultsAPI.class);
  
    	return showResults;
    }
}
