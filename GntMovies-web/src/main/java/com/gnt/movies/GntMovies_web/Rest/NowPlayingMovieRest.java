package com.gnt.movies.GntMovies_web.Rest;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gnt.movies.beans.NowPlayingMovieBean;
import com.gnt.movies.entities.NowPlayingMovie;

@Path("/nowplaying")
public class NowPlayingMovieRest {

	@Inject
	private NowPlayingMovieBean nowPlayingMovieBean;
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<NowPlayingMovie> getNowPlayingMovies(@HeaderParam("token") String token) {
		return nowPlayingMovieBean.getAllNowPlayingMovies();
	}
}
