package com.gnt.movies.theMovieDB;

import com.google.gson.annotations.SerializedName;

public class CastCrewAPI {
	
	@SerializedName("name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CastCrewAPI [name=" + name + "]";
	}
}
