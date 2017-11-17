package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.OnTheAirShow;
import com.gnt.movies.entities.ShowReview;
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

	@Override
	public ArrayList<OnTheAirShow> findAll(DataProviderHolder dataProviderHolder) {
		ArrayList<OnTheAirShow> list = new ArrayList<>();
		list.addAll(dataProviderHolder.getEntityManager().createNamedQuery(Utils.ON_THE_AIR_SHOW_FIND_ALL).getResultList());
		return list;
	}

	@Override
	public HashSet<Integer> getAllIdTmdb(DataProviderHolder dataProviderHolder) {
		HashSet<Integer> set = new HashSet<>();
		
		set.addAll((List<Integer>)dataProviderHolder.getEntityManager().createNamedQuery(Utils.ON_THE_AIR_SHOW_GET_ALL_IDTMDB).getResultList());
		return set;
	}

	@Override
	public void deleteOnTheAirShowByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		dataProviderHolder.getEntityManager().createNamedQuery(Utils.ON_THE_AIR_DELETE_BY_IDTMDB).setParameter("idTmdb", idTmdb).executeUpdate();
	}
}
