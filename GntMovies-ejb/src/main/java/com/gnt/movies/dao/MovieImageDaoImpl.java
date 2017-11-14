package com.gnt.movies.dao;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.MovieImage;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("MovieImageDaoImpl")
public class MovieImageDaoImpl extends AbstractDao implements MovieImageDao {

	@Override
	public void createMovieImage(DataProviderHolder dataProviderHolder, MovieImage movieImage) {
		createEntity(dataProviderHolder.getEntityManager(), movieImage);
	}

	@Override
	public void updateMovieImage(DataProviderHolder dataProviderHolder, MovieImage movieImage) {
		updateEntity(dataProviderHolder.getEntityManager(), movieImage);
	}

	@Override
	public void deleteMovieImage(DataProviderHolder dataProviderHolder, MovieImage movieImage) {
		removeEntity(dataProviderHolder.getEntityManager(), movieImage);
	}

	@Override
	public MovieImage findMovieImageById(DataProviderHolder dataProviderHolder, Integer id) {
		return (MovieImage) findSingleEntity(dataProviderHolder.getEntityManager(), Utils.MOVIE_IMAGE_FIND_BY_ID, "id", id);
	}

	@Override
	public ArrayList<MovieImage> findMovieGenreByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		return (ArrayList<MovieImage>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_IMAGE_FIND_BY_MOVIE_ID)
				.setParameter("movieId", movieId).getResultList();
	}

}
