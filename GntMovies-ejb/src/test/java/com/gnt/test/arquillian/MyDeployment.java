package com.gnt.test.arquillian;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

public class MyDeployment {
	
	public static WebArchive getWar() {
		return ShrinkWrap.create(WebArchive.class)
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
	}

	public static JavaArchive getJar() {
		return ShrinkWrap.create(JavaArchive.class)
				.addPackages(true, "com.gnt.movies")
	            .addAsManifestResource("test-persistence.xml", "persistence.xml")
	            .addAsManifestResource("test-ds.xml")
	            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

}
