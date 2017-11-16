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
	private static OkHttpClient client;
	public static synchronized void setTimer() {
		if (timer == null) {
			System.out.println("timer created");
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
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
		checkNumCalls(url);

		Response response;
		try {
			response = client.newCall(buildRequest(url)).execute();
			logger.info(Thread.currentThread().getId() + ":" + response.code());
			checkForReachingCallsLimit(response, url);
			return response.body().string();
		} catch (IOException e) {
			logger.error("Couldn't get the responce", e);
			return getResultFromTMDB(url);
		}
	}
	private static void checkNumCalls(String url) {
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
			getResultFromTMDB(url);
		}
	}
	
	private static Request buildRequest (String url) {
		Builder b = new Builder();
		b.readTimeout(15, TimeUnit.SECONDS);
		client = b.build();
		return new Request.Builder().url(url).get().build();
	}
	
	private static void checkForReachingCallsLimit(Response response, String url) {
		if (response.code() == 429) {
			try {
				System.out.println(Thread.currentThread().getId() + ":sleeping");
				Thread.sleep(1500);
				System.out.println(Thread.currentThread().getId() + ":will try again");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			getResultFromTMDB(url);
		}
	}
}
