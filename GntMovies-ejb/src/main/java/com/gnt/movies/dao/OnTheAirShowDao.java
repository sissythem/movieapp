package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.OnTheAirShow;

public interface OnTheAirShowDao {
	void createOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow);
	void updateOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow);
	void deleteOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow);
	OnTheAirShow findOnTheAirShowById(DataProviderHolder dataProviderHolder, Integer id);
	OnTheAirShow findOnTheAirShowByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
	OnTheAirShow findOnTheAirShowByMovieId(DataProviderHolder dataProviderHolder, Integer showId);
	List<OnTheAirShow> findAll(DataProviderHolder dataProviderHolder);
	List<Integer> getAllIdTmdb(DataProviderHolder dataProviderHolder);
}
