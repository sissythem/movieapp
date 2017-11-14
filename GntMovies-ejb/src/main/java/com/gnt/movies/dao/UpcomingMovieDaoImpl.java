package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("UpcomingMovieDaoImpl")
public class UpcomingMovieDaoImpl extends AbstractDao implements UpcomingMovieDao {

	@Override
	public void createUpcomingMovie(DataProviderHolder dataProviderHolder, UpcomingMovie upcomingMovie) {
		createEntity(dataProviderHolder.getEntityManager(), upcomingMovie);
	}

	@Override
	public void updateUpcomingMovie(DataProviderHolder dataProviderHolder, UpcomingMovie upcomingMovie) {
		updateEntity(dataProviderHolder.getEntityManager(), upcomingMovie);
	}

	@Override
	public void deleteUpcomingMovie(DataProviderHolder dataProviderHolder, UpcomingMovie upcomingMovie) {
		removeEntity(dataProviderHolder.getEntityManager(), upcomingMovie);
	}

	@Override
	public UpcomingMovie findUpcomingMovieById(DataProviderHolder dataProviderHolder, Integer id) {
		return (UpcomingMovie)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.UPCOMING_MOVIE_FIND_BY_ID, "id", id);
	}
	
	@Override
	public UpcomingMovie findByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		return (UpcomingMovie)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.UPCOMING_MOVIE_FIND_BY_MOVIE_ID, "movieId", movieId);
	}

	@Override
	public UpcomingMovie findByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		return (UpcomingMovie)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.UPCOMING_MOVIE_FIND_BY_IDTMDB, "idTmdb", idTmdb);
	}

	@Override
	public ArrayList<UpcomingMovie> findAll(DataProviderHolder dataProviderHolder) {
		return (ArrayList<UpcomingMovie>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.UPCOMING_MOVIE_FIND_ALL).getResultList();
	}
	
	@Override
	public HashSet<Integer> getAllIdTmdb(DataProviderHolder dataProviderHolder){
		HashSet<Integer> set = new HashSet<>();
		
		set.addAll((List<Integer>)dataProviderHolder.getEntityManager().createNamedQuery(Utils.UPCOMING_MOVIE_GET_ALL_IDTMDB).getResultList());
		return set;
	}

	@Override
	public void deleteUpcomingMovieByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		dataProviderHolder.getEntityManager().createNamedQuery(Utils.UPCOMING_MOVIE_DELETE_BY_IDTMDB).setParameter("idTmdb", idTmdb).executeUpdate();
	}
	
}
