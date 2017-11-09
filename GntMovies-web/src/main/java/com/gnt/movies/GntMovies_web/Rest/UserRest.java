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

import com.gnt.movies.GntMovies_web.Rest.Utils.UserLogin;
import com.gnt.movies.beans.UserBean;
import com.gnt.movies.dto.UserSessionDto;
import com.gnt.movies.entities.User;

@Path("/user")

public class UserRest {

	@Inject
	private UserBean userBean;

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String login(UserLogin userLogin, @Context HttpServletRequest request) {
		System.out.println(userLogin.getUsername() + " " + userLogin.getPassword());
		String token = null;
		if (userBean.loginUser(userLogin.getUsername(), userLogin.getPassword())) {
			token = AuthenticationToken.issueToken(userLogin.getUsername());
			UserSessionDto user = userBean.findUserDtoByUsername(userLogin.getUsername());
			HttpSession session = request.getSession();
			session.setAttribute("userDto", user);
		}
		return token;
	}

	@Path("/login/{token}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean loginWithToken(@PathParam("token") String token) {
		if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {
			userBean.loginToken(token);

			return true;
		}
		return false;
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean register(User user) {
		return userBean.registerUser(user);
	}

	@Path("/getUser/{username}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserSessionDto getUserByUsername(@HeaderParam("token") String token,
			@PathParam("username") String username) {
		UserSessionDto user = null;
		System.out.println("token:" + token);
		System.out.println("Classname:" + this.getClass().getSimpleName());
		if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {
			user = userBean.findUserDtoByUsername(username);
		}
		return user;
	}

	@Path("/sayHi")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi(@HeaderParam("token") String token, @Context HttpServletRequest request) {
		UserSessionDto user = null;
		// System.out.println("token:"+token);
		if (token != null) {
			System.out.println("Classname:" + this.getClass().getSimpleName());
			if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {
				HttpSession session = request.getSession();
				user = (UserSessionDto) session.getAttribute("userDto");
				return "Hi " + user.getUsername();
			}
		}
		return "Sorry Please loging...";
	}

}
