package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.MovieReview;

public interface MovieReviewDao {
	void createMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview);
	void updateMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview);
	void deleteMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview);
	MovieReview findMovieReviewById(DataProviderHolder dataProviderHolder, Integer id);
	List<MovieReview>findMovieReviewByUserId(DataProviderHolder dataProviderHolder, Integer userId);
	List<MovieReview>findMovieReviewByMovieId(DataProviderHolder dataProviderHolder, Integer movieId);
}
