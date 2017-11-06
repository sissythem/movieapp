package com.gnt.movies.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.NowPlayingMovieDao;

/**
 * Session Bean implementation class NowPlayingMovieBean
 */
@Stateless
@LocalBean
public class NowPlayingMovieBean implements DataProviderHolder{
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("NowPlayingMovieDaoImpl")
	NowPlayingMovieDao nowPlayingMovieDao;
	
    public NowPlayingMovieBean() {
    }
    
    @Override
	public EntityManager getEntityManager() {
		return em;
	}
    public boolean addNowPlayingMovie() {
    	return true;
    }

}
