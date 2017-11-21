package com.gnt.movies.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.MovieBean;

@RunWith(Arquillian.class)
public class MovieTester 
{
	@PersistenceContext
    EntityManager em;
	
	@EJB
	MovieBean movieBean;
	
	@Deployment
	public static JavaArchive createDeployment() throws IOException 
	{
		
		// You can use war packaging...
        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(MovieBean.class.getPackage())
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource("wildfly-ds.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        // or jar packaging...
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addPackage(MovieBean.class.getPackage())
            .addAsManifestResource("test-persistence.xml", "persistence.xml")
            .addAsManifestResource("wildfly-ds.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        
        // choose your packaging here
        return jar;
	}
	
	@Test
//	@InSequence(1)
	public void testProximity() {
//		ApiNewMovie apiNewMovie = new ApiNewMovie(100, 120, 9.5, "Lord of the Rings", "image.jpeg",
//				"English", "Lord of the Rings", false, "Lord of the Rings", "2000-05-28");
//		Movie movie = movieBean.getMovie(apiNewMovie);
//		assertNotNull(movie);
//		movieBean.addMovie(new Movie((byte)0, 120, "2000-05-28", "English", "Lord of the Rings", "Lord of the Rings", "Lord of the Rings", 9.5, 100, "image.png"));
		assertEquals(movieBean.findMovieByIdTmdb(120).getTitle(), "Lord of the Rings");
		assertNotNull(movieBean.findMovieByIdTmdb(120));
	}
}
