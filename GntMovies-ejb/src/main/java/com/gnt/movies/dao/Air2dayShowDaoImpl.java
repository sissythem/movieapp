package com.gnt.movies.dao;

import com.gnt.movies.entities.Air2dayShow;

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
		// TODO Auto-generated method stub
		return null;
	}

}
