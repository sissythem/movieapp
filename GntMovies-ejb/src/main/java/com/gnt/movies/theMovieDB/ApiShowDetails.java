package com.gnt.movies.theMovieDB;

import java.util.ArrayList;

import com.gnt.movies.entities.Genre;
import com.google.gson.annotations.SerializedName;

public class ApiShowDetails {

	@SerializedName("created_by")
	private ArrayList<ApiCreator> createdBy;
	@SerializedName("first_air_date")
	private String firstAirDate;
	@SerializedName("genres")
	private ArrayList<Genre> genres;
	@SerializedName("homepage")
	private String homepage;
	@SerializedName("id")
	private int id;
	@SerializedName("in_production")
	private boolean inProduction;
	@SerializedName("languages")
	private ArrayList<String> languages;
	@SerializedName("last_air_date")
	private String lastAirDate;
	@SerializedName("name")
	private String name;
	@SerializedName("networks")
	private ArrayList<ApiNetworks> networks;
	@SerializedName("episode_run_time")
	private Integer[] runtime;
	@SerializedName("number_of_episodes")
	private int episodesNum;
	@SerializedName("number_of_seasons")
	private int seasonsNum;
	@SerializedName("origin_country")
	private ArrayList<String> originCountry;
	@SerializedName("original_language")
	private String originalLanguage;
	@SerializedName("original_name")
	private String originalName;
	@SerializedName("overview")
	private String overview;
	@SerializedName("production_companies")
	private ArrayList<ApiProductionCompanies> apiProductionCompanies;
	@SerializedName("production_countries")
	private ArrayList<ApiProductionCountries> apiProductionCountries;
	@SerializedName("status")
	private String status;
	@SerializedName("type")
	private String type;
	@SerializedName("vote_average")
	private double voteAverage;
	@SerializedName("vote_count")
	private int voteCount;
	@SerializedName("images")
	private ApiImages apiImages;
	@SerializedName("credits")
	private ApiCredits apiCredits;

	private ArrayList<ApiPostersBackdrops> allImages;

	public ArrayList<ApiCreator> getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ArrayList<ApiCreator> createdBy) {
		this.createdBy = createdBy;
	}

	public String getFirstAirDate() {
		return firstAirDate;
	}

	public void setFirstAirDate(String firstAirDate) {
		this.firstAirDate = firstAirDate;
	}

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
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

	public boolean isInProduction() {
		return inProduction;
	}

	public void setInProduction(boolean inProduction) {
		this.inProduction = inProduction;
	}

	public ArrayList<String> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}

	public String getLastAirDate() {
		return lastAirDate;
	}

	public void setLastAirDate(String lastAirDate) {
		this.lastAirDate = lastAirDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ApiNetworks> getNetworks() {
		return networks;
	}

	public void setNetworks(ArrayList<ApiNetworks> networks) {
		this.networks = networks;
	}

	public int getEpisodesNum() {
		return episodesNum;
	}

	public void setEpisodesNum(int episodesNum) {
		this.episodesNum = episodesNum;
	}

	public int getSeasonsNum() {
		return seasonsNum;
	}

	public void setSeasonsNum(int seasonsNum) {
		this.seasonsNum = seasonsNum;
	}

	public ArrayList<String> getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(ArrayList<String> originCountry) {
		this.originCountry = originCountry;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		if (getApiCredits() != null && getApiCredits().getCast() != null)
			return getApiCredits().getCast();
		else
			return null;
	}

	public void setCast(ArrayList<ApiCastCrew> cast) {
		getApiCredits().setCast(cast);
	}

	public ArrayList<ApiCastCrew> getCrew() {
		if (getApiCredits() != null && getApiCredits().getCrew() != null)
			return getApiCredits().getCrew();
		else
			return null;
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

	public Integer getRuntime() {
		if (runtime != null && runtime.length > 0)
			return runtime[0];
		else
			return 0;
	}

	public void setRuntime(Integer[] runtime) {
		this.runtime = runtime;
	}

	public ArrayList<ApiPostersBackdrops> getAllImages() {
		return allImages;
	}

	public void setAllImages(ApiImages apiImages) {
		this.allImages = apiImages.getApiBackdrops();
		this.allImages = apiImages.getApiPosters();
	}

}
