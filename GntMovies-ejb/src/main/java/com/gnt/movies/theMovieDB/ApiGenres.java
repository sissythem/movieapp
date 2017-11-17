package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.gnt.movies.entities.Genre;
import com.google.gson.annotations.SerializedName;

public class ApiGenres {
	
	@SerializedName("genres")
	ArrayList<Genre> genres;

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
	}
	
}
