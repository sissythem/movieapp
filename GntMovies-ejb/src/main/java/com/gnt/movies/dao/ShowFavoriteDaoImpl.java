package com.gnt.movies.dao;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.ShowFavorite;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("ShowFavoriteDaoImpl")
public class ShowFavoriteDaoImpl extends AbstractDao implements ShowFavoriteDao {

	@Override
	public void createShowFavorite(DataProviderHolder dataProviderHolder, ShowFavorite showFavorite) {
		createEntity(dataProviderHolder.getEntityManager(), showFavorite);
	}

	@Override
	public void updateShowFavorite(DataProviderHolder dataProviderHolder, ShowFavorite showFavorite) {
		updateEntity(dataProviderHolder.getEntityManager(), showFavorite);
	}

	@Override
	public void deleteShowFavorite(DataProviderHolder dataProviderHolder, ShowFavorite showFavorite) {
		removeEntity(dataProviderHolder.getEntityManager(), showFavorite);
	}

	@Override
	public ShowFavorite findShowFavoriteById(DataProviderHolder dataProviderHolder, Integer id) {
		return (ShowFavorite)findSingleEntity(dataProviderHolder.getEntityManager(),Utils.SHOW_FAVORITE_FIND_BY_ID, "id", id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ShowFavorite> findShowFavoriteByUserId(DataProviderHolder dataProviderHolder, Integer userId){
		return (ArrayList<ShowFavorite>)dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_FAVORITE_FIND_BY_USER_ID).setParameter("userId", userId).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ShowFavorite> findShowFavoriteByShowId(DataProviderHolder dataProviderHolder, Integer showId){
		return (ArrayList<ShowFavorite>)dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_FAVORITE_FIND_BY_SHOW_ID)
				.setParameter("showId", showId).getResultList();
	}
}