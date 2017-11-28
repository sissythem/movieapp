package com.gnt.movies.utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyExecutor {
	private static final Logger logger = LoggerFactory.getLogger(MyExecutor.class);

	public static ExecutorService getNewExecutor() {
		return Executors.newFixedThreadPool(40);
	}
	
	
	public static void terminateExecutor(ExecutorService executor) {
		executor.shutdown();
		try 
		{
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e1) {
			logger.info("GetUpcomingMovies: Executor interrupted ");
			e1.printStackTrace();
		}
		logger.info("Finished all Executors threads");
	}
	
	
	
	
	
}
