package com.gnt.test.beans;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gnt.movies.beans.UserBean;
import com.gnt.movies.dao.UserDao;

@RunWith(MockitoJUnitRunner.class)
public class TestUserBean {

	@Mock 
	UserDao userDaoMock;
	
	@Mock
	EntityManager entityManager;
	
	@InjectMocks
	UserBean userBean;

	@Test
	public void testLoginUser() {
		when(userDaoMock.checkCredentials(userBean, "username", "password"))
			.thenReturn(true);
		assertEquals(true, userBean.loginUser("username", "password"));
		when(userDaoMock.checkCredentials(userBean, "gmaz", "1234"))
			.thenReturn(false);
		assertEquals(false, userBean.loginUser("gmaz", "1234"));
	}
	
	@Test
	public void testLoginToken() {
		when(userDaoMock.checkToken(userBean, "1234")).thenReturn(true);
		assertEquals(true, userBean.loginToken("1234"));
	}
}
