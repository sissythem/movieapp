package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.MovieImage;

public interface MovieImageDao {
	void createMovieImage(DataProviderHolder dataProviderHolder, MovieImage movieImage);
	void updateMovieImage(DataProviderHolder dataProviderHolder, MovieImage movieImage);
	void deleteMovieImage(DataProviderHolder dataProviderHolder, MovieImage movieImage);
	MovieImage findMovieImageById(DataProviderHolder dataProviderHolder, Integer id);
	List<MovieImage>findMovieGenreByMovieId(DataProviderHolder dataProviderHolder, Integer movieId);
}
