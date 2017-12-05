package com.gnt.appobjects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sissy on 12/5/17.
 */

public class Genre implements Serializable {
    private Integer id;
    private String name;
    private Integer tmdId;
    private ArrayList<Movie> movies;
    private ArrayList<Show> shows;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTmdId() {
        return tmdId;
    }

    public void setTmdId(Integer tmdId) {
        this.tmdId = tmdId;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public void setShows(ArrayList<Show> shows) {
        this.shows = shows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (!getName().equals(genre.getName())) return false;
        return getTmdId().equals(genre.getTmdId());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getTmdId().hashCode();
        return result;
    }
}
