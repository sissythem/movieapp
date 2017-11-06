package com.gnt.movies.dao;

import java.util.List;

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
		return (MovieFavorite)getSingleResult(dataProviderHolder.getEntityManager(), Utils.MOVIE_FAVORITE_FIND_BY_ID, id);
	}

	@Override
	public List<Object> findMovieFavoriteByUserId(DataProviderHolder dataProviderHolder, Integer userId) {
		return findListEntities(dataProviderHolder, "userId", userId.toString(), Utils.MOVIE_FAVORITE_FIND_BY_USER_ID);
	}

	@Override
	public List<Object> findMovieFavoriteByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		return findListEntities(dataProviderHolder, "movieId", movieId.toString(), Utils.MOVIE_FAVORITE_FIND_BY_MOVIE_ID);
	}

}
