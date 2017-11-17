package com.gnt.movies.testing;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.UserBean;
import com.gnt.movies.entities.User;

@RunWith(Arquillian.class)
public class UserTester 
{
	@Deployment
	public static JavaArchive createDeployment() throws IOException 
	{
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
				.addPackages(true, "com.gnt")
				.addAsResource("META-INF/persistence.xml");
				System.out.println(jar.toString(true));
				return jar;
	}
	
	// Declare your beans here
	@EJB
	UserBean userBean;

	// Add your test cases here
	@Test
//	@InSequence(1)
	public void testProximity() {
		User user = new User(20, "email@email.com", "firstname", "lastname", "password", "username");
		// Mock your input data and then combine the result with the expected value
		// Assert.assertEquals(movieBean.isProximityReserved("4417010628"), true);
		assertEquals(userBean.registerUser(user), true);
	}
}