package com.gnt.test.arquillian;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
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
		/** 
		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(GenreBean.class.getPackage())
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource("wildfly-ds.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        // or jar packaging...
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addPackage(GenreBean.class.getPackage())
            .addAsManifestResource("test-persistence.xml", "persistence.xml")
            .addAsManifestResource("wildfly-ds.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		 **/
		return ShrinkWrap.create(WebArchive.class, "testUseBean.war")
				.addPackages(true, "com.gnt.movies")
				.addAsResource("META-INF/persistence.xml");
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