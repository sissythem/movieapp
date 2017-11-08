package com.gnt.movies.dao;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.Air2dayShow;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("Air2dayShowDaoImpl")
public class Air2dayShowDaoImpl extends AbstractDao implements Air2dayShowDao {

	@Override
	public void createAir2dayShow(DataProviderHolder dataProviderHolder, Air2dayShow air2dayShow) {
		createEntity(dataProviderHolder.getEntityManager(), air2dayShow);
	}

	@Override
	public void updateAir2dayShow(DataProviderHolder dataProviderHolder, Air2dayShow air2dayShow) {
		updateEntity(dataProviderHolder.getEntityManager(), air2dayShow);
	}

	@Override
	public void deleteAir2dayShow(DataProviderHolder dataProviderHolder, Air2dayShow air2dayShow) {
		removeEntity(dataProviderHolder.getEntityManager(), air2dayShow);
	}

	@Override
	public Air2dayShow findAir2dayShowById(DataProviderHolder dataProviderHolder, Integer id) {
		return (Air2dayShow)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.AIR2DAY_SHOW_FIND_BY_ID, "id", id);
	}

	@Override
	public Air2dayShow findByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb) {
		return (Air2dayShow)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.AIR2DAY_SHOW_FIND_BY_IDTMDB_ID, "idTmdb", idTmdb);
	}

	@Override
	public Air2dayShow findByShowId(DataProviderHolder dataProviderHolder, Integer showId) {
		return (Air2dayShow)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.AIR2DAY_SHOW_FIND_BY_SHOW_ID, "showId", showId);
	}

	@Override
	public List<Air2dayShow> findAll(DataProviderHolder dataProviderHolder) {
		return dataProviderHolder.getEntityManager().createNamedQuery(Utils.AIR2DAY_SHOW_FIND_ALL).getResultList();
	}

}
