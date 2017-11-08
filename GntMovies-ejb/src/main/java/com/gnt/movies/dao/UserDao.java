package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.User;

public interface UserDao {
	void createUser(DataProviderHolder dataProviderHolder, User user);
	void updateUser(DataProviderHolder dataProviderHolder, User user);
	void deleteUser(DataProviderHolder dataProviderHolder, User user);
	boolean UsernameExists(DataProviderHolder dataProviderHolder, String username);
	boolean EmailExists(DataProviderHolder dataProviderHolder, String email);
	User findUserById(DataProviderHolder dataProviderHolder, Integer id);
	User findUserByUsername(DataProviderHolder dataProviderHolder, String username);
	User findUserByEmail(DataProviderHolder dataProviderHolder, String email);
	User findUserByPassword(DataProviderHolder dataProviderHolder, String password);
	List<User> findByAge(DataProviderHolder dataProviderHolder, int age);
	boolean checkCredentials(DataProviderHolder dataProviderHolder, String username, String password);
}

