package com.gnt.movies.dao;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.Movie;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("MovieDaoImpl")
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
		return (Movie)getSingleResult(dataProviderHolder.getEntityManager(), Utils.MOVIE_FIND_BY_ID, id);
	}

	@Override
	public Movie findMovieByTitle(DataProviderHolder dataProviderHolder, String title) {
		return (Movie)getSingleResult(dataProviderHolder.getEntityManager(), Utils.MOVIE_FIND_BY_TITLE, title);
	}

	@Override
	public Movie findMovieByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		return (Movie)getSingleResult(dataProviderHolder.getEntityManager(), Utils.MOVIE_FIND_BY_IDTMDB, idTmdb);
	}

}
