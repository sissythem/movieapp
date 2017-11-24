package com.gnt.test.junit.apicalls;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;

import org.junit.Test;

import com.gnt.movies.entities.Movie;
import com.gnt.movies.utilities.ApiCalls;

public class TestApiCalls {

	@Test
	public void testUpcomingMoviesCall() {
		HashSet<Movie> upcomingMovies = ApiCalls.getUpcomingMovies();
		assertNotNull(upcomingMovies);
	}

}
