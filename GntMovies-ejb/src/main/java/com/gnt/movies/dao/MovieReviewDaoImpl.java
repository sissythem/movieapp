package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.Query;

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

	@Override
	public List<MovieReview> findMovieReviewByUserId(DataProviderHolder dataProviderHolder, Integer userId) {
		List<MovieReview> movieGenres = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_REVIEW_FIND_BY_USER_ID);
		query.setParameter("userId", userId);
		movieGenres = query.getResultList();
		return movieGenres;
	}

	@Override
	public List<MovieReview> findMovieReviewByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		List<MovieReview> movieGenres = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_REVIEW_FIND_BY_MOVIE_ID);
		query.setParameter("movieId", movieId);
		movieGenres = query.getResultList();
		return movieGenres;
	}

}
