package com.gnt.movies.GntMovies_web.Rest;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gnt.movies.beans.OnTheAirShowBean;
import com.gnt.movies.entities.OnTheAirShow;

@Path("/ontheair")
public class OnTheAirShowRest {
	
	@Inject
	private OnTheAirShowBean onTheAirShowBean;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<OnTheAirShow> getOnTheAirShows(@HeaderParam("token") String token) {
		return onTheAirShowBean.getAllOnTheAirShows();
	}
}
