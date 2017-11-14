package com.gnt.movies.dao;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.MovieGenre;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("MovieGenreDaoImpl")
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
		return (MovieGenre) findSingleEntity(dataProviderHolder.getEntityManager(), Utils.MOVIE_GENRE_FIND_BY_ID, "id", id);
	}

	@Override
	public ArrayList<MovieGenre> findMovieGenreByGenreId(DataProviderHolder dataProviderHolder, Integer genreId) {
		return (ArrayList<MovieGenre>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_GENRE_FIND_BY_GENRE_ID)
				.setParameter("genreId", genreId).getResultList();
	}

	@Override
	public ArrayList<MovieGenre> findMovieGenreByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		return (ArrayList<MovieGenre>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_GENRE_FIND_BY_MOVIE_ID)
				.setParameter("movieId", movieId).getResultList();
	}

}
