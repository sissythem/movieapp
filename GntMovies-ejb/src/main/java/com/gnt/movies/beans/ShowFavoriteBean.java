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
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
public class ShowFavoriteBean implements DataProviderHolder{
	private static final Logger logger = LoggerFactory.getLogger(ShowFavoriteBean.class);
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
		logger.info("Adding show favorite in the database for show: " + show.getId() + " and for user: " + user.getId());
    	showFavoriteDao.createShowFavorite(this, new ShowFavorite(user, show));
    }
    
    public ArrayList<ShowFavorite> getAllShowFavoritesForUser(User user){
    	return (ArrayList<ShowFavorite>) showFavoriteDao.findShowFavoriteByUserId(this, user.getId());
    }
    
    public void removeShowFavorite(User user, Show show) {
    	showFavoriteDao.deleteShowFavorite(this, new ShowFavorite(user, show));
    }
}
