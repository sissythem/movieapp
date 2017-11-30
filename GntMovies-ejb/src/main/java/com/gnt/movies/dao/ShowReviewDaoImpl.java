package com.gnt.movies.dao;

import java.util.ArrayList;

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
		return (ShowReview)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.SHOW_REVIEW_FIND_BY_ID, "id", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ShowReview> findShowReviewByShowId(DataProviderHolder dataProviderHolder, Integer showId) {
		return (ArrayList<ShowReview>)dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_REVIEW_FIND_BY_SHOW_ID).setParameter("showId", showId).getResultList() ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ShowReview> findShowReviewByUserId(DataProviderHolder dataProviderHolder, Integer userId) {
		return (ArrayList<ShowReview>)dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_REVIEW_FIND_BY_USER_ID).setParameter("userId", userId).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ShowReview> findShowReviewByRating(DataProviderHolder dataProviderHolder, double rating) {
		return (ArrayList<ShowReview>)dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_REVIEW_FIND_BY_RATING).setParameter("rating", rating).getResultList();
	}
	
}