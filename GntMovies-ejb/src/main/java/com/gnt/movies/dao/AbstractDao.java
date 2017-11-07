package com.gnt.movies.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

public abstract class AbstractDao {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);
		
	public void createEntity(EntityManager em, Object object) {
		em.persist(object);
	}
	
	public void updateEntity(EntityManager em, Object object) {
		em.merge(object);		
	}
	
	public void removeEntity(EntityManager em, Object object) {
		em.remove(object);		
	}
	
	public static Object findSingleEntity(DataProviderHolder dataProviderHolder, String param, String valueParam, String namedQuery) {
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(namedQuery);
		query.setParameter(param, valueParam);
		return query.getSingleResult(); 
	}
	
//	public DbEntity createEntityAndReturn(EntityManager em, DbEntity dbEntity) {
//		em.persist(dbEntity);
//		return dbEntity;
//	}
//	
//	public DbEntity updateEntityAndReturn(EntityManager em, DbEntity dbEntity) {
//		return em.merge(dbEntity);
//	}
	
	public Object getSingleResult(EntityManager em, String query, Object... parameters) {
		Object object = null;
		try {
			object = getSingleResultFromNamedQueryWithParameters(em, query, parameters);
		} catch(NoResultException e) {
			logger.error("No result while fetching query: " + query + " with parameters " + parameters, e);
			object = null;
		}
		
        return object;
	}
//	
//	public Object getSingleResultNative(EntityManager em, String query, Object... parameters) {
//		
//		Object object = null;
//		try {
//			object = getSingleResultFromNativeQueryWithParameters(em, query, parameters);
//		} catch(NoResultException e) {
//			logger.error("No result while fetching query: " + query + " with parameters " + parameters, e);
//			object = null;
//		}
//		
//        return object;
//	}
//
	private Object getSingleResultFromNamedQueryWithParameters(EntityManager em, String query, Object... parameters) {
		
		Query q = em.createNamedQuery(query);
		fillQueryWitParameters(q, parameters);
		
		return q.getSingleResult();
	}
//	
//	private Object getSingleResultFromNativeQueryWithParameters(EntityManager em, String query, Object... parameters) {
//		
//		Query q = em.createNativeQuery(query);
//		fillQueryWitParameters(q, parameters);
//		
//		return q.getSingleResult();
//	}
//	
	private void fillQueryWitParameters(Query q, Object... parameters) {
		int i = 1;
		for (Object obj: parameters)
			q.setParameter(i++, obj);
	}

}
