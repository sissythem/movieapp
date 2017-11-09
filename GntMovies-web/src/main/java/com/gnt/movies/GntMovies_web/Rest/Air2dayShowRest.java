package com.gnt.movies.GntMovies_web.Rest;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gnt.movies.beans.Air2dayShowBean;
import com.gnt.movies.entities.Air2dayShow;

@Path("/air2day")
public class Air2dayShowRest {
	
	@Inject
	private Air2dayShowBean air2dayShowBean;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Air2dayShow> getAir2dayShows(@HeaderParam("token") String token) {
		return air2dayShowBean.getAllAir2dayShows();
	}
}
