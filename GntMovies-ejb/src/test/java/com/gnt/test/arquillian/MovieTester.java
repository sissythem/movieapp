package com.gnt.test.arquillian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.GenreBean;
import com.gnt.movies.beans.MovieBean;
import com.gnt.movies.beans.UpcomingMovieBean;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.ApiClient;

@RunWith(Arquillian.class)
public class MovieTester {
	@PersistenceContext
	EntityManager em;

	@EJB
	MovieBean movieBean;
	
	@EJB
	UpcomingMovieBean upcomingMovieBean;
	
	@EJB
	GenreBean genreBean;

	@Deployment
	public static WebArchive createDeployment() throws IOException 
	{
		WebArchive archive = MyDeployment.getWar();
		archive.addClass(MyDeployment.class);
		return archive;

	}
	
	@Before
	public void init() {
		ApiClient.init();
		UpcomingMovieBean.init();
	}

	@Test
	public void testAddAndFindMovie() {
		Movie movie = new Movie((byte) 0, 120, "2000-05-28", "English", "Lord of the Rings", "Lord of the Rings",
				"Lord of the Rings", 9.5, 100, "image.png");
		movieBean.addMovie(movie);
		UpcomingMovie lorMovie = new UpcomingMovie();
		lorMovie.setIdTmdb(120);
		lorMovie.setMovie(movie);
		upcomingMovieBean.addUpcomingMovie(lorMovie);
		assertEquals(movieBean.findMovieByIdTmdb(120).getTitle(), "Lord of the Rings");
		assertNotNull(movieBean.findMovieByIdTmdb(120));
	}
}
