package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class ApiNewShow {

	@SerializedName("original_name")
	private String originalName;
	@SerializedName("name")
	private String name;
	@SerializedName("origin_country")
	private ArrayList<String> originCountry;
	@SerializedName("vote_count")
	private int voteCount;
	@SerializedName("poster_path")
	private String poster_path;
	@SerializedName("first_air_date")
	private String firstAirDate;
	@SerializedName("original_language")
	private String originalLanguage;
	@SerializedName("id")
	private int id;
	@SerializedName("vote_average")
	private double voteAverage;
	@SerializedName("overview")
	private String overview;

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(ArrayList<String> originCountry) {
		this.originCountry = originCountry;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getFirstAirDate() {
		return firstAirDate;
	}

	public void setFirstAirDate(String firstAirDate) {
		this.firstAirDate = firstAirDate;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
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

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}
}
