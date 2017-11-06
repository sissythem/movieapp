package com.gnt.movies.dao;

import com.gnt.movies.entities.MovieFavorite;

public interface MovieFavoriteDao {
	void createMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite);
	void updateMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite);
	void deleteMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite);
	MovieFavorite findMovieFavoriteById(DataProviderHolder dataProviderHolder, Integer id);
}
