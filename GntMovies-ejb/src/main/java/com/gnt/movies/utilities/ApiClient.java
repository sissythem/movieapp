package com.gnt.movies.utilities;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

public class ApiClient {
	private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
	private static AtomicLong counter = new AtomicLong(0);
	private static Timer timer;
	private static OkHttpClient client = new OkHttpClient();

	private static ConcurrentHashMap<Long, ApiEntry> map;

	public static synchronized void init() {
		map = new ConcurrentHashMap<>();
	}

	public static synchronized void setTimer() {
		if (timer == null) {
			logger.info("timer created");

			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					logger.info("Total requests:" + counter.get() + ", temporal active requests:" + map.size());
				}
			}, 0, 2 * 1000);
		}
	}

	public static synchronized void unsetTimer() {
		timer.cancel();
		counter.set(0l);
		timer = null;
	}

	public static String getResultFromTMDB(String url) {
		checkNumCalls(url);
		ApiEntry entry = null;
		try {
			Long l = counter.getAndIncrement();
			entry = new ApiEntry(l);
			map.put(l, entry);
			Response response = client.newCall(buildRequest(url)).execute();
			entry.setTimer();
			logger.info(Thread.currentThread().getId() + ":" + response.code());
			checkForReachingCallsLimit(response, url);
			return response.body().string();
		} catch (IOException e) {
			logger.error("Couldn't get the response", e);
			entry.setTimer();
			return getResultFromTMDB(url);
		}
	}

	private static synchronized void checkNumCalls(String url) {
		if (map.size() >= 38) {
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

	private static Request buildRequest(String url) {
		Builder b = new Builder();
		b.readTimeout(15, TimeUnit.SECONDS);
		client = b.build();
		return new Request.Builder().url(url).get().build();
	}

	private static void checkForReachingCallsLimit(Response response, String url) {
		if (response.code() == 429) {
			try {
				logger.info(Thread.currentThread().getId() + ":sleeping");
				Thread.sleep(1500);
				logger.info(Thread.currentThread().getId() + ":will try again");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			getResultFromTMDB(url);
		}
	}

	public static ConcurrentHashMap<Long, ApiEntry> getMap() {
		return map;
	}

	public static void notifyCounter() {
		synchronized (counter) {
			counter.notify();
		}
	}

}
