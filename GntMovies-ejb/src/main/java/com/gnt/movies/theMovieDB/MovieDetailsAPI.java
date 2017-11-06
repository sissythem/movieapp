package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

public class MovieDetailsAPI {

	private byte adult;
	private String budget;
	private ArrayList<MovieGenresAPI> movieGenresAPI;
	private String homepage;
	private int id;
	private String imdbId;
	private String originalLanguage;
	private String originalTitle;
	private String overview;
	private ArrayList<ProductionCompaniesAPI> productionCompaniesAPI;
	private ArrayList<ProductionCountriesAPI> productionCountriesAPI;
	private String releaseDate;
	private double revenue;
	private int runtime;
	private ArrayList<SpokenLanguagesAPI> spokenLanguages;
	private String status;
	private String title;
	private double voteAverage;
	private int voteCount;

	public byte getAdult() {
		return adult;
	}

	public void setAdult(byte adult) {
		this.adult = adult;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public ArrayList<MovieGenresAPI> getMovieGenresAPI() {
		return movieGenresAPI;
	}

	public void setMovieGenresAPI(ArrayList<MovieGenresAPI> movieGenresAPI) {
		this.movieGenresAPI = movieGenresAPI;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
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

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public ArrayList<ProductionCompaniesAPI> getProductionCompaniesAPI() {
		return productionCompaniesAPI;
	}

	public void setProductionCompaniesAPI(ArrayList<ProductionCompaniesAPI> productionCompaniesAPI) {
		this.productionCompaniesAPI = productionCompaniesAPI;
	}

	public ArrayList<ProductionCountriesAPI> getProductionCountriesAPI() {
		return productionCountriesAPI;
	}

	public void setProductionCountriesAPI(ArrayList<ProductionCountriesAPI> productionCountriesAPI) {
		this.productionCountriesAPI = productionCountriesAPI;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public ArrayList<SpokenLanguagesAPI> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(ArrayList<SpokenLanguagesAPI> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

}
