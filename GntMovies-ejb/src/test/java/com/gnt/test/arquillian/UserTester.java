package com.gnt.test.arquillian;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.UserBean;
import com.gnt.movies.entities.User;

@RunWith(Arquillian.class)
public class UserTester 
{
	@Deployment
	public static WebArchive createDeployment() throws IOException 
	{	 
		WebArchive archive = MyDeployment.getWar();
		archive.addClass(MyDeployment.class);
		return archive;
	}
	
	@EJB
	UserBean userBean;

	@Test
	public void testProximity() {
		User user = new User(20, "email@email.com", "firstname", "lastname", "password", "username");
		assertEquals(userBean.registerUser(user), true);
		assertEquals(userBean.findUserByUsername("username").getUsername(), "username");
		assertEquals(userBean.loginUser("username", "password"), true);
	}
}