package com.gnt.movies.GntMovies_web.Rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.gnt.movies.beans.MovieBean;
import com.gnt.movies.beans.MovieFavoriteBean;
import com.gnt.movies.beans.UserBean;
import com.gnt.movies.dto.UserSessionDto;
import com.gnt.movies.entities.Movie;

@Path("/movie")
public class MovieRest {
	
	@Inject
	private MovieBean movieBean;
	
	@Inject 
	private UserBean userBean;
	
	@Inject MovieFavoriteBean movieFavoriteBean;
	
	@GET
	@Path("/details/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getMovieDetailsById(@HeaderParam("token") String token, @PathParam("id") int id) {
		return movieBean.findMovieById(id);
	}
	
	@GET
	@Path("/details/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Movie getMovieDetailsByTitle(@HeaderParam("token") String token, @PathParam("title") String title) {
		return movieBean.findMovieByTitle(title);
	}

}
