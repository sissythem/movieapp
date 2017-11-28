package com.gnt.movies.utilities;

import java.util.ArrayList;

import com.gnt.movies.entities.Show;
import com.google.gson.annotations.SerializedName;

public class ApiNewShowResults {
	
	@SerializedName("page")
	private int page;
	@SerializedName("total_results")
	private int totalResults;
	@SerializedName("total_pages")
	private int totalPages;
	@SerializedName("results")
	private ArrayList<Show> results;
	
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
	public ArrayList<Show> getResults() {
		return results;
	}
	public void setResults(ArrayList<Show> results) {
		this.results = results;
	}
}
