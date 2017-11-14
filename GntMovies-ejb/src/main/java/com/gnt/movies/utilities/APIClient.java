package com.gnt.movies.utilities;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

		StringBuilder url = new StringBuilder(Utils.GENERAL_MOVIE_URL).append(Integer.toString(id))
				.append(Utils.API_KEY).append(Utils.IMAGES_URL).append(Utils.CREW_CAST_URL);

		String result = getResultFromTMDB(url.toString());

		ApiMovieDetails movieDetails = new Gson().fromJson(result, ApiMovieDetails.class);

		return movieDetails;
	}

	public ApiNewMovieResults getPagesForMovies(int page, String urlApi) {
		StringBuilder url = new StringBuilder(urlApi).append(Utils.API_KEY).append(Utils.LANGUAGE_FOR_URL)
				.append(Utils.NUMBER_PAGE_FOR_URL).append(Integer.toString(page));
		String resultJson = getResultFromTMDB(url.toString());
		ApiNewMovieResults upcomingNowPlayingMovieResults = new Gson().fromJson(resultJson, ApiNewMovieResults.class);
		return upcomingNowPlayingMovieResults;
	}

	public ArrayList<ApiNewMovie> getNowPlayingMoviesFromAPI() {
		String command = Utils.NOW_PLAYING_MOVIES_URL;
		return getMovieListFromApi(command);
	}

	private ArrayList<ApiNewMovie> getMovieListFromApi(String urlApi) {
		ArrayList<ApiNewMovie> movies = new ArrayList<>();
		ApiNewMovieResults resultNowPlaying = getPagesForMovies(1, urlApi);
		movies.addAll(resultNowPlaying.getResults());
		int i;
		for (i = 0; i < resultNowPlaying.getTotalPages(); i++) {

		}
		for (int page = 2; page <= resultNowPlaying.getTotalPages(); page++) {
			resultNowPlaying = getPagesForMovies(page, urlApi);
			movies.addAll(resultNowPlaying.getResults());
		}
		return movies;

	}

	private ArrayList<ApiNewShow> getShowListFromApi(String command) {
		ArrayList<ApiNewShow> shows = new ArrayList<>();
		ApiNewShowResults showResults = getPagesForShows(1, command);
		shows.addAll(showResults.getResults());
		for (int page = 2; page <= showResults.getTotalPages(); page++) {
			showResults = getPagesForShows(page, command);
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
		StringBuilder url = new StringBuilder(Utils.GENERAL_SHOW_URL).append(Integer.toString(id)).append(Utils.API_KEY)
				.append(Utils.IMAGES_URL).append(Utils.CREW_CAST_URL);

		String result = getResultFromTMDB(url.toString());
		ApiShowDetails showDetails = new Gson().fromJson(result, ApiShowDetails.class);
		return showDetails;
	}

	public ApiNewShowResults getPagesForShows(int page, String typeOfShowUrl) {
		StringBuilder url = new StringBuilder(typeOfShowUrl).append(Utils.API_KEY).append(Utils.LANGUAGE_FOR_URL)
				.append(Utils.NUMBER_PAGE_FOR_URL).append(Integer.toString(page));
		String resultJson = getResultFromTMDB(url.toString());
		ApiNewShowResults showResults = new Gson().fromJson(resultJson, ApiNewShowResults.class);
		return showResults;
	}

	public ArrayList<ApiNewMovie> getUpcomingMovies() {

		StringBuilder sb = new StringBuilder(Utils.UPCOMING_MOVIES_URL).append(Utils.API_KEY)
				.append(Utils.LANGUAGE_FOR_URL).append(Utils.NUMBER_PAGE_FOR_URL);

		return getMovies(sb.toString());
	}

	public ArrayList<ApiNewMovie> getMovies(String url) {
		ArrayList<APIClientRunnable> rs = new ArrayList<>();
		ArrayList<Thread> ts = new ArrayList<>();
		ArrayList<ApiNewMovie> movies = new ArrayList<>();

		StringBuilder sb = new StringBuilder(url).append("1");
		APIClientRunnable r = new APIClientRunnable(sb.toString());

		Thread t = new Thread(r);
		rs.add(r);
		ts.add(t);

		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pages = new Gson().fromJson(r.getResult(), ApiNewMovieResults.class).getTotalPages();
		for (int page = 2; page <= pages; page++) {
			sb = new StringBuilder(url).append(page);
			r = new APIClientRunnable(sb.toString());
			rs.add(r);
			t = new Thread(r);
			ts.add(t);
			t.start();
		}
		for (int page = 1; page < pages; page++) {
			try {
				ts.get(page).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int page = 1; page < pages; page++) {
			movies.addAll(new Gson().fromJson(rs.get(page).getResult(), ApiNewMovieResults.class).getResults());
		}
		return movies;
	}
}
