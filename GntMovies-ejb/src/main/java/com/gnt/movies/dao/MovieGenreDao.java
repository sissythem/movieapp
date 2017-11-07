package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.MovieGenre;

public interface MovieGenreDao {
	void createMovieGenre(DataProviderHolder dataProviderHolder, MovieGenre movieGenre);
	void updateMovieGenre(DataProviderHolder dataProviderHolder, MovieGenre movieGenre);
	void deleteMovieGenre(DataProviderHolder dataProviderHolder, MovieGenre movieGenre);
	MovieGenre findMovieGenreById(DataProviderHolder dataProviderHolder, Integer id);
	List<MovieGenre>findMovieGenreByGenreId(DataProviderHolder dataProviderHolder, Integer genreId);
	List<MovieGenre>findMovieGenreByMovieId(DataProviderHolder dataProviderHolder, Integer movieId);
}
