package com.gnt.movies.dao;

import com.gnt.movies.entities.Movie;

public class MovieDaoImpl extends AbstractDao implements MovieDao {

	@Override
	public void createMovie(DataProviderHolder dataProviderHolder, Movie movie) {
		createEntity(dataProviderHolder.getEntityManager(), movie);
	}

	@Override
	public void updateMovie(DataProviderHolder dataProviderHolder, Movie movie) {
		updateEntity(dataProviderHolder.getEntityManager(), movie);
	}

	@Override
	public void deleteMovie(DataProviderHolder dataProviderHolder, Movie movie) {
		removeEntity(dataProviderHolder.getEntityManager(), movie);
	}

	@Override
	public Movie findMovieById(DataProviderHolder dataProviderHolder, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
