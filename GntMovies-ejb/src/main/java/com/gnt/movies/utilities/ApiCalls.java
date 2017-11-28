package com.gnt.movies.utilities;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.gnt.movies.beans.SchedulerBean;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.Show;
import com.gnt.movies.theMovieDB.ApiNewMovieResults;
import com.gnt.movies.theMovieDB.ApiNewShowResults;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ApiCalls {
	private static final Logger logger = LoggerFactory.getLogger(ApiCalls.class);
	private static ArrayList<ApiClientRunnable> runnables;
	private static Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {

				@Override
				public LocalDate deserialize(JsonElement json, Type type,
						JsonDeserializationContext jsonDeserializationContext) {
					String s = json.getAsJsonPrimitive().getAsString();
					if (s.length() > 9)
						return LocalDate.parse(s);
					else
						return null;
				}

			}).create();

	/**
	 * Calls to MovieDB API ====================
	 **/
	public static HashSet<Genre> getGenres() {
		HashSet<Genre> set = new HashSet<>();
		addMovieGenres(set);
		addShowGenres(set);
		return set;
	}

	private static void addMovieGenres(HashSet<Genre> set) {
		String url = createUrl(Utils.MOVIE_GENRES, Utils.API_KEY, Utils.LANGUAGE_FOR_URL);
		String result = ApiClient.getResultFromTMDB(url);
		JsonObject jo = JsonUtils.getJsonObjectFromString(result);

		for (Genre genre : gson.fromJson(JsonUtils.getJsonArrayFromJson("genres", jo), Genre[].class)) {
			set.add(genre);
		}

	}

	private static void addShowGenres(HashSet<Genre> set) {
		String url = createUrl(Utils.SHOW_GENRES, Utils.API_KEY, Utils.LANGUAGE_FOR_URL);
		String result = ApiClient.getResultFromTMDB(url);
		JsonObject jo = JsonUtils.getJsonObjectFromString(result);

		for (Genre genre : gson.fromJson(JsonUtils.getJsonArrayFromJson("genres", jo), Genre[].class)) {
			set.add(genre);
		}
	}

	public static HashSet<Movie> getUpcomingMovies() {
		return (HashSet<Movie>) getAllResults(Utils.UPCOMING_MOVIES_URL, "movie");
	}

	public static HashSet<Movie> getNowPlayingMovies() {
		return (HashSet<Movie>) getAllResults(Utils.NOW_PLAYING_MOVIES_URL, "movie");
	}

	public static HashSet<Show> getOnTheAirShows() {
		return (HashSet<Show>) getAllResults(Utils.ON_THE_AIR_SHOWS_URL, "show");
	}

	public static HashSet<Show> getAir2dayShows() {
		return (HashSet<Show>) getAllResults(Utils.AIR2DAY_SHOWS_URL, "show");
	}

	public static String getMovieDetailsFromAPI(int id) {
		logger.info("getMovieDetailsFromAPI movie with tmdbId=" + id);
		String url = createUrl(Utils.GENERAL_MOVIE_URL, Integer.toString(id), Utils.API_KEY, Utils.IMAGES_URL,
				Utils.CREW_CAST_URL);
		return ApiClient.getResultFromTMDB(url);
	}

	public static String getShowDetailsFromAPI(int id) {
		logger.info("getShowDetailsFromAPI movie with tmdbId=" + id);
		String url = createUrl(Utils.GENERAL_SHOW_URL, Integer.toString(id), Utils.API_KEY, Utils.IMAGES_URL,
				Utils.CREW_CAST_URL);
		return ApiClient.getResultFromTMDB(url.toString());
	}

	/**
	 * Get results from calls to the MovieDB API
	 * ==========================================
	 **/
	private static HashSet<?> getAllResults(String url, String type) {
		String urlComplete = createUrl(url, Utils.API_KEY, Utils.LANGUAGE_FOR_URL, Utils.NUMBER_PAGE_FOR_URL);
		ApiClientRunnable runnable = firstThreadRun(urlComplete);
		int pages = getTotalNumPages(type, runnable);
		runRemainingThreads(urlComplete, pages);
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
		String newUrl = createUrl(url, "1");
		ApiClientRunnable runnable = new ApiClientRunnable(newUrl);
		ExecutorService executor = MyExecutor.getNewExecutor();
		runnables.add(runnable);
		executor.execute(runnable);
		MyExecutor.terminateExecutor(executor);
		return runnable;
	}

	private static void runRemainingThreads(String url, int pages) {
		ExecutorService executor = MyExecutor.getNewExecutor();
		for (int page = 2; page <= pages; page++) {
			StringBuilder sb = new StringBuilder(url).append(page);
			ApiClientRunnable runnable = new ApiClientRunnable(sb.toString());
			runnables.add(runnable);
			executor.execute(runnable);
		}
		MyExecutor.terminateExecutor(executor);
	}

	private static HashSet<?> getResultsFromPages(String type) {
		if (type == "movie") {
			HashSet<Movie> movies = new HashSet<>();
			runnables.stream().forEach(apiClientRunnable -> {
				String result = apiClientRunnable.getResult();
				logger.info(Thread.currentThread().getId() + ": Result:" + result);
				ApiNewMovieResults apiNewMovieResults = gson.fromJson(result, ApiNewMovieResults.class);
				if (result.contains("status_code\":25")) {
					logger.info(Thread.currentThread().getId() + ": Result:" + result);
				}
				if (apiNewMovieResults != null && apiNewMovieResults.getResults() != null)
					movies.addAll(apiNewMovieResults.getResults());
			});
			return movies;
		} else {
			HashSet<Show> shows = new HashSet<>();
			runnables.stream().forEach(apiClientRunnable -> {
				String result = apiClientRunnable.getResult();
				ApiNewShowResults apiNewShowResults = gson.fromJson(result, ApiNewShowResults.class);
				if (result.contains("status_code\":25")) {
					logger.info(Thread.currentThread().getId() + ": Result:" + result);
				}
				if (apiNewShowResults != null && apiNewShowResults.getResults() != null)
					shows.addAll(apiNewShowResults.getResults());
			});
			return shows;
		}
	}

	public static String createUrl(String... str) {
		StringBuilder sb = new StringBuilder();
		for (String string : str) {
			sb.append(string);
		}
		return sb.toString();
	}
}
