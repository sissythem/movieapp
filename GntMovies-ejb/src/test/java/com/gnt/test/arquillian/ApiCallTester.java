package com.gnt.test.arquillian;

import java.io.IOException;
import java.util.HashSet;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.entities.Movie;
import com.gnt.movies.theMovieDB.ApiNewMovieResults;
import com.gnt.movies.utilities.Utils;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RunWith(Arquillian.class)
public class ApiCallTester {
	
	@Deployment
	public static WebArchive createDeployment() throws IOException {
		WebArchive archive = MyDeployment.getWar();
		archive.addClass(MyDeployment.class);
		return archive;
	}
	
	@Before
	public void initialize() {
		
		upmovies = getUpcomingMoviesTest();

	}

	HashSet<Movie> upmovies = new HashSet<>();

	@Test
	public void testUpcomingMovieApiCall() {
		
		Assert.assertTrue(upmovies.size() != 0);
		System.out.println("############################################ Upcoming movies are: "+ upmovies.size() + " movies.");

	}
	
	public HashSet<Movie> getUpcomingMoviesTest() {
		HashSet<Movie> newUpcomingMovies = new HashSet<>();

		ApiNewMovieResults upcomingMovieResults = getPagesForMoviesTest(1, Utils.UPCOMING_MOVIES_URL);
		newUpcomingMovies.addAll(upcomingMovieResults.getResults());

		for (int page = 2; page <= upcomingMovieResults.getTotalPages(); page++) {
			upcomingMovieResults = getPagesForMoviesTest(page, Utils.UPCOMING_MOVIES_URL);
			newUpcomingMovies.addAll(upcomingMovieResults.getResults());
		}
		return newUpcomingMovies;
	}

	public ApiNewMovieResults getPagesForMoviesTest(int page, String typeOfMovieUrl) {

		StringBuilder url = new StringBuilder(typeOfMovieUrl).append(Utils.API_KEY).append(Utils.LANGUAGE_FOR_URL)
				.append(Utils.NUMBER_PAGE_FOR_URL).append(Integer.toString(page));

		String resultJson = getResultFromTMDBTest(url.toString());

		ApiNewMovieResults upcomingMovieResults = new Gson().fromJson(resultJson, ApiNewMovieResults.class);

		return upcomingMovieResults;
	}

	public String getResultFromTMDBTest(String url) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).get().build();

		Response response;
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
