package com.gnt.test.arquillian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.MovieBean;
import com.gnt.movies.beans.UpcomingMovieBean;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.ApiClient;

@RunWith(Arquillian.class)
public class MovieTester {
	@PersistenceContext
	EntityManager em;

	@EJB
	MovieBean movieBean;

	@Deployment
	public static WebArchive createDeployment() throws IOException 
	{
		WebArchive war = ShrinkWrap.create(WebArchive.class, "testMovieBean.war")
				.addPackages(true, "com.gnt.movies")
	            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
	            .addAsWebInfResource("test-ds.xml")
	            .addAsLibraries(
						Maven.resolver().resolve("com.google.code.gson:gson:2.8.2").withoutTransitivity().asFile())
				.addAsLibraries(
						Maven.resolver().resolve("com.squareup.okio:okio:1.13.0").withoutTransitivity().asFile())
				.addAsLibraries(
						Maven.resolver().resolve("com.squareup.okhttp3:okhttp:3.9.0").withoutTransitivity().asFile())
	            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
				.addPackages(true, "com.gnt.movies")
	            .addAsManifestResource("test-persistence.xml", "persistence.xml")
	            .addAsManifestResource("wildfly-ds.xml")
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		return war;
//		return ShrinkWrap.create(WebArchive.class, "testMovieBean.war").addPackages(true, "com.gnt.movies")
//				.addAsLibraries(
//						Maven.resolver().resolve("com.google.code.gson:gson:2.8.2").withoutTransitivity().asFile())
//				.addAsLibraries(
//						Maven.resolver().resolve("com.squareup.okio:okio:1.13.0").withoutTransitivity().asFile())
//				.addAsLibraries(
//						Maven.resolver().resolve("com.squareup.okhttp3:okhttp:3.9.0").withoutTransitivity().asFile())
//				.addAsResource("META-INF/persistence.xml");
	}
	
	@Before
	public void init() {
		ApiClient.init();
		UpcomingMovieBean.init();
	}

	@Test
	public void testProximity() {
//		ApiNewMovie apiNewMovie = new ApiNewMovie(100, 120, 9.5, "Lord of the Rings", "image.jpeg", "English",
//				"Lord of the Rings", false, "Lord of the Rings", "2000-05-28");
//		Movie movie = movieBean.getMovie(apiNewMovie);
//		assertNotNull(movie);
		movieBean.addMovie(new Movie((byte) 0, 120, "2000-05-28", "English", "Lord of the Rings", "Lord of the Rings",
				"Lord of the Rings", 9.5, 100, "image.png"));
		assertEquals(movieBean.findMovieByIdTmdb(120).getTitle(), "Lord of the Rings");
		assertNotNull(movieBean.findMovieByIdTmdb(120));
	}
	
	@Test
	public void getUpcomingMoviesTest() {
		HashSet<ApiNewMovie> newUpcomingMovies = ApiCalls.getUpcomingMovies();
		assertNotNull(newUpcomingMovies);
	}
}
