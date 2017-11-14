package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.HashSet;

import com.gnt.movies.entities.UpcomingMovie;

public interface UpcomingMovieDao {
	void createUpcomingMovie(DataProviderHolder dataProviderHolder, UpcomingMovie upcomingMovie);
	void updateUpcomingMovie(DataProviderHolder dataProviderHolder, UpcomingMovie upcomingMovie);
	void deleteUpcomingMovie(DataProviderHolder dataProviderHolder, UpcomingMovie upcomingMovie);
	UpcomingMovie findUpcomingMovieById(DataProviderHolder dataProviderHolder, Integer id);
	UpcomingMovie findByMovieId(DataProviderHolder dataProviderHolder, Integer movieId);
	UpcomingMovie findByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
	ArrayList<UpcomingMovie> findAll(DataProviderHolder dataProviderHolder);
	HashSet<Integer> getAllIdTmdb(DataProviderHolder dataProviderHolder);
	void deleteUpcomingMovieByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
}
