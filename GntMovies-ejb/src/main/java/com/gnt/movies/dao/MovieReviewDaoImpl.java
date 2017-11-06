package com.gnt.movies.dao;

import com.gnt.movies.entities.MovieReview;

public class MovieReviewDaoImpl extends AbstractDao implements MovieReviewDao {

	@Override
	public void createMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview) {
		createEntity(dataProviderHolder.getEntityManager(), movieReview);
	}

	@Override
	public void updateMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview) {
		updateEntity(dataProviderHolder.getEntityManager(), movieReview);
	}

	@Override
	public void deleteMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview) {
		removeEntity(dataProviderHolder.getEntityManager(), movieReview);
	}

	@Override
	public MovieReview findMovieReviewById(DataProviderHolder dataProviderHolder, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
