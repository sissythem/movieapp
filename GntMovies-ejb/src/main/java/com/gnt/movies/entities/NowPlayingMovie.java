package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "now_playing_movies")
@NamedQueries({ 
//	@NamedQuery(name = "NowPlayingMovie.findAll", query = "SELECT n FROM NowPlayingMovie n"),
		@NamedQuery(name = "NowPlayingMovie.findById", query = "SELECT n FROM NowPlayingMovie n WHERE n.id = :id"),
		@NamedQuery(name = "NowPlayingMovie.findByIdTmdb", query = "SELECT n FROM NowPlayingMovie n WHERE n.idTmdb = :idTmdb"),
		@NamedQuery(name = "NowPlayingMovie.getAllIdTmdb", query = "SELECT n.idTmdb FROM NowPlayingMovie n"),
		@NamedQuery(name = "NowPlayingMovie.findByMovieId", query = "SELECT n FROM NowPlayingMovie n WHERE n.movie.id = :movieId"),
		@NamedQuery(name = "NowPlayingMovie.deleteByIdTmdb", query = "DELETE FROM NowPlayingMovie n WHERE n.idTmdb = :idTmdb") })
@NamedNativeQueries({
		@NamedNativeQuery(name = "NowPlayingMovie.findAll", query = "SELECT m.id, m.originalTitle, m.posterPath, m.voteAverage, m.voteCount FROM now_playing_movies as npm inner join movies as m on npm.movieId = m.id", resultSetMapping = "MovieListItemDto")
})
public class NowPlayingMovie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer idTmdb;

	@OneToOne
	@JoinColumn(name = "movieId")
	private Movie movie;

	public NowPlayingMovie() {
	}

	public NowPlayingMovie(Integer idTmdb) {
		this.idTmdb = idTmdb;
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