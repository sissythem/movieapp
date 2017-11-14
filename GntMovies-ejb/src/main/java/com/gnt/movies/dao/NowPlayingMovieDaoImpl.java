package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
		return (NowPlayingMovie) findSingleEntity(dataProviderHolder.getEntityManager(),
				Utils.NOW_PLAYING_MOVIE_FIND_BY_ID, "id", id);
	}

	@Override
	public NowPlayingMovie findNowPlayingMovieByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		return (NowPlayingMovie) findSingleEntity(dataProviderHolder.getEntityManager(),
				Utils.NOW_PLAYING_MOVIE_FIND_BY_IDTMDB, "idTmdb", idTmdb);
	}

	@Override
	public NowPlayingMovie findNowPlayingMovieByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		return (NowPlayingMovie) findSingleEntity(dataProviderHolder.getEntityManager(),
				Utils.NOW_PLAYING_MOVIE_FIND_BY_MOVIE_ID, "movieId", movieId);
	}

	@Override
	public ArrayList<NowPlayingMovie> findAll(DataProviderHolder dataProviderHolder) {
		return (ArrayList<NowPlayingMovie>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.NOW_PLAYING_MOVIE_FIND_ALL).getResultList();
	}

	@Override
	public HashSet<Integer> getAllIdTmdb(DataProviderHolder dataProviderHolder) {
		HashSet<Integer> set = new HashSet<>();

		set.addAll((List<Integer>) dataProviderHolder.getEntityManager()
				.createNamedQuery(Utils.NOW_PLAYING_MOVIE_GET_ALL_IDTMDB).getResultList());
		return set;
	}

	@Override
	public void deleteNowPlayingMovieByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		dataProviderHolder.getEntityManager().createNamedQuery(Utils.NOW_PLAYING_MOVIE_DELETE_BY_IDTMDB)
				.setParameter("idTmdb", idTmdb).executeUpdate();
	}
}
