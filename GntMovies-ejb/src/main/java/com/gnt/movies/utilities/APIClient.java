package com.gnt.movies.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.gnt.movies.beans.SchedulerBean;
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
	private static final Logger logger = LoggerFactory.getLogger(APIClient.class);
	private static AtomicInteger counter = new AtomicInteger(0);
	private static Timer timer;

	public static synchronized void setTimer() {
		if (timer == null) {
			System.out.println("timer created");
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					// Your database code here
					System.out.println("Hi from Apiclient timer!!!");
					synchronized (counter) {
						counter.set(0);
						counter.notifyAll();
					}
				}
			}, 0, 15*1000);
		}
	}

	public static synchronized void unsetTimer() {
		timer = null;
	}

	public static String getResultFromTMDB(String url) {
		if (counter.incrementAndGet() >= 20) {
			try {
				logger.info(Thread.currentThread().getId() + ":Will wait before making a new request.");
				synchronized (counter) {
					counter.wait();
					logger.info(Thread.currentThread().getId() + ":woken");
				}
			} catch (InterruptedException e) {
				logger.info(Thread.currentThread().getId() + ":interrupted");
			}
			logger.info(Thread.currentThread().getId() + ":making a new request.");
//			counter.decrementAndGet();
			return getResultFromTMDB(url);

		}

		// OkHttpClient client = new OkHttpClient();
		Builder b = new Builder();
		b.readTimeout(15, TimeUnit.SECONDS);
		OkHttpClient client = b.build();
		Request request = new Request.Builder().url(url).get().build();
		Response response;
		try {
			response = client.newCall(request).execute();

			System.out.println(Thread.currentThread().getId() + ":" + response.code());
			if (response.code() == 429) {
				try {
					System.out.println(Thread.currentThread().getId() + ":sleeping");
					Thread.sleep(1500);
					System.out.println(Thread.currentThread().getId() + ":will try again");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return getResultFromTMDB(url);
			}
			return response.body().string();
		} catch (IOException e) {
			logger.error("Couldn't get the responce", e);
			return getResultFromTMDB(url);
		}
	}

	/** Get new Movies and Shows from API **/
	/*
	 * public ArrayList<ApiNewMovie> getUpcomingMoviesFromAPI() { String command =
	 * Utils.UPCOMING_MOVIES_URL; return getMovieListFromApi(command); }
	 * 
	 * public ArrayList<ApiNewMovie> getNowPlayingMoviesFromAPI() { String command =
	 * Utils.NOW_PLAYING_MOVIES_URL; return getMovieListFromApi(command); }
	 * 
	 * public ArrayList<ApiNewShow> getAir2dayShowsFromAPI() { String command =
	 * Utils.AIR2DAY_SHOWS_URL; return getShowListFromApi(command); }
	 * 
	 * public ArrayList<ApiNewShow> getOnTheAirShowsFromAPI() { String command =
	 * Utils.ON_THE_AIR_SHOWS_URL; return getShowListFromApi(command); }
	 * 
	 * public ApiNewMovieResults getPagesForMovies(int page, String urlApi) {
	 * StringBuilder url = new
	 * StringBuilder(urlApi).append(Utils.API_KEY).append(Utils.LANGUAGE_FOR_URL)
	 * .append(Utils.NUMBER_PAGE_FOR_URL).append(Integer.toString(page)); String
	 * resultJson = getResultFromTMDB(url.toString()); ApiNewMovieResults
	 * upcomingNowPlayingMovieResults = new Gson().fromJson(resultJson,
	 * ApiNewMovieResults.class); return upcomingNowPlayingMovieResults; }
	 * 
	 * private ArrayList<ApiNewMovie> getMovieListFromApi(String urlApi) {
	 * ArrayList<ApiNewMovie> movies = new ArrayList<>(); ApiNewMovieResults
	 * resultNowPlaying = getPagesForMovies(1, urlApi);
	 * movies.addAll(resultNowPlaying.getResults()); int i; for (i = 0; i <
	 * resultNowPlaying.getTotalPages(); i++) {
	 * 
	 * } for (int page = 2; page <= resultNowPlaying.getTotalPages(); page++) {
	 * resultNowPlaying = getPagesForMovies(page, urlApi);
	 * movies.addAll(resultNowPlaying.getResults()); } return movies; }
	 * 
	 * public ApiNewShowResults getPagesForShows(int page, String typeOfShowUrl) {
	 * StringBuilder url = new
	 * StringBuilder(typeOfShowUrl).append(Utils.API_KEY).append(Utils.
	 * LANGUAGE_FOR_URL)
	 * .append(Utils.NUMBER_PAGE_FOR_URL).append(Integer.toString(page)); String
	 * resultJson = getResultFromTMDB(url.toString()); ApiNewShowResults showResults
	 * = new Gson().fromJson(resultJson, ApiNewShowResults.class); return
	 * showResults; }
	 * 
	 * private ArrayList<ApiNewShow> getShowListFromApi(String command) {
	 * ArrayList<ApiNewShow> shows = new ArrayList<>(); ApiNewShowResults
	 * showResults = getPagesForShows(1, command);
	 * shows.addAll(showResults.getResults()); for (int page = 2; page <=
	 * showResults.getTotalPages(); page++) { showResults = getPagesForShows(page,
	 * command); shows.addAll(showResults.getResults()); } return shows; }
	 */
	public static ApiMovieDetails getMovieDetailsFromAPI(int id) {
		StringBuilder url = new StringBuilder(Utils.GENERAL_MOVIE_URL).append(Integer.toString(id))
				.append(Utils.API_KEY).append(Utils.IMAGES_URL).append(Utils.CREW_CAST_URL);
		String result = getResultFromTMDB(url.toString());
		ApiMovieDetails movieDetails = new Gson().fromJson(result, ApiMovieDetails.class);
		return movieDetails;
	}

	public static ApiShowDetails getShowDetailsFromAPI(int id) {
		StringBuilder url = new StringBuilder(Utils.GENERAL_SHOW_URL).append(Integer.toString(id)).append(Utils.API_KEY)
				.append(Utils.IMAGES_URL).append(Utils.CREW_CAST_URL);

		String result = getResultFromTMDB(url.toString());
		ApiShowDetails showDetails = new Gson().fromJson(result, ApiShowDetails.class);
		return showDetails;
	}

	/**
	 * =====================================================================================================================
	 * Using threads for getting all pages from API
	 **/

	public static ArrayList<ApiNewMovie> getUpcomingMovies() {

		StringBuilder sb = new StringBuilder(Utils.UPCOMING_MOVIES_URL).append(Utils.API_KEY)
				.append(Utils.LANGUAGE_FOR_URL).append(Utils.NUMBER_PAGE_FOR_URL);

		return (ArrayList<ApiNewMovie>) getPages(sb.toString(), "movie");
	}

	public static ArrayList<ApiNewMovie> getNowPlayingMovies() {

		StringBuilder sb = new StringBuilder(Utils.NOW_PLAYING_MOVIES_URL).append(Utils.API_KEY)
				.append(Utils.LANGUAGE_FOR_URL).append(Utils.NUMBER_PAGE_FOR_URL);

		return (ArrayList<ApiNewMovie>) getPages(sb.toString(), "movie");
	}

	public static ArrayList<ApiNewShow> getOnTheAirShows() {

		StringBuilder sb = new StringBuilder(Utils.ON_THE_AIR_SHOWS_URL).append(Utils.API_KEY)
				.append(Utils.LANGUAGE_FOR_URL).append(Utils.NUMBER_PAGE_FOR_URL);

		return (ArrayList<ApiNewShow>) getPages(sb.toString(), "show");
	}

	public static ArrayList<ApiNewShow> getAir2dayShows() {

		StringBuilder sb = new StringBuilder(Utils.AIR2DAY_SHOWS_URL).append(Utils.API_KEY)
				.append(Utils.LANGUAGE_FOR_URL).append(Utils.NUMBER_PAGE_FOR_URL);

		return (ArrayList<ApiNewShow>) getPages(sb.toString(), "show");
	}

	private static ArrayList<?> getPages(String url, String type) {
		ArrayList<APIClientRunnable> runnables = new ArrayList<>();
		ArrayList<Thread> threads = new ArrayList<>();

		StringBuilder sb = new StringBuilder(url).append("1");
		APIClientRunnable runnable = new APIClientRunnable(sb.toString());

		Thread thread = new Thread(runnable);
		runnables.add(runnable);
		threads.add(thread);

		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int pages = 0;

		if (type == "movie") {
			pages = new Gson().fromJson(runnable.getResult(), ApiNewMovieResults.class).getTotalPages();
		} else if (type == "show") {
			pages = new Gson().fromJson(runnable.getResult(), ApiNewShowResults.class).getTotalPages();
		}

		for (int page = 2; page <= pages; page++) {
			sb = new StringBuilder(url).append(page);
			runnable = new APIClientRunnable(sb.toString());
			runnables.add(runnable);
			thread = new Thread(runnable);
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

		if (type == "movie") {
			ArrayList<ApiNewMovie> movies = new ArrayList<>();
			// for (int page = 1; page < pages; page++) {
			//
			// String s = runnables.get(page).getResult();
			// ArrayList<ApiNewMovie> l =new Gson().fromJson(s,
			// ApiNewMovieResults.class).getResults();
			// movies.addAll(l);
			// }
			for (APIClientRunnable apiClientRunnable : runnables) {
				ArrayList<ApiNewMovie> l = new Gson().fromJson(apiClientRunnable.getResult(), ApiNewMovieResults.class)
						.getResults();
				movies.addAll(l);
			}

			return movies;
		} else {
			ArrayList<ApiNewShow> shows = new ArrayList<>();
			for (int page = 1; page < pages; page++) {
				shows.addAll(
						new Gson().fromJson(runnables.get(page).getResult(), ApiNewShowResults.class).getResults());
			}
			return shows;
		}
	}
}
