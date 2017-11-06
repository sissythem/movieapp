package com.gnt.movies.dao;

import javax.persistence.Query;

import com.gnt.movies.entities.UpcomingMovie;

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
		UpcomingMovie upcomingMovie = new UpcomingMovie();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("UpcomingMovie.findById");
		query.setParameter("id", id);
		upcomingMovie = (UpcomingMovie)query.getSingleResult();
		return upcomingMovie;
	}
	
	public UpcomingMovie findByMovieId(DataProviderHolder dataProviderHolder, Integer movieId) {
		UpcomingMovie upcomingMovie = new UpcomingMovie();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("UpcomingMovie.findByMovieId");
		query.setParameter("movieId", movieId);
		upcomingMovie = (UpcomingMovie)query.getSingleResult();
		return upcomingMovie;
	}

	@Override
	public UpcomingMovie findByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		UpcomingMovie upcomingMovie = new UpcomingMovie();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("UpcomingMovie.findByIdTmdb");
		query.setParameter("idTmdb", idTmdb);
		upcomingMovie = (UpcomingMovie)query.getSingleResult();
		return upcomingMovie;
	}
}
