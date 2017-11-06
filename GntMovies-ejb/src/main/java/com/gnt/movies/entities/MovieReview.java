package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the movie_reviews database table.
 *
 */
@Entity
@Table(name="movie_reviews")
@NamedQueries({
        @NamedQuery(name = "MovieReview.findAll", query = "SELECT m FROM MovieReview m"),
        @NamedQuery(name = "MovieReview.findById", query = "SELECT m FROM MovieReview m WHERE m.id = :id"),
        @NamedQuery(name = "MovieReview.findByRating", query = "SELECT m FROM MovieReview m WHERE m.rating = :rating")
})
public class MovieReview implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Lob
    private String comment;

    private int rating;

    //bi-directional many-to-one association to Movie
    @ManyToOne
    @JoinColumn(name="movieId")
    private Movie movie;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public MovieReview() {
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