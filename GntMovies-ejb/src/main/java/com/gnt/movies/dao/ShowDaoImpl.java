package com.gnt.movies.dao;

import com.gnt.movies.entities.Show;
import com.gnt.movies.utilities.Utils;

public class ShowDaoImpl extends AbstractDao implements ShowDao {

	@Override
	public void createShow(DataProviderHolder dataProviderHolder, Show show) {
		createEntity(dataProviderHolder.getEntityManager(), show);
	}

	@Override
	public void updateShow(DataProviderHolder dataProviderHolder, Show show) {
		updateEntity(dataProviderHolder.getEntityManager(), show);
	}

	@Override
	public void deleteShow(DataProviderHolder dataProviderHolder, Show show) {
		removeEntity(dataProviderHolder.getEntityManager(), show);
	}

	@Override
	public Show findShowById(DataProviderHolder dataProviderHolder, Integer id) {
		return (Show)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.SHOW_FIND_BY_ID, "id", id);
	}

	@Override
	public Show findShowByName(DataProviderHolder dataProviderHolder, String name) {
		return (Show)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.SHOW_FIND_BY_NAME, "name", name);
	}

	@Override
	public Show findShowByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		return (Show)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.SHOW_FIND_BY_IDTMDB, "idTmdb", idTmdb);
	}
	

}
