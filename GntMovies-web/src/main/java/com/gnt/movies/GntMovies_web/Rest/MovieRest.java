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
import com.gnt.movies.beans.MovieBean;
import com.gnt.movies.beans.MovieFavoriteBean;
import com.gnt.movies.beans.MovieReviewBean;
import com.gnt.movies.beans.UserBean;
import com.gnt.movies.dto.UserSessionDto;
import com.gnt.movies.entities.Movie;

@Path("/movie")
public class MovieRest {

	@Inject
	private MovieBean movieBean;

	@Inject
	private UserBean userBean;

	@Inject
	private MovieFavoriteBean movieFavoriteBean;

	@Inject
	private MovieReviewBean movieReviewBean;

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

	@POST
	@Path("/addMovieFav")
	@Consumes(MediaType.TEXT_PLAIN)
	public boolean addMovieFavorite(@HeaderParam("token") String token, Integer movieId,
			@Context HttpServletRequest request) {

		if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {

			HttpSession session = request.getSession();
			UserSessionDto user = (UserSessionDto) session.getAttribute("userDto");
			movieFavoriteBean.addMovieFavorite(userBean.findUserByUsername(user.getUsername()),
					movieBean.findMovieById(movieId));
			return true;
		}
		return false;
	}

	@POST
	@Path("/removeMovieFav")
	@Consumes(MediaType.TEXT_PLAIN)
	public boolean removeMovieFavorite(@HeaderParam("token") String token, Integer movieId,
			@Context HttpServletRequest request) {

		if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {

			HttpSession session = request.getSession();
			UserSessionDto user = (UserSessionDto) session.getAttribute("userDto");
			movieFavoriteBean.removeMovieFavorite(userBean.findUserByUsername(user.getUsername()),
					movieBean.findMovieById(movieId));
			return true;
		}
		return false;
	}

	@POST
	@Path("/addMovieReview")
	@Consumes(MediaType.TEXT_PLAIN)
	public boolean addMovieReview(@HeaderParam("token") String token, Integer movieId, @Context HttpServletRequest request) {

		if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {

			HttpSession session = request.getSession();
			UserSessionDto user = (UserSessionDto) session.getAttribute("userDto");

			if (movieReviewBean.getMovieReviewFromUser(movieId, user.getId()) == null) {
				movieReviewBean.addMovieReview(userBean.findUserByUsername(user.getUsername()),
						movieBean.findMovieById(movieId));
				return true;
			} else {
				movieReviewBean.updateMovieReview(userBean.findUserByUsername(user.getUsername()),
						movieBean.findMovieById(movieId));
				return true;
			}
		}
		return false;
	}

	@POST
	@Path("/removeMovieReview")
	@Consumes(MediaType.TEXT_PLAIN)
	public boolean removeMovieReview(@HeaderParam("token") String token, Integer movieId, @Context HttpServletRequest request) {
		
		if (AuthenticationToken.isTokenValid(token, this.getClass().getSimpleName())) {

			HttpSession session = request.getSession();
			UserSessionDto user = (UserSessionDto) session.getAttribute("userDto");
			
			movieReviewBean.deleteMovieReview(userBean.findUserByUsername(user.getUsername()), 
					movieBean.findMovieById(movieId));
			
			return true;
		}
			
		return false;
	}


}
