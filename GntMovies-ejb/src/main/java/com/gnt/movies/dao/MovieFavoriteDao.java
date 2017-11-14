package com.gnt.movies.dao;

import java.util.ArrayList;

import com.gnt.movies.entities.MovieFavorite;

public interface MovieFavoriteDao {
	void createMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite);
	void updateMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite);
	void deleteMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite);
	MovieFavorite findMovieFavoriteById(DataProviderHolder dataProviderHolder, Integer id);
	ArrayList<MovieFavorite>findMovieFavoriteByUserId(DataProviderHolder dataProviderHolder, Integer userId);
	ArrayList<MovieFavorite>findMovieFavoriteByMovieId(DataProviderHolder dataProviderHolder, Integer movieId);
	ArrayList<MovieFavorite>findAll(DataProviderHolder dataProviderHolder);
}
