package com.gnt.appobjects;

import java.io.Serializable;

/**
 * Created by sissy on 12/5/17.
 */

public class UpcomingMovie implements Serializable {
    private Integer id;
    private Integer idTmdb;
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
