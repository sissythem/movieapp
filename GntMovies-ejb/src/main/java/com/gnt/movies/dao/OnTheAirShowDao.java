package com.gnt.movies.dao;

import com.gnt.movies.entities.OnTheAirShow;

public interface OnTheAirShowDao {
	void createOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow);
	void updateOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow);
	void deleteOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow);
	OnTheAirShow findOnTheAirShowById(DataProviderHolder dataProviderHolder, Integer id);
}
