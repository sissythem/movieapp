package com.gnt.movies.dao;

import com.gnt.movies.entities.MovieGenre;

public interface MovieGenreDao {
	void createMovieGenre(DataProviderHolder dataProviderHolder, MovieGenre movieGenre);
	void updateMovieGenre(DataProviderHolder dataProviderHolder, MovieGenre movieGenre);
	void deleteMovieGenre(DataProviderHolder dataProviderHolder, MovieGenre movieGenre);
	MovieGenre findMovieGenreById(DataProviderHolder dataProviderHolder, Integer id);
}
