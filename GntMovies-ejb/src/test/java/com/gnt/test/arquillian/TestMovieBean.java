package com.gnt.test.arquillian;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.GenreBean;
import com.gnt.movies.beans.MovieBean;
import com.gnt.movies.utilities.ApiClient;

@RunWith(Arquillian.class)
public class TestMovieBean {

	@EJB
	private MovieBean movieBean;
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
		
//		apiNewMovie = new ApiNewMovie(10, 120, 25, "The Title", null, null, "The Original Title", false, null, LocalDate.now().toString());
		
	}

	
	@Test
	public void testGetEntityManager() {
		assertNotNull("The entityManager cannot be null.",movieBean.getEntityManager());
	}

	@Test
	public void testGetMovie() {
//		genreBean.addGenres(ApiCalls.getGenres());
//		Movie movie1 = movieBean.getMovie(apiNewMovie);
//		Movie movie2 = movieBean.getMovie(apiNewMovie);
//		Movie movie3 = movieBean.findMovieByIdTmdb(120);
//		Movie movie4 = movieBean.findMovieById(movie1.getId());
//		Movie movie5 = movieBean.findMovieByTitle(movie1.getTitle());
//		movie2.setAdult(true);
//		movieBean.updateMovieInDataBase(movie2);
//		Movie movie6 = movieBean.findMovieByTitle(movie1.getTitle());
//		assertNotNull("The movie cannot be null.",movie1);
//		assertNotNull("The movie cannot be null.",movie2);
//		assertNotNull("The movie cannot be null.",movie3);
//		assertNotNull("The movie cannot be null.",movie4);
//		assertNotNull("The movie cannot be null.",movie5);
//		assertNotNull("The movie cannot be null.",movie6);
//		assertEquals(movie1, movie2);
//		assertEquals(movie1, movie3);
//		assertEquals(movie1, movie4);
//		assertEquals(movie1, movie5);
//		assertEquals(movie1, movie6);
//		assertEquals((byte)0, movie1.getAdult());
//		assertEquals((byte)1, movie6.getAdult());
		
	}
	

//	@Test
//	public void testUpdateMovieInDataBase() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindMovieByIdTmdb() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindMovieByTitle() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindMovieById() {
//		fail("Not yet implemented");
//	}

}
