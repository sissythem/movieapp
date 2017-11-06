package com.gnt.movies.theMovieDB;

import com.google.gson.annotations.SerializedName;

public class UpcomingMovieAPI {
	 @SerializedName("vote_count")
	 private int voteCount;
	 private int id;
	 private double voteAverage;
	 private String title;
	 private double popularity;
	 private String poster_path;
	 private String originalLanguage;
	 private String originalTitle;
	 private byte adult;
	 private String overview;
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
	public double getPopularity() {
		return popularity;
	}
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
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
	public byte isAdult() {
		return adult;
	}
	public void setAdult(byte adult) {
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
