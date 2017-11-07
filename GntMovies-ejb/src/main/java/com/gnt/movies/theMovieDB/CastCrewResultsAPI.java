package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

public class CastCrewResultsAPI {

	private ArrayList<CastCrewAPI> castResults;
	private ArrayList<CastCrewAPI> crewResults;
	
	public ArrayList<CastCrewAPI> getCastResults() {
		return castResults;
	}
	public void setCastResults(ArrayList<CastCrewAPI> castResults) {
		this.castResults = castResults;
	}
	public ArrayList<CastCrewAPI> getCrewResults() {
		return crewResults;
	}
	public void setCrewResults(ArrayList<CastCrewAPI> crewResults) {
		this.crewResults = crewResults;
	}
}
