package com.gnt.movies.dao;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

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
	public ArrayList<MovieFavorite> findMovieFavoriteByUserId(DataProviderHolder dataProviderHolder, Integer userId) {
		return (ArrayList<MovieFavorite>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_FAVORITE_FIND_BY_USER_ID)
				.setParameter(userId, userId).getResultList();
	}

	@Override
	public ArrayList<MovieFavorite> findMovieFavoriteByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		return (ArrayList<MovieFavorite>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_FAVORITE_FIND_BY_MOVIE_ID)
				.setParameter(movieId, movieId).getResultList();
	}

	@Override
	public ArrayList<MovieFavorite> findAll(DataProviderHolder dataProviderHolder) {
		return (ArrayList<MovieFavorite>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.MOVIE_FAVORITE_FIND_ALL).getResultList();
	}

}
