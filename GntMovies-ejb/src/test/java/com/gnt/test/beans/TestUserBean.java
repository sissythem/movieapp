package com.gnt.test.beans;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

import com.gnt.movies.entities.User;

public class TestUserBean {

	@Mock User userMock;
	
	@Test
	public void testLoginUserSuccess() {
		when(userMock.getUsername()).thenReturn("username");
		when(userMock.getPassword()).thenReturn("password");
		assertEquals("username",userMock.getUsername());
		assertEquals("password", userMock.getPassword());
	}

}
