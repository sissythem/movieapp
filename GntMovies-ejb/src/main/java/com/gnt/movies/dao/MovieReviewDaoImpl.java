package com.gnt.movies.dao;

import java.util.List;

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
		return (MovieReview)getSingleResult(dataProviderHolder.getEntityManager(), Utils.MOVIE_REVIEW_FIND_BY_ID, id);
	}

	@Override
	public List<Object> findMovieReviewByUserId(DataProviderHolder dataProviderHolder, Integer userId) {
		return findListEntities(dataProviderHolder, "userId", userId.toString(), Utils.MOVIE_REVIEW_FIND_BY_USER_ID);
	}

	@Override
	public List<Object> findMovieReviewByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		return findListEntities(dataProviderHolder, "movieId", movieId.toString(), Utils.MOVIE_REVIEW_FIND_BY_MOVIE_ID);
	}

}
