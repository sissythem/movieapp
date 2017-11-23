package com.gnt.test.arquillian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.Air2dayShowBean;
import com.gnt.movies.beans.GenreBean;
import com.gnt.movies.beans.NowPlayingMovieBean;
import com.gnt.movies.beans.OnTheAirShowBean;
import com.gnt.movies.beans.UpcomingMovieBean;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.ApiClient;

@RunWith(Arquillian.class)
public class ScheduledJobsTester {

	@Deployment
	public static WebArchive createDeployment() throws IOException 
	{
		WebArchive archive = MyDeployment.getWar();
		archive.addClass(MyDeployment.class);
		return archive;
	}
	
	@EJB
	GenreBean genreBean;
	
	@EJB
	Air2dayShowBean air2dayShowBean;
	
	@EJB
	private UpcomingMovieBean upcomingMovieBean;

	@EJB
	private NowPlayingMovieBean nowPlayingMovieBean;

	@EJB
	private OnTheAirShowBean onTheAirShowBean;
	
	@Before
	public void initialize() {
		ApiClient.init();
		UpcomingMovieBean.init();
		NowPlayingMovieBean.init();
		Air2dayShowBean.init();
		OnTheAirShowBean.init();
		
		HashSet<Genre> genres = ApiCalls.getGenres();
		genreBean.addGenres(genres);
		
		upcomingMovieBean.findAllIdTmdb();
		nowPlayingMovieBean.findAllIdTmdb();
		onTheAirShowBean.findAllIdTmdb();
		air2dayShowBean.findAllIdTmdb();
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
	
	@Test
	public void testGetAir2dayShows() {
		HashSet<ApiNewShow> air2dayShowsAPI = ApiCalls.getAir2dayShows();
		assertNotNull(air2dayShowsAPI);
		
		HashSet<ApiNewShow> testAir2dayShowsAPI =new HashSet<>();
		Iterator<ApiNewShow> it = air2dayShowsAPI.iterator();
		for(int i=0;i<10;i++) {
			if(it.hasNext())
				testAir2dayShowsAPI.add(it.next());
		}
		assertNotNull(testAir2dayShowsAPI);
		testAir2dayShowsAPI.stream().parallel().forEach(e->air2dayShowBean.checkAir2dayShow(e));
		assertEquals(testAir2dayShowsAPI.size(), air2dayShowBean.getAllAir2dayShows().size());
		air2dayShowBean.removeOldNotAir2dayShow(testAir2dayShowsAPI);
	}
	
}
