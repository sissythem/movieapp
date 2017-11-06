package com.gnt.movies.dao;

import com.gnt.movies.entities.User;

public interface UserDao {
	void createUser(DataProviderHolder dataProviderHolder, User user);
	void updateUser(DataProviderHolder dataProviderHolder, User user);
	void deleteUser(DataProviderHolder dataProviderHolder, User user);
	User findUserById(DataProviderHolder dataProviderHolder, Integer id);
	User findUserByUsername(DataProviderHolder dataProviderHolder, String username);
	User findUserByEmail(DataProviderHolder dataProviderHolder, String email);
	User findUserByPassword(DataProviderHolder dataProviderHolder, String password);
}

