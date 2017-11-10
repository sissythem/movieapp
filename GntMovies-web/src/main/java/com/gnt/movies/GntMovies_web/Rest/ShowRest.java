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

import com.gnt.movies.GntMovies_web.Rest.Utils.AuthenticationToken;
import com.gnt.movies.beans.ShowBean;
import com.gnt.movies.beans.ShowFavoriteBean;
import com.gnt.movies.beans.ShowReviewBean;
import com.gnt.movies.beans.UserBean;
import com.gnt.movies.dto.UserSessionDto;
import com.gnt.movies.entities.Show;

@Path("/show")
public class ShowRest {
	
	@Inject
	private ShowBean showBean;

	@Inject 
	private UserBean userBean;
	
	@Inject 
	private ShowReviewBean showReviewBean;
	
	@Inject
	private ShowFavoriteBean showFavoriteBean;
	
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
	
	@POST
	@Path("/addShowFav")
	@Consumes(MediaType.TEXT_PLAIN)
	public boolean addShowFavorite(@HeaderParam("token") String token, Integer showId, @Context HttpServletRequest request) {
		
		if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {
			
			HttpSession session = request.getSession();
			UserSessionDto user = (UserSessionDto) session.getAttribute("userDto");
			showFavoriteBean.addShowFavorite(userBean.findUserByUsername(user.getUsername()), showBean.findShowById(showId));
			return true;
		}
		return false;
	}
	
	@POST
	@Path("/removeShowFav")
	@Consumes(MediaType.TEXT_PLAIN)
	public boolean removeShowFavorite(@HeaderParam("token") String token, Integer showId, @Context HttpServletRequest request) {
		
		if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {
			
			HttpSession session = request.getSession();
			UserSessionDto user = (UserSessionDto) session.getAttribute("userDto");
			showFavoriteBean.removeShowFavorite(userBean.findUserByUsername(user.getUsername()), showBean.findShowById(showId));
			return true;
		}
		return false;
	}
	
	@POST
	@Path("/removeShowReview")
	@Consumes(MediaType.TEXT_PLAIN)
	public boolean removeShowReview(@HeaderParam("token") String token, Integer movieId, @Context HttpServletRequest request) {
		
		if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {

			HttpSession session = request.getSession();
			UserSessionDto user = (UserSessionDto) session.getAttribute("userDto");
			
			showReviewBean.deleteShowReview(userBean.findUserByUsername(user.getUsername()), 
					showBean.findShowById(movieId));
			
			return true;
		}
			
		return false;
	}
	
	@POST
	@Path("/addShowReview")
	@Consumes(MediaType.TEXT_PLAIN)
	public boolean addShowReview(@HeaderParam("token") String token, Integer movieId, @Context HttpServletRequest request) {
		
		if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {

			HttpSession session = request.getSession();
			UserSessionDto user = (UserSessionDto) session.getAttribute("userDto");
			
			showReviewBean.addShowReview(userBean.findUserByUsername(user.getUsername()), 
					showBean.findShowById(movieId));
			
			return true;
		}
			
		return false;
	}
	
}
