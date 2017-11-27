package com.gnt.movies.utilities;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.Show;
import com.gnt.movies.theMovieDB.ApiGenres;
import com.gnt.movies.theMovieDB.ApiMovieDetails;
import com.gnt.movies.theMovieDB.ApiNewMovieResults;
import com.gnt.movies.theMovieDB.ApiNewShowResults;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class ApiCalls {
	private static final Logger logger = LoggerFactory.getLogger(ApiCalls.class);
	private static ArrayList<ApiClientRunnable> runnables;
	private static ArrayList<Thread> threads;
	private static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
	    
		@Override
	    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
	        String s = json.getAsJsonPrimitive().getAsString();
	        if(s.length()>9)
	        return LocalDate.parse(s);
	        else
	        	return null;
	    }
		
	}).create();	
	/**
	 * Calls to MovieDB API
	 * ====================
	 **/
	public static HashSet<Genre> getGenres() {
		HashSet<Genre> set = new HashSet<>();

		set.addAll(getMovieGenres());
		set.addAll(getShowGenres());
		return set;
	}

	private static ArrayList<Genre> getMovieGenres() {
		StringBuilder sb = new StringBuilder(Utils.MOVIE_GENRES).append(Utils.API_KEY).append(Utils.LANGUAGE_FOR_URL);
		String result = ApiClient.getResultFromTMDB(sb.toString());
		ApiGenres apiGenres = new Gson().fromJson(result, ApiGenres.class);
		return apiGenres.getGenres();
	}

	private static ArrayList<Genre> getShowGenres() {
		StringBuilder sb = new StringBuilder(Utils.SHOW_GENRES).append(Utils.API_KEY).append(Utils.LANGUAGE_FOR_URL);
		String result = ApiClient.getResultFromTMDB(sb.toString());
		ApiGenres apiGenres = new Gson().fromJson(result, ApiGenres.class);
		return apiGenres.getGenres();
	}

	public static HashSet<Movie> getUpcomingMovies() {

		StringBuilder sb = new StringBuilder(Utils.UPCOMING_MOVIES_URL).append(Utils.API_KEY)
				.append(Utils.LANGUAGE_FOR_URL).append(Utils.NUMBER_PAGE_FOR_URL);

		return (HashSet<Movie>) getAllResults(sb.toString(), "movie");
	}

	public static HashSet<Movie> getNowPlayingMovies() {

		StringBuilder sb = new StringBuilder(Utils.NOW_PLAYING_MOVIES_URL).append(Utils.API_KEY)
				.append(Utils.LANGUAGE_FOR_URL).append(Utils.NUMBER_PAGE_FOR_URL);

		return (HashSet<Movie>) getAllResults(sb.toString(), "movie");
	}

	public static HashSet<Show> getOnTheAirShows() {

		StringBuilder sb = new StringBuilder(Utils.ON_THE_AIR_SHOWS_URL).append(Utils.API_KEY)
				.append(Utils.LANGUAGE_FOR_URL).append(Utils.NUMBER_PAGE_FOR_URL);

		return (HashSet<Show>) getAllResults(sb.toString(), "show");
	}

	public static HashSet<Show> getAir2dayShows() {

		StringBuilder sb = new StringBuilder(Utils.AIR2DAY_SHOWS_URL).append(Utils.API_KEY)
				.append(Utils.LANGUAGE_FOR_URL).append(Utils.NUMBER_PAGE_FOR_URL);

		return (HashSet<Show>) getAllResults(sb.toString(), "show");
	}

	public static ApiMovieDetails getMovieDetailsFromAPI(int id) {
		logger.info("getMovieDetailsFromAPI movie with tmdbId=" + id);
		StringBuilder url = new StringBuilder(Utils.GENERAL_MOVIE_URL).append(Integer.toString(id))
				.append(Utils.API_KEY).append(Utils.IMAGES_URL).append(Utils.CREW_CAST_URL);
		 
		return gson.fromJson(ApiClient.getResultFromTMDB(url.toString()), ApiMovieDetails.class);
	}

	public static String getShowDetailsFromAPI(int id) {
		logger.info("getShowDetailsFromAPI movie with tmdbId=" + id);
		StringBuilder url = new StringBuilder(Utils.GENERAL_SHOW_URL).append(Integer.toString(id)).append(Utils.API_KEY)
				.append(Utils.IMAGES_URL).append(Utils.CREW_CAST_URL);
		return ApiClient.getResultFromTMDB(url.toString());
	}
