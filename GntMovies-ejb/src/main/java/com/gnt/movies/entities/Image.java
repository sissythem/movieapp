package com.gnt.movies.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
@Table(name="images")
@NamedQueries({
        @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
        @NamedQuery(name = "Image.findById", query = "SELECT i FROM Image i WHERE i.id = :id")
})
public class Image implements Serializable{
    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose(deserialize = false)
    @Column(name="id")
    private int id;
	@SerializedName("file_path")
	@Column(name="filePath")
	private String filePath;
	@SerializedName("aspect_ratio")
	@Column(name="aspectRatio")
	private double aspectRatio;
	@SerializedName("height")
	@Column(name="height")
	private int height;
	@SerializedName("width")
	@Column(name="width")
	private int width;
		
	public Image() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public double getAspectRatio() {
		return aspectRatio;
	}
	public void setAspectRatio(double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	
	
	
}
