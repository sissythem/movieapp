package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "movie_reviews")
@NamedQueries({ @NamedQuery(name = "MovieReview.findAll", query = "SELECT m FROM MovieReview m"),
		@NamedQuery(name = "MovieReview.findById", query = "SELECT m FROM MovieReview m WHERE m.id = :id"),
		@NamedQuery(name = "MovieReview.findByRating", query = "SELECT m FROM MovieReview m WHERE m.rating = :rating"),
		@NamedQuery(name = "MovieReview.findByUserId", query = "SELECT m FROM MovieReview m WHERE m.user.id = :userId"),
		@NamedQuery(name = "MovieReview.findByMovieId", query = "SELECT m FROM MovieReview m WHERE m.movie.id = :movieId"),
		@NamedQuery(name = "MovieReview.findMovieReview", query = "SELECT m FROM MovieReview m WHERE m.movie.id = ?1 AND m.user.id = ?2") })
public class MovieReview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Lob
	private String comment;

	private Integer rating;

	@ManyToOne
	@JoinColumn(name = "movieId")
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public MovieReview() {
	}

	public MovieReview(User user, Movie movie) {
		this.user = user;
		this.movie = movie;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}