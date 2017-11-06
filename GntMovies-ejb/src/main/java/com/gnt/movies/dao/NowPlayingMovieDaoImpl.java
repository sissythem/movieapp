package com.gnt.movies.dao;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.NowPlayingMovie;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("NowPlayingMovieDaoImpl")
public class NowPlayingMovieDaoImpl extends AbstractDao implements NowPlayingMovieDao {

	@Override
	public void createNowPlayingMovie(DataProviderHolder dataProviderHolder, NowPlayingMovie nowPlayingMovie) {
		createEntity(dataProviderHolder.getEntityManager(), nowPlayingMovie);
	}

	@Override
	public void updateNowPlayingMovie(DataProviderHolder dataProviderHolder, NowPlayingMovie nowPlayingMovie) {
		updateEntity(dataProviderHolder.getEntityManager(), nowPlayingMovie);
	}

	@Override
	public void deleteNowPlayingMovie(DataProviderHolder dataProviderHolder, NowPlayingMovie nowPlayingMovie) {
		removeEntity(dataProviderHolder.getEntityManager(), nowPlayingMovie);
	}

	@Override
	public NowPlayingMovie findNowPlayingMovieById(DataProviderHolder dataProviderHolder, Integer id) {
		return (NowPlayingMovie)getSingleResult(dataProviderHolder.getEntityManager(), Utils.NOW_PLAYING_MOVIE_FIND_BY_ID, id);
	}

	@Override
	public NowPlayingMovie findNowPlayingMovieByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		return (NowPlayingMovie)getSingleResult(dataProviderHolder.getEntityManager(), Utils.NOW_PLAYING_MOVIE_FIND_BY_IDTMDB, idTmdb.toString());
	}

	@Override
	public NowPlayingMovie findNowPlayingMovieByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		return (NowPlayingMovie)getSingleResult(dataProviderHolder.getEntityManager(), Utils.NOW_PLAYING_MOVIE_FIND_BY_MOVIE_ID, movieId);
	}

}
