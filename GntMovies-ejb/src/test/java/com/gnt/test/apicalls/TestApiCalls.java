package com.gnt.test.apicalls;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;

import org.junit.Test;

import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.utilities.ApiCalls;

public class TestApiCalls {

	@Test
	public void testUpcomingMoviesCall() {
		HashSet<ApiNewMovie> upcomingMovies = ApiCalls.getUpcomingMovies();
		assertNotNull(upcomingMovies);
	}

}
