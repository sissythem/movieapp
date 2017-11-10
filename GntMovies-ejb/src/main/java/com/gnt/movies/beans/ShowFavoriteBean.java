package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.ShowFavoriteDao;
import com.gnt.movies.entities.Show;
import com.gnt.movies.entities.ShowFavorite;
import com.gnt.movies.entities.User;

/**
 * Session Bean implementation class FavoriteShowBean
 */
@Stateless
@LocalBean
public class ShowFavoriteBean implements DataProviderHolder{


	@PersistenceContext EntityManager em;

	@Inject
	@JpaDao
	@Named("ShowFavoriteDaoImpl")
	ShowFavoriteDao showFavoriteDao;

    public ShowFavoriteBean() {

    }

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
    public void addShowFavorite(User user, Show show) {
    	showFavoriteDao.createShowFavorite(this, new ShowFavorite(user, show));
    }
    
    public ArrayList<ShowFavorite> getAllShowFavoritesForUser(User user){
    	return (ArrayList<ShowFavorite>) showFavoriteDao.findShowFavoriteByUserId(this, user.getId());
    }
}
