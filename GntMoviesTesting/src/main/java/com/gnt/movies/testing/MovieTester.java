package com.gnt.movies.testing;

import java.io.IOException;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.MovieBean;

@RunWith(Arquillian.class)
public class MovieTester 
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
	
	@EJB
	MovieBean movieBean;
	
	@Test
//	@InSequence(1)
	public void testProximity() {
		
	}
}
