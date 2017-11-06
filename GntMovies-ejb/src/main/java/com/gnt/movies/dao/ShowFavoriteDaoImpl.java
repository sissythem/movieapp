package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.gnt.movies.entities.ShowFavorite;

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
		ShowFavorite showFavorite = new ShowFavorite();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("ShowFavorite.findById");
		query.setParameter("id", id);
		showFavorite = (ShowFavorite)query.getSingleResult();
		return showFavorite;
	}
	
	@Override
	public List<ShowFavorite> findByUserId(DataProviderHolder dataProviderHolder, Integer userId){
		List<ShowFavorite> showFavorites = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("ShowFavorite.findByUserId");
		query.setParameter("userId", userId);
		showFavorites = query.getResultList();
		return showFavorites;
	}
	
	@Override
	public List<ShowFavorite> findByShowId(DataProviderHolder dataProviderHolder, Integer showId){
		List<ShowFavorite> showFavorites = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("ShowFavorite.findByShowId");
		query.setParameter("showId", showId);
		showFavorites = query.getResultList();
		return showFavorites;
	}

}