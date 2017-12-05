package com.gnt.appobjects;

import java.io.Serializable;

/**
 * Created by sissy on 12/5/17.
 */

public class MovieFavorite implements Serializable {

    private Integer id;
    private Movie movie;
    private User user;

    public MovieFavorite() {
    }

    public MovieFavorite(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
