package com.gnt.movies.dao;

import java.util.ArrayList;

import com.gnt.movies.entities.MovieReview;

public interface MovieReviewDao {
	void createMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview);
	void updateMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview);
	void deleteMovieReview(DataProviderHolder dataProviderHolder, MovieReview movieReview);
	MovieReview findMovieReviewById(DataProviderHolder dataProviderHolder, Integer id);
	ArrayList<MovieReview>findMovieReviewByUserId(DataProviderHolder dataProviderHolder, Integer userId);
	ArrayList<MovieReview>findMovieReviewByMovieId(DataProviderHolder dataProviderHolder, Integer movieId);
	MovieReview findMovieReview(DataProviderHolder dataProviderHolder, Integer movieId, Integer userId);
}
