package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class UpcomingNowPlayingMovieResultsAPI {
	@SerializedName("results")
	private ArrayList<UpcomingNowPlayingMovieAPI> results;
	@SerializedName("page")
	private int page;
	@SerializedName("total_results")
	private int totalResults;
	@SerializedName("total_pages")
	private int totalPages;
	public ArrayList<UpcomingNowPlayingMovieAPI> getResults() {
		return results;
	}
	public void setResults(ArrayList<UpcomingNowPlayingMovieAPI> results) {
		this.results = results;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
