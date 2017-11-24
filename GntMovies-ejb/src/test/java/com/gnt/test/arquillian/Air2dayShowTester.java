package com.gnt.test.arquillian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.GenreBean;
import com.gnt.movies.beans.Air2dayShowBean;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Air2dayShow;
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.ApiClient;

@RunWith(Arquillian.class)
public class Air2dayShowTester {

	@EJB
	Air2dayShowBean air2dayShowBeanTest;
	
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
	public void initialize() {
		ApiClient.init();
		Air2dayShowBean.init();
		
		HashSet<Genre> genres = ApiCalls.getGenres();
		genreBean.addGenres(genres);
		
		air2dayShowBeanTest.findAllIdTmdb();
		HashSet<ApiNewShow> air2dayShows = ApiCalls.getAir2dayShows();
		
		air2dayShows.stream().parallel().forEach(e->air2dayShowBeanTest.checkAir2dayShow(e));
		air2dayShowBeanTest.removeOldNotAir2dayShow(air2dayShows);
		
		
		
	}
	
	@Test
	public void testIfAir2dayShowsAddedOnce() {
		
		HashMap<Integer, Integer> air2dayShowsTimes = new HashMap<>();
		
		ArrayList<Air2dayShow> air2dayShows = new ArrayList<>();
		air2dayShows.addAll(air2dayShowBeanTest.getAllAir2dayShows());
		
		for (Air2dayShow show : air2dayShows) {
			if (!air2dayShowsTimes.containsKey(show.getIdTmdb())) air2dayShowsTimes.put(show.getIdTmdb(), 1);
			else air2dayShowsTimes.put(show.getIdTmdb(), 2);
		}
		System.out.println("################################# " +air2dayShowsTimes.size() + " air today shows.");
		System.out.println(air2dayShowsTimes.toString());
		
		Assert.assertFalse(air2dayShowsTimes.containsValue(2));
	}
	
	
}
