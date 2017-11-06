package com.gnt.movies.dao;

import com.gnt.movies.entities.NowPlayingMovie;

public interface NowPlayingMovieDao {
	void createNowPlayingMovie(DataProviderHolder dataProviderHolder, NowPlayingMovie nowPlayingMovie);
	void updateNowPlayingMovie(DataProviderHolder dataProviderHolder, NowPlayingMovie nowPlayingMovie);
	void deleteNowPlayingMovie(DataProviderHolder dataProviderHolder, NowPlayingMovie nowPlayingMovie);
	NowPlayingMovie findNowPlayingMovieById(DataProviderHolder dataProviderHolder, Integer id);
	NowPlayingMovie findNowPlayingMovieByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
	NowPlayingMovie findNowPlayingMovieByMovieId(DataProviderHolder dataProviderHolder, Integer movieId);
}
