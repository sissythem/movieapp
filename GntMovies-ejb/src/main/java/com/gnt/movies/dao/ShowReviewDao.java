package com.gnt.movies.dao;

import java.util.ArrayList;

import com.gnt.movies.entities.ShowReview;

public interface ShowReviewDao {
	void createShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview);
	void updateShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview);
	void deleteShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview);
	ShowReview findShowReviewById(DataProviderHolder dataProviderHolder, Integer id);
	ArrayList<ShowReview> findShowReviewByShowId(DataProviderHolder dataProviderHolder, Integer showId);
	ArrayList<ShowReview> findShowReviewByUserId(DataProviderHolder dataProviderHolder, Integer userId);
	ArrayList<ShowReview> findShowReviewByRating(DataProviderHolder dataProviderHolder, double rating);
}