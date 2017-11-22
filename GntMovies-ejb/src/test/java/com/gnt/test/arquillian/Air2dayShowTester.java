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
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.Air2dayShowBean;
import com.gnt.movies.beans.GenreBean;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.ApiClient;

@RunWith(Arquillian.class)
public class Air2dayShowTester {

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
	
	@Test
	public void testGetAir2dayShows() {
		ApiClient.init();
		Air2dayShowBean.init();
		HashSet<Genre> genres = ApiCalls.getGenres();
		genreBean.addGenres(genres);
		air2dayShowBean.findAllIdTmdb();
		
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
