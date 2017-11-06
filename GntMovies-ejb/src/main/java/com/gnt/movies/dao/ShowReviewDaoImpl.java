package com.gnt.movies.dao;

import javax.persistence.Query;

import com.gnt.movies.entities.ShowReview;

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
		ShowReview showReview = new ShowReview();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("ShowReview.findById");
		query.setParameter("id", id);
		showReview = (ShowReview)query.getSingleResult();
		return showReview;
	}

	@Override
	public ShowReview findByShowId(DataProviderHolder dataProviderHolder, Integer showId) {
		ShowReview showReview = new ShowReview();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("ShowReview.findByShowId");
		query.setParameter("showId", showId);
		showReview = (ShowReview)query.getSingleResult();
		return showReview;
	}

	@Override
	public ShowReview findByUserId(DataProviderHolder dataProviderHolder, Integer userId) {
		ShowReview showReview = new ShowReview();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("ShowReview.findByUserId");
		query.setParameter("userId", userId);
		showReview = (ShowReview)query.getSingleResult();
		return showReview;
	}
	
	@Override
	public ShowReview findByRating(DataProviderHolder dataProviderHolder, double rating) {
		ShowReview showReview = new ShowReview();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("ShowReview.findByRating");
		query.setParameter("rating", rating);
		showReview = (ShowReview)query.getSingleResult();
		return showReview;
	}
	
}