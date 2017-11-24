package com.gnt.test.junit.apicalls;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

<<<<<<< HEAD
import com.gnt.movies.entities.Movie;
=======
import com.gnt.movies.entities.Genre;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.theMovieDB.ApiNewShow;
>>>>>>> branch 'master' of https://gitlab.com/gnt-training/Gnt-Movies.git
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.ApiClient;

public class TestApiCalls {
	
	@Before
	public void init() {
		ApiClient.init();
	}

	@Test
	public void testGetGenres() {
		HashSet<Genre> genres = ApiCalls.getGenres();
		assertNotNull(genres);
	}
	
	@Test
	public void testUpcomingMoviesCall() {
		HashSet<Movie> upcomingMovies = ApiCalls.getUpcomingMovies();
		assertNotNull(upcomingMovies);
	}
	
	@Test
	public void testNowPlayingMoviesCall() {
		HashSet<ApiNewMovie> nowPlayingMovies = ApiCalls.getNowPlayingMovies();
		assertNotNull(nowPlayingMovies);
	}
	
	@Test
	public void testOnTheAirShowsCall() {
		HashSet<ApiNewShow> onTheAirShows = ApiCalls.getOnTheAirShows();
		assertNotNull(onTheAirShows);
	}
	
	@Test
	public void testAir2dayShowsCall() {
		HashSet<ApiNewShow> air2dayShows = ApiCalls.getAir2dayShows();
		assertNotNull(air2dayShows);
	}
}
