package com.gnt.movies.utilities;

import java.util.Timer;
import java.util.TimerTask;

public class ApiEntry {
	private static final Logger logger = LoggerFactory.getLogger(ApiEntry.class);
	private Long l;
	private static Timer timer;

	public ApiEntry(Long l) {
		super();
		this.l = l;
	}

	public void setTimer() {

		logger.info("timer created");
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				ApiClient.getMap().remove(l);
//				logger.info("ApiEntry timer:" + l + " map size:" + ApiClient.getMap().size());
				ApiClient.notifyCounter();
			}
		}, 10 * 1000);
	}
}

