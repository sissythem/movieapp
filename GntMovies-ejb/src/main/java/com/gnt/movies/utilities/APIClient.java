package com.gnt.movies.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.gnt.movies.theMovieDB.ApiCastCrewResults;
import com.gnt.movies.theMovieDB.ApiMovieDetails;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.theMovieDB.ApiNewMovieResults;
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.theMovieDB.ApiNewShowResults;
import com.gnt.movies.theMovieDB.ApiShowDetails;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

public class APIClient {

	public String getResultFromTMDB(String url) {
		// OkHttpClient client = new OkHttpClient();

		Builder b = new Builder();
		b.readTimeout(15, TimeUnit.SECONDS);
		// b.writeTimeout(600, TimeUnit.SECONDS);

		OkHttpClient client = b.build();

		Request request = new Request.Builder().url(url).get().build();

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
	public ArrayList<ApiNewMovie> getUpcomingMoviesFromAPI() {
		String command = Utils.UPCOMING_MOVIES_URL;
		return getMovieListFromApi(command);
	}

	public ApiMovieDetails getMovieDetailsFromAPI(int id) {

		StringBuilder url = new StringBuilder(Utils.GENERAL_MOVIE_URL).append(Integer.toString(id)).append(Utils.API_KEY);
		StringBuilder castCrewURL = new StringBuilder(Utils.GENERAL_MOVIE_URL).append(Integer.toString(id)).append(Utils.CREW_CAST_URL).append(Utils.API_KEY);
		
		APIClientRunnable run1 = new APIClientRunnable(url.toString());
		Thread t1 = new Thread(run1);
		t1.start();
		
		APIClientRunnable run2 = new APIClientRunnable(castCrewURL.toString());
		Thread t2 = new Thread(run2);
		t2.start();
		

		//join here
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ApiMovieDetails movieDetails = new Gson().fromJson(run1.getResult(), ApiMovieDetails.class);
		ApiCastCrewResults castCrewResults = new Gson().fromJson(run2.getResult(), ApiCastCrewResults.class);
		movieDetails.setCast(castCrewResults.getCastResults());
		movieDetails.setCrew(castCrewResults.getCrewResults());

		return movieDetails;
	}

	public ApiNewMovieResults getPagesForMovies(int page, String typeOfMovieUrl) {
		StringBuilder url = new StringBuilder(typeOfMovieUrl).append(Utils.API_KEY).append(Utils.LANGUAGE_FOR_URL)
				.append(Utils.NUMBER_PAGE_FOR_URL).append(Integer.toString(page));

		String resultJson = getResultFromTMDB(url.toString());

		ApiNewMovieResults upcomingNowPlayingMovieResults = new Gson().fromJson(resultJson, ApiNewMovieResults.class);

		return upcomingNowPlayingMovieResults;
	}

	public ArrayList<ApiNewMovie> getNowPlayingMoviesFromAPI() {

		String command = Utils.NOW_PLAYING_MOVIES_URL;
		return getMovieListFromApi(command);
	}

	private ArrayList<ApiNewMovie> getMovieListFromApi(String command) {
		ArrayList<ApiNewMovie> movies = new ArrayList<>();

		ApiNewMovieResults resultNowPlaying = getPagesForMovies(1, command);
		movies.addAll(resultNowPlaying.getResults());

		for (int page = 2; page <= resultNowPlaying.getTotalPages(); page++) {
			resultNowPlaying = getPagesForMovies(page, command);
			movies.addAll(resultNowPlaying.getResults());
		}

		return movies;

	}
	private ArrayList<ApiNewShow> getShowListFromApi(String command) {
		ArrayList<ApiNewShow> shows = new ArrayList<>();

		ApiNewShowResults showResults = getPagesForShows(1, command);
		shows.addAll(showResults.getResults());

		for (int page = 2; page <= showResults.getTotalPages(); page++) {
			showResults= getPagesForShows(page, command);
			shows.addAll(showResults.getResults());
		}
		
		return shows;

	}
	public ArrayList<ApiNewShow> getAir2dayShowsFromAPI() {
		String command = Utils.AIR2DAY_SHOWS_URL;
		return getShowListFromApi(command);
	}

	public ArrayList<ApiNewShow> getOnTheAirShowsFromAPI() {
		String command = Utils.ON_THE_AIR_SHOWS_URL;
		return getShowListFromApi(command);
	}

	public ApiShowDetails getShowDetailsFromAPI(int id) {
		StringBuilder url = new StringBuilder(Utils.GENERAL_SHOW_URL).append(Integer.toString(id))
				.append(Utils.API_KEY);
		StringBuilder castCrewURL = new StringBuilder(Utils.GENERAL_SHOW_URL).append(Integer.toString(id)).append(Utils.CREW_CAST_URL).append(Utils.API_KEY);
		
		APIClientRunnable run1 = new APIClientRunnable(url.toString());
		Thread t1 = new Thread(run1);
		t1.start();
		
		APIClientRunnable run2 = new APIClientRunnable(castCrewURL.toString());
		Thread t2 = new Thread(run2);
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ApiShowDetails showDetails = new Gson().fromJson(run1.getResult(), ApiShowDetails.class);
		ApiCastCrewResults castCrewResults = new Gson().fromJson(run2.getResult(), ApiCastCrewResults.class);
		showDetails.setCast(castCrewResults.getCastResults());
		showDetails.setCrew(castCrewResults.getCrewResults());

		return showDetails;
	}

	public ApiNewShowResults getPagesForShows(int page, String typeOfShowUrl) {
		StringBuilder url = new StringBuilder(typeOfShowUrl).append(Utils.API_KEY).append(Utils.LANGUAGE_FOR_URL)
				.append(Utils.NUMBER_PAGE_FOR_URL).append(Integer.toString(page));

		String resultJson = getResultFromTMDB(url.toString());

		ApiNewShowResults showResults = new Gson().fromJson(resultJson, ApiNewShowResults.class);

		return showResults;
	}
}
