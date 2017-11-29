package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.dto.MovieListItemDto;
import com.gnt.movies.entities.Movie;

public interface MovieDao {
	void createMovie(DataProviderHolder dataProviderHolder, Movie movie);
	void updateMovie(DataProviderHolder dataProviderHolder, Movie movie);
	void deleteMovie(DataProviderHolder dataProviderHolder, Movie movie);
	List<MovieListItemDto> getAll(DataProviderHolder dataProviderHolder);
	
	Movie findMovieById(DataProviderHolder dataProviderHolder, Integer id);
	Movie findMovieByTitle(DataProviderHolder dataProviderHolder, String title);
	Movie findMovieByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
}
