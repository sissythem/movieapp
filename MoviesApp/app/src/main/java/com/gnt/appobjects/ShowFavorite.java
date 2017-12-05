package com.gnt.appobjects;

import java.io.Serializable;

/**
 * Created by sissy on 12/5/17.
 */

public class ShowFavorite implements Serializable {

    private Integer id;
    private Show show;
    private User user;

    public ShowFavorite() {
    }

    public ShowFavorite(User user, Show show) {
        this.user = user;
        this.show = show;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
