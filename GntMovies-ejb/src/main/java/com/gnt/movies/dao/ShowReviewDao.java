package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.ShowReview;

public interface ShowReviewDao {
	void createShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview);
	void updateShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview);
	void deleteShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview);
	ShowReview findShowReviewById(DataProviderHolder dataProviderHolder, Integer id);
	List<ShowReview> findShowReviewByShowId(DataProviderHolder dataProviderHolder, Integer showId);
	List<ShowReview> findShowReviewByUserId(DataProviderHolder dataProviderHolder, Integer userId);
	List<ShowReview> findShowReviewByRating(DataProviderHolder dataProviderHolder, double rating);
}