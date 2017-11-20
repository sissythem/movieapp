package com.gnt.movies.testing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.theMovieDB.ApiNewMovie;
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
		
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "com.gnt.movies")
				.addAsLibraries(
						Maven.resolver().resolve("com.google.code.gson:gson:2.8.2").withoutTransitivity().asFile())
				.addAsLibraries(
						Maven.resolver().resolve("com.squareup.okio:okio:1.13.0").withoutTransitivity().asFile())
				.addAsLibraries(
						Maven.resolver().resolve("com.squareup.okhttp3:okhttp:3.9.0").withoutTransitivity().asFile())
				.addAsResource("META-INF/persistence.xml");
		
	}
	
	@Before
	public void initialize() {
		
		upmovies = getUpcomingMoviesTest();

	}

	HashSet<ApiNewMovie> upmovies = new HashSet<>();

	@Test
	public void test() {
		
		Assert.assertTrue(upmovies.size() > 10);
		System.out.println("Upcoming movies are: "+ upmovies.size() + " movies.");

	}
	
	public HashSet<ApiNewMovie> getUpcomingMoviesTest() {
		HashSet<ApiNewMovie> newUpcomingMovies = new HashSet<>();

		ApiNewMovieResults upcomingMovieResults = getPagesForMovies(1, Utils.UPCOMING_MOVIES_URL);
		newUpcomingMovies.addAll(upcomingMovieResults.getResults());

		for (int page = 2; page <= upcomingMovieResults.getTotalPages(); page++) {
			upcomingMovieResults = getPagesForMovies(page, Utils.UPCOMING_MOVIES_URL);
			newUpcomingMovies.addAll(upcomingMovieResults.getResults());
		}
		return newUpcomingMovies;
	}

	public ApiNewMovieResults getPagesForMovies(int page, String typeOfMovieUrl) {

		StringBuilder url = new StringBuilder(typeOfMovieUrl).append(Utils.API_KEY).append(Utils.LANGUAGE_FOR_URL)
				.append(Utils.NUMBER_PAGE_FOR_URL).append(Integer.toString(page));

		String resultJson = getResultFromTMDB(url.toString());

		ApiNewMovieResults upcomingMovieResults = new Gson().fromJson(resultJson, ApiNewMovieResults.class);

		return upcomingMovieResults;
	}

	public String getResultFromTMDB(String url) {
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
