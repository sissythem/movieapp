package com.gnt.movies.dao;

import com.gnt.movies.entities.NowPlayingMovie;

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
		// TODO Auto-generated method stub
		return null;
	}

}
