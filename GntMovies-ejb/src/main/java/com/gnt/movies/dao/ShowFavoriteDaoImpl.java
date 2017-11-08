package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.Query;

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
	
	@Override
	public List<ShowFavorite> findByUserId(DataProviderHolder dataProviderHolder, Integer userId){
		List<ShowFavorite> showFavories = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_FAVORITE_FIND_BY_USER_ID);
		query.setParameter("userId", userId);
		showFavories = query.getResultList();
		return showFavories;
	}
	
	@Override
	public List<ShowFavorite> findByShowId(DataProviderHolder dataProviderHolder, Integer showId){
		List<ShowFavorite> showFavories = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_FAVORITE_FIND_BY_SHOW_ID);
		query.setParameter("showId", showId);
		showFavories = query.getResultList();
		return showFavories;
	}

}