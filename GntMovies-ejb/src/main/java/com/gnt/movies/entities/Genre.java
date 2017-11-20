package com.gnt.movies.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * The persistent class for the genres database table.
 *
 */
@Entity
@Table(name="genres")
@NamedQueries({
        @NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"),
        @NamedQuery(name = "Genre.findAllNames", query = "SELECT g.name FROM Genre g"),
        @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.myid = :id"),
        @NamedQuery(name = "Genre.findByName", query = "SELECT g FROM Genre g WHERE g.name = :name")
})
public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Expose(deserialize = false)
    @Column(name="id")
    private int myid;
    @SerializedName("name")
    private String name;
    
    @SerializedName("id")
    private int idm;
    
    @ManyToMany(fetch=FetchType.LAZY)
    private List<Movie> movies;

    public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	public Genre() {
    }
    public Genre(String name) {
    	this.name=name;
    }
    public Genre(String name,int idm) {
    	this.name=name;
    	this.idm=idm;
    }
    public int getId() {
        return this.myid;
    }

    public void setId(int id) {
        this.myid = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
	public int getIdm() {
		return idm;
	}
	
	public void setIdm(int idm) {
		this.idm = idm;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idm;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (idm != other.idm)
			return false;
		return true;
	}

    
}