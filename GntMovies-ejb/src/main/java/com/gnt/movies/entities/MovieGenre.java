package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the movie_genres database table.
 *
 */
@Entity
@Table(name="movie_genres")
@NamedQueries({
        @NamedQuery(name="MovieGenre.findAll", query="SELECT m FROM MovieGenre m"),
        @NamedQuery(name="MovieGenre.findById", query="SELECT m FROM MovieGenre m WHERE m.id = :id"),
        @NamedQuery(name="MovieGenre.findByGenreId", query="SELECT m FROM MovieGenre m WHERE m.genre.id = :genreId"),
        @NamedQuery(name="MovieGenre.findByMovieId", query="SELECT m FROM MovieGenre m WHERE m.movie.id = :movieId")
})

public class MovieGenre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="genreId")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name="movieId")
    private Movie movie;

    public MovieGenre() {
    }
    
    public MovieGenre(Movie movie, Genre genre) {
    	this.movie=movie;
    	this.genre=genre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}