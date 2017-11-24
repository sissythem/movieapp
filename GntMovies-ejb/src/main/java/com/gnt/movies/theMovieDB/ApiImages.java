package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.gnt.movies.entities.Image;
import com.google.gson.annotations.SerializedName;

public class ApiImages {

	@SerializedName("backdrops")
	private ArrayList<Image> image;
	@SerializedName("posters")
	private ArrayList<Image> apiPosters;
	
	public ArrayList<Image> getApiBackdrops() {
		return image;
	}
	public void setApiBackdrops(ArrayList<Image> image) {
		this.image = image;
	}
	public ArrayList<Image> getApiPosters() {
		return apiPosters;
	}
	public void setApiPosters(ArrayList<Image> apiPosters) {
		this.apiPosters = apiPosters;
	}
}
