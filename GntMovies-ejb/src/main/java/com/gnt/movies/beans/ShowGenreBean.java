package com.gnt.movies.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.ShowGenreDao;
import com.gnt.movies.entities.ShowGenre;

/**
 * Session Bean implementation class ShowGenreBean
 */
@Stateless
@LocalBean
public class ShowGenreBean implements DataProviderHolder {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("ShowGenreDaoImpl")
	ShowGenreDao showGenreDao;
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
    public ShowGenreBean() {
        
    }

	public void addShowGenre(ShowGenre showGenre) {
		showGenreDao.createShowGenre(this, showGenre);
		
	}

}
