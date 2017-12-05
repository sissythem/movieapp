package com.gnt.appobjects;

import java.io.Serializable;

/**
 * Created by sissy on 12/5/17.
 */

public class ShowReview implements Serializable {

    private Integer id;
    private String comment;
    private Integer rating;
    private Show show;
    private User user;

    public ShowReview() {
    }

    public ShowReview(User user, Show show) {
        this.user = user;
        this.show = show;
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

    public Show getShow() {
        return this.show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
