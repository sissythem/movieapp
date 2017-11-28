package com.gnt.movies.dao;

import java.util.List;

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
	public List<Movie> getAll(DataProviderHolder dataProviderHolder) {
		
		return getResultsFromNamedQuery(dataProviderHolder.getEntityManager(),Utils.MOVIE_FIND_ALL);
	}

	@Override
	public Movie findMovieById(DataProviderHolder dataProviderHolder, Integer id) {
		return (Movie)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.MOVIE_FIND_BY_ID, "id", id);
	}

	@Override
	public Movie findMovieByTitle(DataProviderHolder dataProviderHolder, String title) {
		return (Movie)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.MOVIE_FIND_BY_TITLE, "title", title);
	}

	@Override
	public Movie findMovieByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		return (Movie)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.MOVIE_FIND_BY_IDTMDB, "idTmdb", idTmdb);
	}

}
