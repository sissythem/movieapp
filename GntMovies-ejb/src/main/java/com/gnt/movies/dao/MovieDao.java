package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.Movie;

public interface MovieDao {
	void createMovie(DataProviderHolder dataProviderHolder, Movie movie);
	void updateMovie(DataProviderHolder dataProviderHolder, Movie movie);
	void deleteMovie(DataProviderHolder dataProviderHolder, Movie movie);
	Movie findMovieById(DataProviderHolder dataProviderHolder, Integer id);
	Movie findMovieByTitle(DataProviderHolder dataProviderHolder, String title);
	List<Movie> findMovieByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
}
