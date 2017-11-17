package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class ApiImages {

	@SerializedName("backdrops")
	private ArrayList<ApiPostersBackdrops> apiPostersBackdrops;
	@SerializedName("posters")
	private ArrayList<ApiPostersBackdrops> apiPosters;
	
	public ArrayList<ApiPostersBackdrops> getApiBackdrops() {
		return apiPostersBackdrops;
	}
	public void setApiBackdrops(ArrayList<ApiPostersBackdrops> apiPostersBackdrops) {
		this.apiPostersBackdrops = apiPostersBackdrops;
	}
	public ArrayList<ApiPostersBackdrops> getApiPosters() {
		return apiPosters;
	}
	public void setApiPosters(ArrayList<ApiPostersBackdrops> apiPosters) {
		this.apiPosters = apiPosters;
	}
}
