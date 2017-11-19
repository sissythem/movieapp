package com.gnt.movies.dao;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.ShowImage;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("ShowImageDaoImpl")
public class ShowImageDaoImpl extends AbstractDao implements ShowImageDao {

	@Override
	public void createShowImage(DataProviderHolder dataProviderHolder, ShowImage showImage) {
		createEntity(dataProviderHolder.getEntityManager(), showImage);
	}

	@Override
	public void updateShowImage(DataProviderHolder dataProviderHolder, ShowImage showImage) {
		updateEntity(dataProviderHolder.getEntityManager(), showImage);
	}

	@Override
	public void deleteShowImage(DataProviderHolder dataProviderHolder, ShowImage showImage) {
		removeEntity(dataProviderHolder.getEntityManager(), showImage);
	}

	@Override
	public ShowImage findShowImageById(DataProviderHolder dataProviderHolder, Integer id) {
		return (ShowImage)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.MOVIE_IMAGE_FIND_BY_ID, "id", id);
	}

	@Override
	public ArrayList<ShowImage> findByShowId(DataProviderHolder dataProviderHolder, Integer showId) {
		ArrayList<ShowImage> list = new ArrayList<>();
		list.addAll(dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_IMAGE_FIND_BY_SHOW_ID)
				.setParameter("showId", showId).getResultList());
		return list;
	}

}
