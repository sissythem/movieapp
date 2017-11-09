package com.gnt.movies.utilities;

import java.io.IOException;
import java.util.ArrayList;

import com.gnt.movies.theMovieDB.ApiCastCrewResults;
import com.gnt.movies.theMovieDB.ApiMovieDetails;
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.theMovieDB.ApiShowDetails;
import com.gnt.movies.theMovieDB.ApiNewShowResults;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.theMovieDB.ApiNewMovieResults;
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
    public static ArrayList<ApiNewMovie> getUpcomingMoviesFromAPI(){
    	
    	ArrayList<ApiNewMovie> newUpcomingMovies = new ArrayList<>();
    	
    	ApiNewMovieResults upcomingNowPlayingMovieResults = getPagesForMovies(1, Utils.UPCOMING_MOVIES_URL);
    	newUpcomingMovies.addAll(upcomingNowPlayingMovieResults.getResults());
    	
    	for (int page = 2; page <= upcomingNowPlayingMovieResults.getTotalPages(); page++) {
   			upcomingNowPlayingMovieResults = getPagesForMovies(page, Utils.UPCOMING_MOVIES_URL);
    			
   			newUpcomingMovies.addAll(upcomingNowPlayingMovieResults.getResults());
    	}
    	
    	return newUpcomingMovies;
    }
    
    public static ArrayList<ApiNewMovie> getNowPlayingMoviesFromAPI(){
    	
    	ArrayList<ApiNewMovie> newNowPlayingMovies = new ArrayList<>();
    	
    	ApiNewMovieResults resultNowPlaying = getPagesForMovies(1, Utils.NOW_PLAYING_MOVIES_URL);
    	newNowPlayingMovies.addAll(resultNowPlaying.getResults());
    	
    	for (int page = 2; page <= resultNowPlaying.getTotalPages(); page++) {
    		
    		resultNowPlaying = getPagesForMovies(page, Utils.NOW_PLAYING_MOVIES_URL);
    		
    		newNowPlayingMovies.addAll(resultNowPlaying.getResults());
    	}
    	
    	return newNowPlayingMovies;
    }
    
    public static ArrayList<ApiNewShow> getAir2dayShowsFromAPI(){
    	ArrayList<ApiNewShow> air2dayShows = new ArrayList<>();
    	
    	ApiNewShowResults showResults = getPagesForShows(1, Utils.AIR2DAY_SHOWS_URL);
    	air2dayShows.addAll(showResults.getResults());
    	
    	for (int page = 2; page <= showResults.getTotalPages(); page++) {
    		
    		showResults =  getPagesForShows(page, Utils.AIR2DAY_SHOWS_URL);
    		
    		air2dayShows.addAll(showResults.getResults());
    	}

    	return air2dayShows;
    }
    
    public static ArrayList<ApiNewShow> getOnTheAirShowsFromAPI(){
    	ArrayList<ApiNewShow> onTheAirShows = new ArrayList<>();
    	
    	ApiNewShowResults apiNewShowResults =  getPagesForShows(1, Utils.ON_THE_AIR_SHOWS_URL);
    	onTheAirShows.addAll(apiNewShowResults.getResults());
    	
    	for (int page = 2; page <= apiNewShowResults.getTotalPages(); page++) {
    		
    		apiNewShowResults = getPagesForShows(page, Utils.ON_THE_AIR_SHOWS_URL);
    		
    		onTheAirShows.addAll(apiNewShowResults.getResults());
    	}
    	
    	return onTheAirShows;
    }
    
    public static ApiMovieDetails getMovieDetailsFromAPI(int id) {
    	
    	StringBuilder url = new StringBuilder(Utils.GENERAL_MOVIE_URL)
    			.append(Integer.toString(id))
    			.append(Utils.API_KEY);
    	
    	String json = getResultFromTMDB(url.toString());
    	
    	ApiMovieDetails movieDetails = new Gson().fromJson(json, ApiMovieDetails.class);
    	String castCrewURL = Utils.GENERAL_MOVIE_URL + Integer.toString(id) + Utils.CREW_CAST_MOVIES_URL + Utils.API_KEY;
    	
    	json = getResultFromTMDB(castCrewURL);
    	ApiCastCrewResults castCrewResults = new Gson().fromJson(json, ApiCastCrewResults.class);
    	
    	movieDetails.setCast(castCrewResults.getCastResults());
    	movieDetails.setCrew(castCrewResults.getCrewResults());
    	
    	return movieDetails;
    }
    
    public static ApiShowDetails getShowDetailsFromAPI(int id) {
    	StringBuilder url = new StringBuilder(Utils.GENERAL_SHOW_URL)
    			.append(Integer.toString(id))
    			.append(Utils.API_KEY);
    	
    	String json = getResultFromTMDB(url.toString());
    	
    	ApiShowDetails showDetails = new Gson().fromJson(json, ApiShowDetails.class);
    	
    	return showDetails;
    }
    
    public static ApiNewMovieResults getPagesForMovies(int page, String typeOfMovieUrl) {    	
    	StringBuilder url = new StringBuilder(typeOfMovieUrl)
    			.append(Utils.API_KEY)
    			.append(Utils.LANGUAGE_FOR_URL)
    			.append(Utils.NUMBER_PAGE_FOR_URL)
    			.append(Integer.toString(page));
    	
    	String resultJson = getResultFromTMDB(url.toString());
    	
    	ApiNewMovieResults upcomingNowPlayingMovieResults = new Gson()
    			.fromJson(resultJson, ApiNewMovieResults.class);
  
    	return upcomingNowPlayingMovieResults;
    }
    
    public static ApiNewShowResults getPagesForShows(int page, String typeOfShowUrl) {    	
    	StringBuilder url = new StringBuilder(typeOfShowUrl)
    			.append(Utils.API_KEY)
    			.append(Utils.LANGUAGE_FOR_URL)
    			.append(Utils.NUMBER_PAGE_FOR_URL)
    			.append(Integer.toString(page));
    	
    	String resultJson = getResultFromTMDB(url.toString());
    	
    	ApiNewShowResults showResults = new Gson()
    			.fromJson(resultJson, ApiNewShowResults.class);
  
    	return showResults;
    }
}
