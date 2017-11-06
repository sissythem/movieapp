package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.Query;

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
		return (UpcomingMovie)getSingleResult(dataProviderHolder.getEntityManager(), Utils.UPCOMING_MOVIE_FIND_BY_ID, id);
	}
	
	@Override
	public UpcomingMovie findByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		return (UpcomingMovie)getSingleResult(dataProviderHolder.getEntityManager(), Utils.UPCOMING_MOVIE_FIND_BY_MOVIE_ID, movieId);
	}

	@Override
	public UpcomingMovie findByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		return (UpcomingMovie)getSingleResult(dataProviderHolder.getEntityManager(), Utils.UPCOMING_MOVIE_FIND_BY_IDTMDB, idTmdb);
	}

	@Override
	public List<UpcomingMovie> findAll(DataProviderHolder dataProviderHolder) {
		List<UpcomingMovie> allUpcomingMovies = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(Utils.UPCOMING_MOVIE_FIND_ALL);
		allUpcomingMovies = query.getResultList();
		return allUpcomingMovies;
	}
	
	@Override
	public ArrayList<Integer> getAllIdTmdb(DataProviderHolder dataProviderHolder){
		ArrayList<Integer> allIdTmdbUpcoming = new ArrayList<>();
		List<UpcomingMovie> allUpcomingMovies = new ArrayList<>();
		allUpcomingMovies = findAll(dataProviderHolder);
		for(UpcomingMovie upcomingMovie : allUpcomingMovies) {
			allIdTmdbUpcoming.add(upcomingMovie.getIdTmdb());
		}
		return allIdTmdbUpcoming;
	}
	
}
