package com.gnt.movies.utilities;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;

import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.Show;
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
	 * Calls to MovieDB API for movie/show genres & movie/show details
	 * ===============================================================
	 **/
	public static HashSet<Genre> getGenres() {
		HashSet<Genre> set = new HashSet<>();
		getGenresFromAPI(set, Utils.MOVIE_GENRES);
		getGenresFromAPI(set, Utils.SHOW_GENRES);
		return set;
	}

	private static void getGenresFromAPI(HashSet<Genre> set, String urlFirstPart) {
		String url = createUrl(urlFirstPart, Utils.API_KEY, Utils.LANGUAGE_FOR_URL);
		String result = ApiClient.getResultFromTMDB(url);
		JsonObject jo = JsonUtils.getJsonObjectFromString(result);
		for (Genre genre : gson.fromJson(JsonUtils.getJsonArrayFromJson("genres", jo), Genre[].class)) {
			set.add(genre);
		}
	}
	
	public static String getDetailsFromAPI(int id, String urlFirstPart) {
		logger.info("getMovieDetailsFromAPI movie with tmdbId=" + id);
		String url = createUrl(urlFirstPart, Integer.toString(id), Utils.API_KEY, Utils.IMAGES_URL, Utils.CREW_CAST_URL);
		return ApiClient.getResultFromTMDB(url);
	}
	
	/** 
	 * Calls for scheduled jobs
	 * ========================
	 **/

	@SuppressWarnings("unchecked")
	public static HashSet<Movie> getUpcomingMovies() {
		return (HashSet<Movie>) getAllResults(Utils.UPCOMING_MOVIES_URL, "movie");
	}

	@SuppressWarnings("unchecked")
	public static HashSet<Movie> getNowPlayingMovies() {
		return (HashSet<Movie>) getAllResults(Utils.NOW_PLAYING_MOVIES_URL, "movie");
	}

	@SuppressWarnings("unchecked")
	public static HashSet<Show> getOnTheAirShows() {
		return (HashSet<Show>) getAllResults(Utils.ON_THE_AIR_SHOWS_URL, "show");
	}

	@SuppressWarnings("unchecked")
	public static HashSet<Show> getAir2dayShows() {
		return (HashSet<Show>) getAllResults(Utils.AIR2DAY_SHOWS_URL, "show");
	}

	/**
	 * Get results from calls to the MovieDB API
	 * ==========================================
	 **/
	private static HashSet<?> getAllResults(String url, String type) {
		String urlComplete = createUrl(url, Utils.API_KEY, Utils.LANGUAGE_FOR_URL, Utils.NUMBER_PAGE_FOR_URL);
		firstThreadRun(urlComplete);
		runRemainingThreads(urlComplete, getTotalNumPages(type, runnables.get(0)));
		return getResultsFromPages(type);
	}

	private static int getTotalNumPages(String type, ApiClientRunnable runnable) {
		if (type == "movie") 
			return gson.fromJson(runnable.getResult(), ApiNewMovieResults.class).getTotalPages();
		else if (type == "show") 
			return gson.fromJson(runnable.getResult(), ApiNewShowResults.class).getTotalPages();
		else
			return 0;
	}

	private static void firstThreadRun(String url) {
		runnables = new ArrayList<>();
		ApiClientRunnable runnable = new ApiClientRunnable(createUrl(url, "1"));
		ExecutorService executor = MyExecutor.getNewExecutor();
		runnables.add(runnable);
		executor.execute(runnable);
		MyExecutor.terminateExecutor(executor);
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
		if (type == "movie")
			return getMovieResultsFromPages();
		else
			return getShowResultsFromPages();
	}
	
	private static HashSet<Movie> getMovieResultsFromPages(){
		HashSet<Movie> movies = new HashSet<>();
		runnables.stream().forEach(apiClientRunnable -> {
			String result = apiClientRunnable.getResult();
			ApiNewMovieResults apiNewMovieResults = gson.fromJson(result, ApiNewMovieResults.class);
			if (apiNewMovieResults != null && apiNewMovieResults.getResults() != null)
				movies.addAll(apiNewMovieResults.getResults());
		});
		return movies;
	}
	
	private static HashSet<Show> getShowResultsFromPages(){
		HashSet<Show> shows = new HashSet<>();
		runnables.stream().forEach(apiClientRunnable -> {
			String result = apiClientRunnable.getResult();
			ApiNewShowResults apiNewShowResults = gson.fromJson(result, ApiNewShowResults.class);
			if (apiNewShowResults != null && apiNewShowResults.getResults() != null)
				shows.addAll(apiNewShowResults.getResults());
		});
		return shows;
	}

	public static String createUrl(String... str) {
		StringBuilder sb = new StringBuilder();
		for (String string : str)
			sb.append(string);
		return sb.toString();
	}
}
