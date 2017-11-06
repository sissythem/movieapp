package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the show_favorites database table.
 *
 */
@Entity
@Table(name="show_favorites")
@NamedQueries({
        @NamedQuery(name = "ShowFavorite.findAll", query = "SELECT s FROM ShowFavorite s"),
        @NamedQuery(name = "ShowFavorite.findById", query = "SELECT s FROM ShowFavorite s WHERE s.id = :id"),
        @NamedQuery(name="ShowFavorite.findByUserId", query="SELECT s FROM ShowFavorite s WHERE s.user.id = :userId"),
        @NamedQuery(name="ShowFavorite.findByShowId", query="SELECT s FROM ShowFavorite s WHERE s.show.id = :showId")
})
public class ShowFavorite implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    //bi-directional many-to-one association to Show
    @ManyToOne
    @JoinColumn(name="showid")
    private Show show;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public ShowFavorite() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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