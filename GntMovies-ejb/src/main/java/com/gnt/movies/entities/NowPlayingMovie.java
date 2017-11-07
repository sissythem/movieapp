package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the now_playing_movies database table.
 *
 */
@Entity
@Table(name="now_playing_movies")
@NamedQueries({
        @NamedQuery(name = "NowPlayingMovie.findAll", query = "SELECT n FROM NowPlayingMovie n"),
        @NamedQuery(name = "NowPlayingMovie.findById", query = "SELECT n FROM NowPlayingMovie n WHERE n.id = :id"),
        @NamedQuery(name = "NowPlayingMovie.findByIdTmdb", query = "SELECT n FROM NowPlayingMovie n WHERE n.idTmdb = :idTmdb"),
        @NamedQuery(name = "NowPlayingMovie.findByMovieId", query = "SELECT n FROM NowPlayingMovie n WHERE n.movie.id = :movieId")
})
public class NowPlayingMovie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private int idTmdb;

    @OneToOne(targetEntity=Movie.class,mappedBy="nowPlayingMovie")
    private Movie movie;

    public NowPlayingMovie() {
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