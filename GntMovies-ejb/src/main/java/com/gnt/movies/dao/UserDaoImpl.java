package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.Query;

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
		User user = new User();
        return (User)Utils.findSingleEntity(dataProviderHolder, "id", id.toString(), "User.findById");
	}
	
	@Override
	public User findUserByUsername(DataProviderHolder dataProviderHolder, String username) {
		User user = new User();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("User.findByUsername");
		query.setParameter("username", username);
		user = (User)query.getSingleResult();
		return user;
	}

	@Override
	public User findUserByEmail(DataProviderHolder dataProviderHolder, String email) {
		User user = new User();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("User.findByEmail");
		query.setParameter("email", email);
		user = (User)query.getSingleResult();
		return user;
	}
	
	@Override
	public User findUserByPassword(DataProviderHolder dataProviderHolder, String password) {
		User user = new User();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("User.findByPassword");
		query.setParameter("password", password);
		user = (User)query.getSingleResult();
		return user;
	}
	
	public List<User> findByAge(DataProviderHolder dataProviderHolder, int age){
		List<User> users = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("User.findByAge");
		query.setParameter("age", age);
		users = query.getResultList();
		return users;
	}

}
