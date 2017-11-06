package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the upcoming_movies database table.
 *
 */
@Entity
@Table(name="upcoming_movies")
@NamedQueries({
        @NamedQuery(name="UpcomingMovie.findAll", query="SELECT u FROM UpcomingMovie u"),
        @NamedQuery(name = "UpcomingMovie.findById", query = "SELECT u FROM UpcomingMovie u WHERE u.id = :id"),
        @NamedQuery(name = "UpcomingMovie.findByIdTmdb", query = "SELECT u FROM UpcomingMovie u WHERE u.idTmdb = :idTmdb"),
        @NamedQuery(name="UpcomingMovie.findByMovieId", query = "SELECT u FROM UpcomingMovie u WHERE u.movie.id = :id")
})

public class UpcomingMovie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private int idTmdb;

    @OneToOne(mappedBy="upcomingMovie")
    private Movie movie;

    public UpcomingMovie() {
    }
    
    public UpcomingMovie(int idTmdb, Movie movie) {
    	this.idTmdb=idTmdb;
    	this.movie=movie;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTmdb() {
        return this.idTmdb;
    }

    public void setIdTmdb(int idTmdb) {
        this.idTmdb = idTmdb;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}