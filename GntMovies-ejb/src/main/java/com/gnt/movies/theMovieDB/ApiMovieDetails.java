package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class ApiMovieDetails {

	@SerializedName("adult")
	private boolean adult;
	@SerializedName("budget")
	private double budget;
	@SerializedName("genres")
	private ArrayList<ApiGenre> apiGenre;
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
	private ArrayList<ApiProductionCompanies> apiProductionCompanies;
	@SerializedName("production_countries")
	private ArrayList<ApiProductionCountries> apiProductionCountries;
	@SerializedName("release_date")
	private String releaseDate;
	@SerializedName("revenue")
	private double revenue;
	@SerializedName("runtime")
	private int runtime;
	@SerializedName("spoken_languages")
	private ArrayList<ApiSpokenLanguages> spokenLanguages;
	@SerializedName("status")
	private String status;
	@SerializedName("title")
	private String title;
	@SerializedName("vote_average")
	private double voteAverage;
	@SerializedName("vote_count")
	private int voteCount;
	@SerializedName("images")
	private ApiImages apiImages;
	@SerializedName("credits")
	private ApiCredits apiCredits;

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

	public ArrayList<ApiGenre> getMovieGenresAPI() {
		return apiGenre;
	}

	public void setMovieGenresAPI(ArrayList<ApiGenre> apiGenre) {
		this.apiGenre = apiGenre;
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

	public ArrayList<ApiProductionCompanies> getProductionCompaniesAPI() {
		return apiProductionCompanies;
	}

	public void setProductionCompaniesAPI(ArrayList<ApiProductionCompanies> apiProductionCompanies) {
		this.apiProductionCompanies = apiProductionCompanies;
	}

	public ArrayList<ApiProductionCountries> getProductionCountriesAPI() {
		return apiProductionCountries;
	}

	public void setProductionCountriesAPI(ArrayList<ApiProductionCountries> apiProductionCountries) {
		this.apiProductionCountries = apiProductionCountries;
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

	public ArrayList<ApiSpokenLanguages> getSpokenLanguages() {
		return spokenLanguages;
	}

	public void setSpokenLanguages(ArrayList<ApiSpokenLanguages> spokenLanguages) {
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

	public ArrayList<ApiGenre> getGenresAPI() {
		return apiGenre;
	}

	public void setGenresAPI(ArrayList<ApiGenre> apiGenre) {
		this.apiGenre = apiGenre;
	}

	public ApiCredits getApiCredits() {
		return apiCredits;
	}

	public void setApiCredits(ApiCredits apiCredits) {
		this.apiCredits = apiCredits;
	}

	public ArrayList<ApiCastCrew> getCast() {
		return getApiCredits().getCast();
	}

	public void setCast(ArrayList<ApiCastCrew> cast) {
		getApiCredits().setCast(cast);
	}

	public ArrayList<ApiCastCrew> getCrew() {
		return getApiCredits().getCrew();
	}

	public void setCrew(ArrayList<ApiCastCrew> crew) {
		getApiCredits().setCrew(crew);
	}

	public ApiImages getApiImages() {
		return apiImages;
	}

	public void setApiImages(ApiImages apiImages) {
		this.apiImages = apiImages;
	}
	

}
