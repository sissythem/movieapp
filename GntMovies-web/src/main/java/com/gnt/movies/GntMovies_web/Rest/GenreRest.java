package com.gnt.movies.GntMovies_web.Rest;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gnt.movies.beans.GenreBean;
import com.gnt.movies.entities.Genre;

@Path("/genre")
public class GenreRest {
	@Inject
	private GenreBean genreBean;
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Genre> getAllGenres(@HeaderParam("token") String token) {
		return (ArrayList<Genre>)genreBean.getAllGenres();
	}
}
