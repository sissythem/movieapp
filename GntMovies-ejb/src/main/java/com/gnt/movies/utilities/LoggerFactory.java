package com.gnt.movies.utilities;

public class LoggerFactory {
	
	public static Logger getLogger(Class<?> clazz) {
		return new Logger(org.apache.log4j.Logger.getLogger(clazz));
	}
	
	public static Logger getLogger(String value) {
		return new Logger(org.apache.log4j.Logger.getLogger(value));
	}
}
