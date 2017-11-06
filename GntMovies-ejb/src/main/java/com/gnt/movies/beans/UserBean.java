package com.gnt.movies.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.UserDao;
import com.gnt.movies.entities.User;


@Stateless
@LocalBean
public class UserBean implements DataProviderHolder {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("UserDaoImpl")
	UserDao userDao;

    public UserBean() {
    
    }
    
    public boolean registerUser(User user) {
    	userDao.createUser(this, user);
    	return true;
    }

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

}
