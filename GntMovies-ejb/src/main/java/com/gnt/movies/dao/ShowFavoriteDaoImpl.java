package com.gnt.movies.dao;

import java.util.List;

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
		return (ShowFavorite)getSingleResult(dataProviderHolder.getEntityManager(),Utils.SHOW_FAVORITE_FIND_BY_ID, id);
	}
	
	@Override
	public List<Object> findByUserId(DataProviderHolder dataProviderHolder, Integer userId){
		return (List<Object>)findListEntities(dataProviderHolder, "userId", userId.toString(), Utils.SHOW_FAVORITE_FIND_BY_USER_ID);
	}
	
	@Override
	public List<Object> findByShowId(DataProviderHolder dataProviderHolder, Integer showId){
		return (List<Object>)findListEntities(dataProviderHolder, "showId", showId.toString(), Utils.SHOW_FAVORITE_FIND_BY_SHOW_ID);
	}

}