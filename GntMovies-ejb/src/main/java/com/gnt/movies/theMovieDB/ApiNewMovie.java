package com.gnt.movies.theMovieDB;

import com.google.gson.annotations.SerializedName;

public class ApiNewMovie {
	@SerializedName("vote_count")
	private int voteCount;
	@SerializedName("id")
	private int id;
	@SerializedName("vote_average")
	private double voteAverage;
	@SerializedName("title")
	private String title;
	@SerializedName("poster_path")
	private String poster_path;
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
	
	public ApiNewMovie() {
		
	}
	public ApiNewMovie(int voteCount, int id, double voteAverage, String title, String poster_path,
			String originalLanguage, String originalTitle, boolean adult, String overview, String releaseDate) {
		super();
		this.voteCount = voteCount;
		this.id = id;
		this.voteAverage = voteAverage;
		this.title = title;
		this.poster_path = poster_path;
		this.originalLanguage = originalLanguage;
		this.originalTitle = originalTitle;
		this.adult = adult;
		this.overview = overview;
		this.releaseDate = releaseDate;
	}

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

	@Override
	public String toString() {
		return "ApiNewMovie [id=" + id + ", title="
				+ title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiNewMovie other = (ApiNewMovie) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
