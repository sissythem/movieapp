package com.gnt.movies.dao;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.User;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("UserDaoImpl")
public class UserDaoImpl extends AbstractDao implements UserDao {

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
		return (User)getSingleResult(dataProviderHolder.getEntityManager(), Utils.USER_FIND_BY_ID, id);
	}
	
	@Override
	public User findUserByUsername(DataProviderHolder dataProviderHolder, String username) {
		return (User)getSingleResult(dataProviderHolder.getEntityManager(), Utils.USER_FIND_BY_USERNAME, username);
	}

	@Override
	public User findUserByEmail(DataProviderHolder dataProviderHolder, String email) {
		return (User)getSingleResult(dataProviderHolder.getEntityManager(), Utils.USER_FIND_BY_EMAIL, email);
	}
	
	@Override
	public User findUserByPassword(DataProviderHolder dataProviderHolder, String password) {
		return (User)getSingleResult(dataProviderHolder.getEntityManager(), Utils.USER_FIND_BY_PASSWORD, password);
	}
	
	@Override
	public List<Object> findByAge(DataProviderHolder dataProviderHolder, int age){
		return (List<Object>)findListEntities(dataProviderHolder, "age", String.valueOf(age), Utils.USER_FIND_BY_AGE);
	}

}
