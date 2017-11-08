package com.gnt.movies.dao;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.OnTheAirShow;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("OnTheAirShowDaoImpl")
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
		return(OnTheAirShow)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.ON_THE_AIR_SHOW_FIND_BY_ID, "id", id);
	}

	@Override
	public OnTheAirShow findOnTheAirShowByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		return(OnTheAirShow)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.ON_THE_AIR_SHOW_FIND_BY_IDTMDB, "idTmdb", idTmdb);
	}

	@Override
	public OnTheAirShow findOnTheAirShowByMovieId(DataProviderHolder dataProviderHolder, Integer showId) {
		return(OnTheAirShow)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.ON_THE_AIR_SHOW_FIND_BY_SHOW_ID, "showId", showId);
	}

}
