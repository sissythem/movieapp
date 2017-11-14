package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class ApiCredits {

	@SerializedName("cast")
	private ArrayList<ApiCastCrew> cast;
	@SerializedName("crew")
	private ArrayList<ApiCastCrew> crew;
	
	public ArrayList<ApiCastCrew> getCast() {
		return cast;
	}
	public void setCast(ArrayList<ApiCastCrew> cast) {
		this.cast = cast;
	}
	public ArrayList<ApiCastCrew> getCrew() {
		return crew;
	}
	public void setCrew(ArrayList<ApiCastCrew> crew) {
		this.crew = crew;
	}
}
