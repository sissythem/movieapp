package com.gnt.movies.utilities;

import java.util.List;

import javax.persistence.Query;

import com.gnt.movies.dao.DataProviderHolder;

public class Utils {
	/** User named queries**/
	public static String USER_FIND_BY_ID = "User.findById";
	public static String USER_FIND_BY_USERNAME = "User.findByUsername";
	public static String USER_FIND_BY_EMAIL = "User.findByEmail";
	public static String USER_FIND_BY_PASSWORD ="User.findByPassword";
	public static String USER_FIND_BY_AGE = "User.findByAge";
	
	/** Show named queries**/
	public static String SHOW_FIND_BY_ID = "Show.findById";
	
	/** Movie named queries**/
	public static String MOVIE_FIND_BY_ID = "Movie.findById";
	
	public static Object findSingleEntity(DataProviderHolder dataProviderHolder, String param, String valueParam, String namedQuery) {
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(namedQuery);
		query.setParameter(param, valueParam);
		return query.getSingleResult(); 
	}
	
	public static List<Object> findListEntities(DataProviderHolder dataProviderHolder, String param, String valueParam, String namedQuery){
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(namedQuery);
		query.setParameter(param, valueParam);
		return query.getResultList();
	}
}
