package com.gnt.movies.dao;

import com.gnt.movies.entities.MovieFavorite;

public class MovieFavoriteDaoImpl extends AbstractDao implements MovieFavoriteDao {

	@Override
	public void createMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite) {
		createEntity(dataProviderHolder.getEntityManager(), movieFavorite);
	}

	@Override
	public void updateMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite) {
		updateEntity(dataProviderHolder.getEntityManager(), movieFavorite);
	}

	@Override
	public void deleteMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite) {
		removeEntity(dataProviderHolder.getEntityManager(), movieFavorite);
	}

	@Override
	public MovieFavorite findMovieFavoriteById(DataProviderHolder dataProviderHolder, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
