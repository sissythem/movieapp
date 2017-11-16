package com.gnt.test.beans;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.gnt.movies.beans.UserBean;
import com.gnt.movies.dao.UserDaoImpl;
import com.gnt.movies.entities.User;

@RunWith(MockitoJUnitRunner.class)
public class TestUserBean {

	@Mock 
	UserDaoImpl userDaoImplMock;
	
	@InjectMocks
	UserBean userBean;
	
	@Test
	public void testLoginUserFailure() {
		User user = new User(18, "email@email.com", "name", "lastname", 
				"password", "username");
		assertEquals(false, userBean.loginUser(user.getUsername(), 
				user.getPassword()));
	}

}
