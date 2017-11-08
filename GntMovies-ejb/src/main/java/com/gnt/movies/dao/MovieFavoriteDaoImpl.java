package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.Query;

import com.gnt.movies.entities.MovieFavorite;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("MovieFavoriteDaoImpl")
public class MovieFavoriteDaoImpl extends AbstractDao implements MovieFavoriteDao {

	@Override
	public void createMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite) {
		createEntity(dataProviderHolder.getEntityManager(), movieFavorite);
	}

	@Override
	public void updateMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite) {
		updateEntity(dataProviderHolder.getEntityManager(), movieFavorite);
	}

	@Override
	public void deleteMovieFavorite(DataProviderHolder dataProviderHolder, MovieFavorite movieFavorite) {
		removeEntity(dataProviderHolder.getEntityManager(), movieFavorite);
	}

	@Override
	public MovieFavorite findMovieFavoriteById(DataProviderHolder dataProviderHolder, Integer id) {
		return (MovieFavorite)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.MOVIE_FAVORITE_FIND_BY_ID, "id", id);
	}

	@Override
	public List<MovieFavorite> findMovieFavoriteByUserId(DataProviderHolder dataProviderHolder, Integer userId) {
		List<MovieFavorite> movieFavorites = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_FAVORITE_FIND_BY_USER_ID);
		query.setParameter(userId, userId);
		movieFavorites = query.getResultList();
		return movieFavorites;
	}

	@Override
	public List<MovieFavorite> findMovieFavoriteByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		List<MovieFavorite> movieFavorites = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_FAVORITE_FIND_BY_MOVIE_ID);
		query.setParameter(movieId, movieId);
		movieFavorites = query.getResultList();
		return movieFavorites;
	}

	@Override
	public List<MovieFavorite> findAll(DataProviderHolder dataProviderHolder) {
		return dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_FAVORITE_FIND_ALL).getResultList();
	}

}
