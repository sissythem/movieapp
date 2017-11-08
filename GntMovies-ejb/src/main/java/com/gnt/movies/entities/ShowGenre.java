package com.gnt.movies.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the show_genres database table.
 *
 */
@Entity
@Table(name="show_genres")
@NamedQueries({
	@NamedQuery(name="ShowGenre.findAll", query="SELECT s FROM ShowGenre s"),
	@NamedQuery(name="ShowGenre.findById", query="SELECT s FROM ShowGenre s WHERE s.id = :id"),
	@NamedQuery(name="ShowGenre.findByGenreId", query="SELECT s FROM ShowGenre s WHERE s.genre.id = :genreId"),
	@NamedQuery(name="ShowGenre.findByShowId", query="SELECT s FROM ShowGenre s WHERE s.show.id = :showId")
})
public class ShowGenre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    //bi-directional many-to-one association to Genre
    @ManyToOne
    @JoinColumn(name="genreId")
    private Genre genre;

    //bi-directional many-to-one association to Show
    @ManyToOne
    @JoinColumn(name="showId")
    private Show show;

    public ShowGenre() {
    }

    public ShowGenre(Show show, Genre genre) {
		this.show = show;
		this.genre = genre;
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

    public Show getShow() {
        return this.show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

}