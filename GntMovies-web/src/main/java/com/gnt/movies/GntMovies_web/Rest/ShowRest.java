package com.gnt.movies.GntMovies_web.Rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gnt.movies.beans.ShowBean;
import com.gnt.movies.entities.Show;

@Path("/show")
public class ShowRest {
	
	@Inject
	private ShowBean showBean;
	
	@GET
	@Path("/details/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Show getShowDetailsById(@HeaderParam("token") String token, @PathParam("id") int id) {
		return showBean.findShowById(id);
	}
	
	@GET
	@Path("/details/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Show getShowDetailsByName(@HeaderParam("token") String token, @PathParam("title") String name) {
		return showBean.findShowByName(name);
	}
}
