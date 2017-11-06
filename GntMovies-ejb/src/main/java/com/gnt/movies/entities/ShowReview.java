package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the show_reviews database table.
 *
 */
@Entity
@Table(name="show_reviews")
@NamedQueries({
        @NamedQuery(name = "ShowReview.findAll", query = "SELECT s FROM ShowReview s"),
        @NamedQuery(name = "ShowReview.findById", query = "SELECT s FROM ShowReview s WHERE s.id = :id"),
        @NamedQuery(name = "ShowReview.findByRating", query = "SELECT s FROM ShowReview s WHERE s.rating = :rating"),
        @NamedQuery(name = "ShowReview.findByUserId", query = "SELECT s FROM ShowReview s WHERE s.user.id = :id"),
        @NamedQuery(name = "ShowReview.findByShowId", query = "SELECT s FROM ShowReview s WHERE s.show.id = :id")
})
public class ShowReview implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Lob
    private String comment;

    private int rating;

    @ManyToOne
    @JoinColumn(name="showId")
    private Show show;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public ShowReview() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
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