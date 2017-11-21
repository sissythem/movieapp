package com.gnt.movies.testing;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.GenreBean;
import com.gnt.movies.beans.SchedulerBean;
import com.gnt.movies.entities.Genre;

@RunWith(Arquillian.class)
public class GenreTester {

	@EJB
	GenreBean genreBean;
	
	@EJB
	SchedulerBean sb;
	
	@Deployment
	public static WebArchive createDeployment() throws IOException 
	{
/*		// You can use war packaging...
        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(GenreBean.class.getPackage())
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource("wildfly-ds.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        // or jar packaging...
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
            .addPackage(GenreBean.class.getPackage())
            .addAsManifestResource("test-persistence.xml", "persistence.xml")
            .addAsManifestResource("wildfly-ds.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        return jar;*/
        
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
		sb.update();
	}

	@Test
	public void testProximity() {
/*		Genre genre = genreBean.findGenreByName("Action");
		assertEquals(genre.getName(), "Action");
		genreBean.editGenre(genre);
		assertEquals(genre.getName(), "Action");*/
		
		HashMap<String, Integer> genreTimes = new HashMap<>();
		
		ArrayList<Genre> genres = new ArrayList<>();
		genres.addAll(genreBean.getAllGenres());
		
		for (Genre genre : genres) {
			
			if (!genreTimes.containsKey(genre.getName())) genreTimes.put(genre.getName(), 1);
			else genreTimes.put(genre.getName(), 2);
		}
		System.out.println(genreTimes.toString());
		
		Assert.assertFalse(genreTimes.containsValue(2));
	}
}
