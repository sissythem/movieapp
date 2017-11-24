package com.gnt.movies.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.ImageDao;
import com.gnt.movies.entities.Image;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
public class ImageBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(ImageBean.class);

	@PersistenceContext
	private EntityManager em;

	@Inject
	@JpaDao
	@Named("ImageDaoImpl")
	ImageDao imageDao;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	public ImageBean() {
	}

	public synchronized void addImage(Image image) {

		imageDao.createImage(this, image);
		logger.info("addImage url:" + image.getFilePath());
	}

}
