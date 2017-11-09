package com.gnt.movies.GntMovies_web.Rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gnt.movies.beans.UserBean;
import com.gnt.movies.entities.User;

@Path("/user")

public class UserRest {
	
	@Inject
	private UserBean userBean;
	
	@Path("/get/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByUsername(@PathParam("username") String username) {
		return userBean.findUserByUsername(username);
	}
	@Path("/check/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkUsername(@PathParam("username") String username) {
		return userBean.usernameExists(username);
	}
	
	@Path("/login/{username}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@PathParam("username") String username, @PathParam("password") String password) {
		String token=null;
		if(userBean.loginUser(username, password)) {
			token = KeyHolder.issueToken(username);
		}
		return token;
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public String register(User user) {
		String token=null;
		boolean registerUser = userBean.registerUser(user);
		if(registerUser) {
			token = KeyHolder.issueToken(user.getUsername());
		}
		return token;
	}
	
}
