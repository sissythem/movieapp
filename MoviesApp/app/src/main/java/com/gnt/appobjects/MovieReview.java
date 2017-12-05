package com.gnt.appobjects;

import java.io.Serializable;

/**
 * Created by sissy on 12/5/17.
 */

public class MovieReview implements Serializable {

    private Integer id;
    private String comment;
    private Integer rating;
    private Movie movie;
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
