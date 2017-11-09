package com.gnt.movies.utilities;

import io.jsonwebtoken.SignatureException;

import javax.persistence.NoResultException;

public class Logger {
	
	org.slf4j.Logger logger;
	
	public Logger(org.slf4j.Logger logger) {
		this.logger = logger;
	}

	public void error(String msg, NoResultException e) {
		logger.error(msg);
	}
	
	public void error(String msg, Exception e) {
		logger.error(msg);
	}
	
	public void error(String msg, SignatureException e) {
		logger.error(msg);
	}
	
	public void info(String msg) {
		logger.info(msg);
	}
}
