package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.HashSet;

import com.gnt.movies.entities.OnTheAirShow;

public interface OnTheAirShowDao {
	void createOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow);
	void updateOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow);
	void deleteOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow);
	OnTheAirShow findOnTheAirShowById(DataProviderHolder dataProviderHolder, Integer id);
	OnTheAirShow findOnTheAirShowByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
	OnTheAirShow findOnTheAirShowByMovieId(DataProviderHolder dataProviderHolder, Integer showId);
	ArrayList<OnTheAirShow> findAll(DataProviderHolder dataProviderHolder);
	HashSet<Integer> getAllIdTmdb(DataProviderHolder dataProviderHolder);
	void deleteOnTheAirShowByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
}
