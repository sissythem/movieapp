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
import com.gnt.movies.dto.UserSessionDto;
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

	public boolean usernameExists(String username) {
		return userDao.UsernameExists(this, username);
	}

	public boolean registerUser(User user) {
		if (userDao.UsernameExists(this, user.getUsername()) || userDao.EmailExists(this, user.getEmail())) {
			return false;
		} else {
			try {
				userDao.createUser(this, user);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public boolean loginUser(String username, String password) {
		if (userDao.checkCredentials(this, username,password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean loginToken(String token) {
		if (userDao.checkToken(this, token)) {
			return true;
		} else {
			return false;
		}
	}

	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(this, username);
	}
	
	public UserSessionDto findUserDtoByUsername(String username) {
		return new UserSessionDto(findUserByUsername(username));
	}
	

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
