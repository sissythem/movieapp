package com.gnt.movies.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.ShowImageDao;
import com.gnt.movies.entities.ShowImage;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
public class ShowImageBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(ShowImageBean.class);

	@PersistenceContext
	private EntityManager em;

	@Inject
	@JpaDao
	@Named("ShowImageDaoImpl")
	ShowImageDao showImageDao;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	public ShowImageBean() {
	}

	public void addShowImage(ShowImage showImage) {

		showImageDao.createShowImage(this, showImage);
		logger.info("addShowImage with showId=" + showImage.getShow().getIdTmdb());
	}

}
