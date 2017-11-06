package com.gnt.movies.dao;

import com.gnt.movies.entities.OnTheAirShow;

public class OnTheAirShowDaoImpl extends AbstractDao implements OnTheAirShowDao {

	@Override
	public void createOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow) {
		createEntity(dataProviderHolder.getEntityManager(), onTheAirShow);
	}

	@Override
	public void updateOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow) {
		updateEntity(dataProviderHolder.getEntityManager(), onTheAirShow);
	}

	@Override
	public void deleteOnTheAirShow(DataProviderHolder dataProviderHolder, OnTheAirShow onTheAirShow) {
		removeEntity(dataProviderHolder.getEntityManager(), onTheAirShow);
	}

	@Override
	public OnTheAirShow findOnTheAirShowById(DataProviderHolder dataProviderHolder, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
