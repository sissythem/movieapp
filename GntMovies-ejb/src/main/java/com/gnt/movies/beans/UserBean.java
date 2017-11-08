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
    	if(userDao.findUserByUsername(this, user.getUsername()) == null || 
    			userDao.findUserByEmail(this, user.getEmail()) == null)
    	{
    		try {
    			userDao.createUser(this, user);
        		return true;
    		}catch(Exception e) {
    			e.printStackTrace();
    			return false;
    		}
    		
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean loginUser(String username, String password) {
    	if(userDao.findUserByUsername(this, username)!=null && userDao.findUserByPassword(this, password) !=null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public User findUserByUsername(String username) {
    	return userDao.findUserByUsername(this, username);
    }

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
