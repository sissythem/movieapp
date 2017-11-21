package com.gnt.test.arquillian;

import java.io.IOException;
import java.util.HashSet;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.GenreBean;
import com.gnt.movies.beans.SchedulerBean;
import com.gnt.movies.beans.UpcomingMovieBean;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.ApiClient;

@RunWith(Arquillian.class)
public class UpcomingMovieTester {

	@Deployment
	public static WebArchive createDeployment() throws IOException {

		WebArchive war = ShrinkWrap.create(WebArchive.class, "testUpcomingMovieBean.war")
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
//		return ShrinkWrap.create(WebArchive.class, "test.war")
//				.addPackages(true, "com.gnt.movies")
//				.addAsLibraries(
//						Maven.resolver().resolve("com.google.code.gson:gson:2.8.2").withoutTransitivity().asFile())
//				.addAsLibraries(
//						Maven.resolver().resolve("com.squareup.okio:okio:1.13.0").withoutTransitivity().asFile())
//				.addAsLibraries(
//						Maven.resolver().resolve("com.squareup.okhttp3:okhttp:3.9.0").withoutTransitivity().asFile())
//				.addAsResource("META-INF/persistence.xml");
		return war;
	}
	
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
	}
	
	@Test
	public void testSchedulersUpcomingMovies() {
		
		HashSet<ApiNewMovie> upcomingMoviesFromApi = new HashSet<>();
		
		upcomingMovieBean.findAllIdTmdb();
		upcomingMoviesFromApi.addAll(ApiCalls.getUpcomingMovies());
		upcomingMoviesFromApi.stream().parallel().forEach(e->upcomingMovieBean.checkUpcomingMovie(e));
		upcomingMovieBean.removeOldNotUpMovies(upcomingMoviesFromApi);

		int upcomingMoviesNumberInDatabase = 0;
		
		for (UpcomingMovie upcomingMovie : upcomingMovieBean.getAllUpcomingMovies()) {
			for (ApiNewMovie apiNewMovie : upcomingMoviesFromApi) {
				if (upcomingMovie.getIdTmdb() == apiNewMovie.getId()) upcomingMoviesNumberInDatabase++;
			}
		}
		
		
		Assert.assertTrue(upcomingMoviesNumberInDatabase == upcomingMoviesFromApi.size());
	}
}
