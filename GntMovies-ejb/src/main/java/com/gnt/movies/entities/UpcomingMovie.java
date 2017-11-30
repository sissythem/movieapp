package com.gnt.movies.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "upcoming_movies")
@NamedQueries({ @NamedQuery(name = "UpcomingMovie.findAll", query = "SELECT u FROM UpcomingMovie u "),
		// @NamedQuery(name="UpcomingMovie.findAllMovies", query="SELECT new u.movie.id,
		// u.movie.originalTitle, u.movie.posterPath, u.movie.voteAverage,
		// u.movie.voteCount FROM UpcomingMovie u "),
		@NamedQuery(name = "UpcomingMovie.findById", query = "SELECT u FROM UpcomingMovie u WHERE u.id = :id"),
		@NamedQuery(name = "UpcomingMovie.findByIdTmdb", query = "SELECT u FROM UpcomingMovie u WHERE u.idTmdb = :idTmdb"),
		@NamedQuery(name = "UpcomingMovie.getAllIdTmdb", query = "SELECT u.idTmdb FROM UpcomingMovie u"),
		@NamedQuery(name = "UpcomingMovie.findByMovieId", query = "SELECT u FROM UpcomingMovie u WHERE u.movie.id = :movieId"),
		@NamedQuery(name = "UpcomingMovie.deleteByIdTmdb", query = "DELETE FROM UpcomingMovie u WHERE u.idTmdb = :idTmdb") })
@NamedNativeQueries({
	@NamedNativeQuery(name = "UpcomingMovie.findAllMovies", query = "SELECT m.id, m.originalTitle, m.posterPath, m.voteAverage, m.voteCount FROM upcoming_movies as um inner join movies as m on um.movieId = m.id", resultSetMapping = "MovieListItemDto")

})
public class UpcomingMovie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer idTmdb;

	@OneToOne
	@JoinColumn(name = "movieId")
	private Movie movie;

	public UpcomingMovie() {
	}

	public UpcomingMovie(Integer idTmdb) {
		this.idTmdb = idTmdb;
	}

	public UpcomingMovie(Integer idTmdb, Movie movie) {
		this.idTmdb = idTmdb;
		this.movie = movie;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdTmdb() {
		return this.idTmdb;
	}

	public void setIdTmdb(Integer idTmdb) {
		this.idTmdb = idTmdb;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}