package com.gnt.movies.theMovieDB;

import com.google.gson.annotations.SerializedName;

public class UpcomingMovieAPI {
	 @SerializedName("vote_count")
	 private int voteCount;
	 @SerializedName("id")
	 private int id;
	 @SerializedName("vote_average")
	 private double voteAverage;
	 @SerializedName("title")
	 private String title;
	 @SerializedName("original_language")
	 private String originalLanguage;
	 @SerializedName("original_title")
	 private String originalTitle;
	 @SerializedName("adult")
	 private boolean adult;
	 @SerializedName("overview")
	 private String overview;
	 @SerializedName("release_date")
	 private String releaseDate;
	 
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getVoteAverage() {
		return voteAverage;
	}
	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOriginalLanguage() {
		return originalLanguage;
	}
	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}
	public String getOriginalTitle() {
		return originalTitle;
	}
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	public boolean isAdult() {
		return adult;
	}
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

}
