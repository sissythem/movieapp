package com.gnt.movies.testing;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.gnt.movies.beans.GenreBean;
import com.gnt.movies.entities.Genre;

@RunWith(Arquillian.class)
public class GenreTester {

	@EJB
	GenreBean genreBean;

	@Deployment
	public static JavaArchive createDeployment() throws IOException {
		// You can use war packaging...
		WebArchive war = ShrinkWrap.create(WebArchive.class, "testGenreBean.war").addPackages(true, "com.gnt.movies")
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml").addAsWebInfResource("wildfly-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		// or jar packaging...
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addPackages(true, "com.gnt.movies")
				.addAsManifestResource("test-persistence.xml", "persistence.xml")
				.addAsManifestResource("wildfly-ds.xml").addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		return jar;
	}

	@Test
	// @InSequence(1)
	public void testProximity() {
		Genre genre = genreBean.findGenreByName("Action");
		assertEquals(genre.getName(), "Action");
		// genreBean.editGenre(genre);
		assertEquals(genre.getName(), "Action");
	}
}
