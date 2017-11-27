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
    private Integer id;
	@SerializedName("file_path")
	@Column(name="filePath")
	private String filePath;
	@SerializedName("aspect_ratio")
	@Column(name="aspectRatio")
	private Double aspectRatio;
	@SerializedName("height")
	@Column(name="height")
	private Integer height;
	@SerializedName("width")
	@Column(name="width")
	private Integer width;
		
	public Image() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Double getAspectRatio() {
		return aspectRatio;
	}
	public void setAspectRatio(Double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	
	
	
}
