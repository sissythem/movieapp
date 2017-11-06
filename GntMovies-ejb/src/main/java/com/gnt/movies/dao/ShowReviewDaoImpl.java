package com.gnt.movies.dao;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.ShowReview;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("ShowReviewDaoImpl")
public class ShowReviewDaoImpl extends AbstractDao implements ShowReviewDao {

	@Override
	public void createShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview) {
		createEntity(dataProviderHolder.getEntityManager(), showReview);
	}

	@Override
	public void updateShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview) {
		updateEntity(dataProviderHolder.getEntityManager(), showReview);
	}

	@Override
	public void deleteShowReview(DataProviderHolder dataProviderHolder, ShowReview showReview) {
		removeEntity(dataProviderHolder.getEntityManager(), showReview);
	}

	@Override
	public ShowReview findShowReviewById(DataProviderHolder dataProviderHolder, Integer id) {
		return (ShowReview)getSingleResult(dataProviderHolder.getEntityManager(), Utils.SHOW_REVIEW_FIND_BY_ID, id);
	}

	@Override
	public ShowReview findByShowId(DataProviderHolder dataProviderHolder, Integer showId) {
		return (ShowReview)getSingleResult(dataProviderHolder.getEntityManager(), Utils.SHOW_REVIEW_FIND_BY_SHOW_ID, showId);
	}

	@Override
	public ShowReview findByUserId(DataProviderHolder dataProviderHolder, Integer userId) {
		return (ShowReview)getSingleResult(dataProviderHolder.getEntityManager(), Utils.SHOW_REVIEW_FIND_BY_USER_ID, userId);
	}
	
	@Override
	public ShowReview findByRating(DataProviderHolder dataProviderHolder, double rating) {
		return (ShowReview)getSingleResult(dataProviderHolder.getEntityManager(), Utils.SHOW_REVIEW_FIND_BY_RATING, rating);
	}
	
}