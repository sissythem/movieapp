package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the genres database table.
 *
 */
@Entity
@Table(name="genres")
@NamedQueries({
        @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
        @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.id = :id"),
        @NamedQuery(name = "Genre.findByName", query = "SELECT g FROM Genre g WHERE g.name = :name")
})
public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy="genre")
    private List<MovieGenre> movieGenres;

    @OneToMany(mappedBy="genre")
    private List<ShowGenre> showGenres;

    public Genre() {
    }
    public Genre(String name) {
    	this.name=name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieGenre> getMovieGenres() {
        return this.movieGenres;
    }

    public void setMovieGenres(List<MovieGenre> movieGenres) {
        this.movieGenres = movieGenres;
    }

    public MovieGenre addMovieGenre(MovieGenre movieGenre) {
        getMovieGenres().add(movieGenre);
        movieGenre.setGenre(this);

        return movieGenre;
    }

    public MovieGenre removeMovieGenre(MovieGenre movieGenre) {
        getMovieGenres().remove(movieGenre);
        movieGenre.setGenre(null);

        return movieGenre;
    }

    public List<ShowGenre> getShowGenres() {
        return this.showGenres;
    }

    public void setShowGenres(List<ShowGenre> showGenres) {
        this.showGenres = showGenres;
    }

    public ShowGenre addShowGenre(ShowGenre showGenre) {
        getShowGenres().add(showGenre);
        showGenre.setGenre(this);

        return showGenre;
    }

    public ShowGenre removeShowGenre(ShowGenre showGenre) {
        getShowGenres().remove(showGenre);
        showGenre.setGenre(null);

        return showGenre;
    }

}