package com.gnt.test.arquillian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.Air2dayShowBean;
import com.gnt.movies.beans.GenreBean;
import com.gnt.movies.beans.UpcomingMovieBean;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.ApiClient;

@RunWith(Arquillian.class)
public class UpcomingMovieTester {

	@Deployment
	public static WebArchive createDeployment() throws IOException {

		return MyDeployment.getWar("UpcomingMovieTester");
	}
	@PersistenceContext
	EntityManager em;
	
	@EJB
	UpcomingMovieBean upcomingMovieBean;

	@EJB
	GenreBean genreBean;
	
	@Before
	public void initialize() {
		ApiClient.init();
		UpcomingMovieBean.init();
		HashSet<Genre> genres = ApiCalls.getGenres();
		genreBean.addGenres(genres);
		upcomingMovieBean.findAllIdTmdb();
	}
	
	@Test
	public void testGetUpcomingMovies() {
		HashSet<ApiNewMovie> upcomingMovie = ApiCalls.getUpcomingMovies();
		assertNotNull(upcomingMovie);	
		HashSet<ApiNewMovie> testUpcomingMovie = new HashSet<>();
		Iterator<ApiNewMovie> it = upcomingMovie.iterator();
		for(int i=0;i<10;i++) {
			if(it.hasNext())
				testUpcomingMovie.add(it.next());
		}
		assertNotNull(testUpcomingMovie);
		testUpcomingMovie.stream().parallel().forEach(e->upcomingMovieBean.checkUpcomingMovie(e));
		assertEquals(testUpcomingMovie.size(), upcomingMovieBean.getAllUpcomingMovies().size());
		upcomingMovieBean.removeOldNotUpMovies(testUpcomingMovie);
	}
}
