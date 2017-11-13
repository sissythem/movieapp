package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class ApiCastCrewResults {

	@SerializedName("cast")
	private ArrayList<ApiCastCrew> castResults;
	@SerializedName("crew")
	private ArrayList<ApiCastCrew> crewResults;
	
	public ArrayList<ApiCastCrew> getCastResults() {
		return castResults;
	}
	public void setCastResults(ArrayList<ApiCastCrew> castResults) {
		this.castResults = castResults;
	}
	public ArrayList<ApiCastCrew> getCrewResults() {
		return crewResults;
	}
	public void setCrewResults(ArrayList<ApiCastCrew> crewResults) {
		this.crewResults = crewResults;
	}
}
