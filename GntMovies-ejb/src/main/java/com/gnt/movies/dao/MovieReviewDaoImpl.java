package com.gnt.movies.dao;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.MovieReview;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("MovieReviewDaoImpl")
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
		return (MovieReview)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.MOVIE_REVIEW_FIND_BY_ID, "id", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<MovieReview> findMovieReviewByUserId(DataProviderHolder dataProviderHolder, Integer userId) {
		return (ArrayList<MovieReview>)dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_REVIEW_FIND_BY_USER_ID)
				.setParameter("userId", userId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<MovieReview> findMovieReviewByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		return (ArrayList<MovieReview>)dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_REVIEW_FIND_BY_MOVIE_ID)
				.setParameter("movieId", movieId).getResultList();
	}
	
	@Override
	public MovieReview findMovieReview(DataProviderHolder dataProviderHolder, Integer movieId, Integer userId){
		return (MovieReview) dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_REVIEW)
				.setParameter(1, movieId)
				.setParameter(2, userId)
				.getSingleResult();
	}
}
