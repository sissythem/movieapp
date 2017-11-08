package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class MovieDetailsAPI {

	@SerializedName("adult")
	private boolean adult;
	@SerializedName("budget")
	private double budget;
	@SerializedName("genres")
	private ArrayList<GenresAPI> genresAPI;
	@SerializedName("homepage")
	private String homepage;
	@SerializedName("id")
	private int id;
	@SerializedName("imdb_id")
	private String imdbId;
	@SerializedName("original_language")
	private String originalLanguage;
	@SerializedName("original_title")
	private String originalTitle;
	@SerializedName("overview")
	private String overview;
	@SerializedName("production_companies")
	private ArrayList<ProductionCompaniesAPI> productionCompaniesAPI;
	@SerializedName("production_countries")
	private ArrayList<ProductionCountriesAPI> productionCountriesAPI;
	@SerializedName("release_date")
	private String releaseDate;
	@SerializedName("revenue")
	private double revenue;
	@SerializedName("runtime")
	private int runtime;
	@SerializedName("spoken_languages")
	private ArrayList<SpokenLanguagesAPI> spokenLanguages;
	@SerializedName("status")
	private String status;
	@SerializedName("title")
	private String title;
	@SerializedName("vote_average")
	private double voteAverage;
	@SerializedName("vote_count")
	private int voteCount;
	
	private ArrayList<CastCrewAPI> cast;
	private ArrayList<CastCrewAPI> crew;

	public boolean getAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public ArrayList<GenresAPI> getMovieGenresAPI() {
		return genresAPI;
	}

	public void setMovieGenresAPI(ArrayList<GenresAPI> genresAPI) {
		this.genresAPI = genresAPI;
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

	public ArrayList<GenresAPI> getGenresAPI() {
		return genresAPI;
	}

	public void setGenresAPI(ArrayList<GenresAPI> genresAPI) {
		this.genresAPI = genresAPI;
	}

	public ArrayList<CastCrewAPI> getCast() {
		return cast;
	}

	public void setCast(ArrayList<CastCrewAPI> cast) {
		this.cast = cast;
	}

	public ArrayList<CastCrewAPI> getCrew() {
		return crew;
	}

	public void setCrew(ArrayList<CastCrewAPI> crew) {
		this.crew = crew;
	}

	@Override
	public String toString() {
		return "MovieDetailsAPI [adult=" + adult + ", budget=" + budget + ", genresAPI=" + genresAPI + ", homepage="
				+ homepage + ", id=" + id + ", imdbId=" + imdbId + ", originalLanguage=" + originalLanguage
				+ ", originalTitle=" + originalTitle + ", overview=" + overview + ", productionCompaniesAPI="
				+ productionCompaniesAPI + ", productionCountriesAPI=" + productionCountriesAPI + ", releaseDate="
				+ releaseDate + ", revenue=" + revenue + ", runtime=" + runtime + ", spokenLanguages=" + spokenLanguages
				+ ", status=" + status + ", title=" + title + ", voteAverage=" + voteAverage + ", voteCount="
				+ voteCount + ", cast=" + cast + ", crew=" + crew + "]";
	}
	
	
}
