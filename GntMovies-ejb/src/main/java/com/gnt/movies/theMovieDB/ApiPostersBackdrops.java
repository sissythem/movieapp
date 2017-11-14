package com.gnt.movies.theMovieDB;

import com.google.gson.annotations.SerializedName;

public class ApiPostersBackdrops {
	
	@SerializedName("aspect_ratio")
	private double aspectRatio;
	@SerializedName("file_path")
	private String filePath;
	@SerializedName("height")
	private int height;
	@SerializedName("iso_639_1")
	private String iso6391;
	@SerializedName("vote_average")
	private double voteAverage;
	@SerializedName("vote_count")
	private int voteCount;
	@SerializedName("width")
	private int width;
	
	public double getAspectRatio() {
		return aspectRatio;
	}
	public void setAspectRatio(double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getIso6391() {
		return iso6391;
	}
	public void setIso6391(String iso6391) {
		this.iso6391 = iso6391;
	}
	public double getVoteAverage() {
		return voteAverage;
	}
	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
}
