package com.gnt.movies.dao;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.User;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("UserDaoImpl")
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public boolean UsernameExists(DataProviderHolder dataProviderHolder, String username) {
		Long result = (Long) findSingleEntity(dataProviderHolder.getEntityManager(), Utils.USER_CHECK_USERNAME,
				"username", username);
		return (result == null ? false : true);
	}

	@Override
	public boolean EmailExists(DataProviderHolder dataProviderHolder, String email) {
		Long result = (Long) findSingleEntity(dataProviderHolder.getEntityManager(), Utils.USER_CHECK_EMAIL,
				"email", email);
		return (result == null ? false : true);
	}

	@Override
	public void createUser(DataProviderHolder dataProviderHolder, User user) {
		createEntity(dataProviderHolder.getEntityManager(), user);
	}

	@Override
	public void updateUser(DataProviderHolder dataProviderHolder, User user) {
		updateEntity(dataProviderHolder.getEntityManager(), user);
	}

	@Override
	public void deleteUser(DataProviderHolder dataProviderHolder, User user) {
		removeEntity(dataProviderHolder.getEntityManager(), user);
	}

	@Override
	public User findUserById(DataProviderHolder dataProviderHolder, Integer id) {
		return (User) findSingleEntity(dataProviderHolder.getEntityManager(), Utils.USER_FIND_BY_ID, "id", id);
	}

	@Override
	public User findUserByUsername(DataProviderHolder dataProviderHolder, String username) {
		return (User) findSingleEntity(dataProviderHolder.getEntityManager(), Utils.USER_FIND_BY_USERNAME, "username",
				username);
	}

	@Override
	public User findUserByEmail(DataProviderHolder dataProviderHolder, String email) {
		return (User) findSingleEntity(dataProviderHolder.getEntityManager(), Utils.USER_FIND_BY_EMAIL, "email", email);
	}

	@Override
	public User findUserByPassword(DataProviderHolder dataProviderHolder, String password) {
		return (User) findSingleEntity(dataProviderHolder.getEntityManager(), Utils.USER_FIND_BY_PASSWORD, "password",
				password);
	}

	@Override
	public ArrayList<User> findByAge(DataProviderHolder dataProviderHolder, int age) {
		return (ArrayList<User>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.USER_FIND_BY_AGE)
				.setParameter("age", age).getResultList();
	}

	@Override
	public boolean checkCredentials(DataProviderHolder dataProviderHolder, String username, String password) {
		Long result = (Long) getSingleResult(dataProviderHolder.getEntityManager(), Utils.USER_LOGIN, username,
				password);
		return (result == null ? false : true);
	}

	@Override
	public boolean checkToken(DataProviderHolder dataProviderHolder, String token) {
		Long result = (Long) getSingleResult(dataProviderHolder.getEntityManager(), Utils.USER_LOGIN_TOKEN, token);
		return (result == null ? false : true);
	}

}
