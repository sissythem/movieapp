package com.gnt.movies.dao;

import com.gnt.movies.entities.MovieGenre;

public class MovieGenreDaoImpl extends AbstractDao implements MovieGenreDao {

	@Override
	public void createMovieGenre(DataProviderHolder dataProviderHolder, MovieGenre movieGenre) {
		createEntity(dataProviderHolder.getEntityManager(), movieGenre);
	}

	@Override
	public void updateMovieGenre(DataProviderHolder dataProviderHolder, MovieGenre movieGenre) {
		updateEntity(dataProviderHolder.getEntityManager(), movieGenre);
	}

	@Override
	public void deleteMovieGenre(DataProviderHolder dataProviderHolder, MovieGenre movieGenre) {
		removeEntity(dataProviderHolder.getEntityManager(), movieGenre);
	}

	@Override
	public MovieGenre findMovieGenreById(DataProviderHolder dataProviderHolder, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
