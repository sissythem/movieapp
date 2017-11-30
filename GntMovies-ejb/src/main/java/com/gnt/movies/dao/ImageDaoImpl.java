package com.gnt.movies.dao;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.Image;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("ImageDaoImpl")
public class ImageDaoImpl extends AbstractDao implements ImageDao {

	@Override
	public void createImage(DataProviderHolder dataProviderHolder, Image image) {
		createEntity(dataProviderHolder.getEntityManager(), image);
	}

	@Override
	public void updateImage(DataProviderHolder dataProviderHolder, Image image) {
		updateEntity(dataProviderHolder.getEntityManager(), image);
	}

	@Override
	public void deleteImage(DataProviderHolder dataProviderHolder, Image image) {
		removeEntity(dataProviderHolder.getEntityManager(), image);
	}

	@Override
	public Image findImageById(DataProviderHolder dataProviderHolder, Integer id) {
		return (Image) findSingleEntity(dataProviderHolder.getEntityManager(), Utils.IMAGE_FIND_BY_ID, "id", id);
	}
}
