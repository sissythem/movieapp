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
@Table(name="show_images")
@NamedQueries({
	@NamedQuery(name="ShowImage.findAll", query="SELECT s FROM ShowImage s"),
	@NamedQuery(name="ShowImage.findById", query="SELECT s FROM ShowImage s WHERE s.id = :id"),
	@NamedQuery(name="ShowImage.findByShowId", query="SELECT s FROM ShowImage s WHERE s.show.id = :showId")
})
public class ShowImage implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@ManyToOne
    @JoinColumn(name="show_id")
    private Show show;
	
	public ShowImage() {
		
	}

	public ShowImage(Show show, String path) {
		this.show = show;
		this.imagePath = path;
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

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}
	
}
