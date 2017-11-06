package com.gnt.movies.utilities;

import javax.persistence.NoResultException;

public class Logger {
	
	org.slf4j.Logger logger;
	
	public Logger(org.slf4j.Logger logger) {
		this.logger = logger;
	}

	public void error(String msg, NoResultException e) {
		logger.error(msg);
	}
}
