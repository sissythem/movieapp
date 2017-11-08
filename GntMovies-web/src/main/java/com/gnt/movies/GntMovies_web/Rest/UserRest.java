package com.gnt.movies.GntMovies_web.Rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gnt.movies.beans.UserBean;
import com.gnt.movies.entities.User;

@Path("/user")

public class UserRest {
	
	@Inject
	private UserBean userbean;
	
	@Path("/get/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByUsername(@PathParam("username") String username) {
		return userbean.findUserByUsername(username);
	}
	@Path("/check/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkUsername(@PathParam("username") String username) {
		return userbean.usernameExists(username);
	}
	
	@Path("/login/{username}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public boolean login(@PathParam("username") String username, @PathParam("password") String password) {
		return userbean.loginUser(username, password);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi() {
		return "Hi";
	}
	
}
