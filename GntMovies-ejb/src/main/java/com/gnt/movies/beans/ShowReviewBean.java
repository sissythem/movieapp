package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.ShowReviewDao;
import com.gnt.movies.entities.Show;
import com.gnt.movies.entities.ShowReview;
import com.gnt.movies.entities.User;

/**
 * Session Bean implementation class ShowReviewBean
 */
@Stateless
@LocalBean
public class ShowReviewBean implements DataProviderHolder {

	@PersistenceContext
	EntityManager em;

	@Inject
	@JpaDao
	@Named("ShowReviewDaoImpl")
	ShowReviewDao showReviewDao;

	public ShowReviewBean() {

	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	public void addShowReview(User user, Show show) {
		showReviewDao.createShowReview(this, new ShowReview(user, show));
	}
	
	public void deleteShowReview(User user, Show show) {
		showReviewDao.deleteShowReview(this, new ShowReview(user, show));
	}
	
	public ArrayList<ShowReview> getShowReviewsByShow(Show show){
		return (ArrayList<ShowReview>) showReviewDao.findShowReviewByShowId(this, show.getId());
	}

}