//	public static ApiShowDetails getShowDetailsFromAPI(int id) {
//		logger.info("getShowDetailsFromAPI movie with tmdbId=" + id);
//		StringBuilder url = new StringBuilder(Utils.GENERAL_SHOW_URL).append(Integer.toString(id)).append(Utils.API_KEY)
//				.append(Utils.IMAGES_URL).append(Utils.CREW_CAST_URL);
//		return gson.fromJson(ApiClient.getResultFromTMDB(url.toString()), ApiShowDetails.class);
//	}

	/** Get results from calls to the MovieDB API
	 * ==========================================
	 * **/
	private static HashSet<?> getAllResults(String url, String type) {
		ApiClientRunnable runnable = firstThreadRun(url);
		int pages = getTotalNumPages(type, runnable);
		runRemainingThreads(url, pages);
		return getResultsFromPages(type);
	}

	private static int getTotalNumPages(String type, ApiClientRunnable runnable) {
		int pages = 0;
		if (type == "movie") {
			pages = gson.fromJson(runnable.getResult(), ApiNewMovieResults.class).getTotalPages();
		} else if (type == "show") {
			pages = gson.fromJson(runnable.getResult(), ApiNewShowResults.class).getTotalPages();
		}
		return pages;
	}

	private static ApiClientRunnable firstThreadRun(String url) {
		runnables = new ArrayList<>();
		threads = new ArrayList<>();

		StringBuilder sb = new StringBuilder(url).append("1");
		ApiClientRunnable runnable = new ApiClientRunnable(sb.toString());
		Thread thread = new Thread(runnable);

		runnables.add(runnable);
		threads.add(thread);

		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return runnable;
	}

	private static void runRemainingThreads(String url, int pages) {
		for (int page = 2; page <= pages; page++) {
			StringBuilder sb = new StringBuilder(url).append(page);
			ApiClientRunnable runnable = new ApiClientRunnable(sb.toString());
			runnables.add(runnable);
			Thread thread = new Thread(runnable);
			threads.add(thread);
		}
		for (int i = 1; i < threads.size(); i++) {
			threads.get(i).start();
		}
		for (int i = 1; i < threads.size(); i++) {
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static HashSet<?> getResultsFromPages(String type) {
		if (type == "movie") {
			HashSet<Movie> movies = new HashSet<>();
			runnables.stream().forEach(apiClientRunnable -> {
				String result = apiClientRunnable.getResult();
				logger.info(Thread.currentThread().getId() + ": Result:" + result);
				ApiNewMovieResults apiNewMovieResults = gson.fromJson(result, ApiNewMovieResults.class);
				if(result.contains("status_code\":25")) {
					logger.info(Thread.currentThread().getId() + ": Result:" + result);
				}
				if (apiNewMovieResults != null && apiNewMovieResults.getResults()!=null)
					movies.addAll(apiNewMovieResults.getResults());
			});
			return movies;
		} else {
			HashSet<Show> shows = new HashSet<>();
			runnables.stream().forEach(apiClientRunnable ->{
				String result = apiClientRunnable.getResult();
				ApiNewShowResults apiNewShowResults = gson.fromJson(result, ApiNewShowResults.class);
				if(result.contains("status_code\":25")) {
					logger.info(Thread.currentThread().getId() + ": Result:" + result);
				}
				if (apiNewShowResults != null && apiNewShowResults.getResults()!=null)
				shows.addAll(apiNewShowResults.getResults());});
			return shows;
		}
	}
}
