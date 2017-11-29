package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="upcoming_movies")
@NamedQueries({
        @NamedQuery(name="UpcomingMovie.findAll", query="SELECT u FROM UpcomingMovie u "),
        @NamedQuery(name="UpcomingMovie.findAllMovies", query="SELECT u.movie FROM UpcomingMovie u "),
        @NamedQuery(name = "UpcomingMovie.findById", query = "SELECT u FROM UpcomingMovie u WHERE u.id = :id"),
        @NamedQuery(name = "UpcomingMovie.findByIdTmdb", query = "SELECT u FROM UpcomingMovie u WHERE u.idTmdb = :idTmdb"),
        @NamedQuery(name="UpcomingMovie.getAllIdTmdb", query = "SELECT u.idTmdb FROM UpcomingMovie u"),
        @NamedQuery(name="UpcomingMovie.findByMovieId", query = "SELECT u FROM UpcomingMovie u WHERE u.movie.id = :movieId"),
        @NamedQuery(name="UpcomingMovie.deleteByIdTmdb", query = "DELETE FROM UpcomingMovie u WHERE u.idTmdb = :idTmdb")
})
public class UpcomingMovie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer idTmdb;

    @OneToOne
    @JoinColumn(name="movieId")
    private Movie movie;

    public UpcomingMovie() {
    }
    
    public UpcomingMovie(Integer idTmdb) {
    	this.idTmdb=idTmdb;
    }
    public UpcomingMovie(Integer idTmdb, Movie movie) {
    	this.idTmdb=idTmdb;
    	this.movie=movie;
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