package com.gnt.movies.utilities;

public class Logger {
	
	org.apache.log4j.Logger logger;
	
	public Logger(org.apache.log4j.Logger logger) {
		this.logger = logger;
	}
	
	public void error(String msg, Exception e) {
		logger.error(msg);
	}
	
	public void info(String msg) {
		logger.info(msg);
	}
	
	public void error(String msg) {
		logger.error(msg);
	}

	public void error(String msg, Throwable t) {
		logger.error(msg, t);
	}
}