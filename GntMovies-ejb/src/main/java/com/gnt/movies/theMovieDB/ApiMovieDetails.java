package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Image;
import com.google.gson.annotations.SerializedName;

public class ApiMovieDetails {

	@SerializedName("adult")
	private boolean adult;
	@SerializedName("budget")
	private double budget;
	@SerializedName("genres")
	private ArrayList<Genre> genres;
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
	
	private ArrayList<Image> allImages;
	
	public ApiMovieDetails() {
		
	}
	public ApiMovieDetails(boolean adult, double budget, ArrayList<Genre> genres, String homepage, int id,
			String imdbId, String originalLanguage, String originalTitle, String overview,
			ArrayList<ApiProductionCompanies> apiProductionCompanies,
			ArrayList<ApiProductionCountries> apiProductionCountries, String releaseDate, double revenue, int runtime,
			ArrayList<ApiSpokenLanguages> spokenLanguages, String status, String title, double voteAverage,
			int voteCount, ApiImages apiImages, ApiCredits apiCredits, ArrayList<Image> allImages) {
		super();
		this.adult = adult;
		this.budget = budget;
		this.genres = genres;
		this.homepage = homepage;
		this.id = id;
		this.imdbId = imdbId;
		this.originalLanguage = originalLanguage;
		this.originalTitle = originalTitle;
		this.overview = overview;
		this.apiProductionCompanies = apiProductionCompanies;
		this.apiProductionCountries = apiProductionCountries;
		this.releaseDate = releaseDate;
		this.revenue = revenue;
		this.runtime = runtime;
		this.spokenLanguages = spokenLanguages;
		this.status = status;
		this.title = title;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.apiImages = apiImages;
		this.apiCredits = apiCredits;
		this.allImages = allImages;
	}

	public boolean isAdult() {
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

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setApiMovieGenres(ArrayList<Genre> genres) {
		this.genres = genres;
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

	public ArrayList<ApiProductionCompanies> getApiProductionCompanies() {
		return apiProductionCompanies;
	}

	public void setApiProductionCompanies(ArrayList<ApiProductionCompanies> apiProductionCompanies) {
		this.apiProductionCompanies = apiProductionCompanies;
	}

	public ArrayList<ApiProductionCountries> getApiProductionCountries() {
		return apiProductionCountries;
	}

	public void setApiProductionCountries(ArrayList<ApiProductionCountries> apiProductionCountries) {
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

	public ArrayList<Image> getAllImages() {
		return allImages;
	}

	public void setAllImages(ApiImages apiImages) {
		this.allImages = apiImages.getApiBackdrops();
		this.allImages.addAll( apiImages.getApiPosters());
	}
}
