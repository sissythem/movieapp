package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class ApiImages {

	@SerializedName("backdrops")
	private ArrayList<ApiBackdrops> apiBackdrops;
	@SerializedName("posters")
	private ArrayList<ApiPosters> apiPosters;
	
	public ArrayList<ApiBackdrops> getApiBackdrops() {
		return apiBackdrops;
	}
	public void setApiBackdrops(ArrayList<ApiBackdrops> apiBackdrops) {
		this.apiBackdrops = apiBackdrops;
	}
	public ArrayList<ApiPosters> getApiPosters() {
		return apiPosters;
	}
	public void setApiPosters(ArrayList<ApiPosters> apiPosters) {
		this.apiPosters = apiPosters;
	}
	
	
}
