package com.gnt.movies.dao;

import com.gnt.movies.entities.ShowReview;

public interface ShowReviewDao {
	void createShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview);
	void updateShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview);
	void deleteShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview);
	ShowReview findShowReviewById(DataProviderHolder dataProviderHolder, Integer id);
	ShowReview findByShowId(DataProviderHolder dataProviderHolder, Integer showId);
	ShowReview findByUserId(DataProviderHolder dataProviderHolder, Integer userId);
	ShowReview findByRating(DataProviderHolder dataProviderHolder, double rating);
}