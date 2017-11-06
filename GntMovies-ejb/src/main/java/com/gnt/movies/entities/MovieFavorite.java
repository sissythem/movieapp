package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the movie_favorites database table.
 *
 */
@Entity
@Table(name="movie_favorites")
@NamedQueries({
        @NamedQuery(name = "MovieFavorite.findAll", query = "SELECT m FROM MovieFavorite m"),
        @NamedQuery(name = "MovieFavorite.findById", query = "SELECT m FROM MovieFavorite m WHERE m.id = :id")
})
public class MovieFavorite implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="movieid")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public MovieFavorite() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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