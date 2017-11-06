package com.gnt.movies.dao;

import com.gnt.movies.entities.MovieReview;

public interface MovieReviewDao {
	void createMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview);
	void updateMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview);
	void deleteMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview);
	MovieReview findMovieReviewById(DataProviderHolder dataProviderHolder, Integer id);
}
