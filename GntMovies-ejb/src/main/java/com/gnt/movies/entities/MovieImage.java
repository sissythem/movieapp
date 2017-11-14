package com.gnt.movies.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="movie_images")
@NamedQueries({
        @NamedQuery(name="MovieImage.findAll", query="SELECT m FROM MovieImage m"),
        @NamedQuery(name="MovieImage.findById", query="SELECT m FROM MovieImage m WHERE m.id = :id"),
        @NamedQuery(name="MovieImage.findByMovieId", query="SELECT m FROM MovieImage m WHERE m.movie.id = :movieId")
})
public class MovieImage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;
	
	public MovieImage() {
		
	}
	
	public MovieImage(Movie movie, String path) {
		this.movie=movie;
		this.imagePath=path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
