package com.gnt.movies.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shows database table.
 *
 */
@Entity
@Table(name="shows")
@NamedQueries({
        @NamedQuery(name = "Show.findAll", query = "SELECT s FROM Show s"),
        @NamedQuery(name = "Show.findById", query = "SELECT s FROM Show s WHERE s.id = :id"),
        @NamedQuery(name = "Show.findByName", query = "SELECT s FROM Show s WHERE s.name = :name"),
        @NamedQuery(name = "Show.findByOriginalName", query = "SELECT s FROM Show s WHERE s.originalName = :originalName"),
        @NamedQuery(name = "Show.findByIdTmdb", query = "SELECT s FROM Show s WHERE s.idTmdb = :idTmdb"),
        @NamedQuery(name = "Show.findByHomepage", query = "SELECT s FROM Show s WHERE s.homepage = :homepage"),
        @NamedQuery(name = "Show.findByBudget", query = "SELECT s FROM Show s WHERE s.budget = :budget"),
        @NamedQuery(name = "Show.findByRevenue", query = "SELECT s FROM Show s WHERE s.revenue = :revenue"),
        @NamedQuery(name = "Show.findByRuntime", query = "SELECT s FROM Show s WHERE s.runtime = :runtime"),
        @NamedQuery(name = "Show.findByStatus", query = "SELECT s FROM Show s WHERE s.status = :status"),
        @NamedQuery(name = "Show.findByFirstAirDate", query = "SELECT s FROM Show s WHERE s.firstAirDate = :firstAirDate"),
        @NamedQuery(name = "Show.findByLastAirDate", query = "SELECT s FROM Show s WHERE s.lastAirDate = :lastAirDate"),
        @NamedQuery(name = "Show.findByVoteAverage", query = "SELECT s FROM Show s WHERE s.voteAverage = :voteAverage"),
        @NamedQuery(name = "Show.findByVoteCount", query = "SELECT s FROM Show s WHERE s.voteCount = :voteCount"),
        @NamedQuery(name = "Show.findByNumOfEpisodes", query = "SELECT s FROM Show s WHERE s.numOfEpisodes = :numOfEpisodes"),
        @NamedQuery(name = "Show.findByNumOfSeasons", query = "SELECT s FROM Show s WHERE s.numOfSeasons = :numOfSeasons"),
        @NamedQuery(name = "Show.findByOriginalLanguage", query = "SELECT s FROM Show s WHERE s.originalLanguage = :originalLanguage"),
        @NamedQuery(name = "Show.findByCreatedBy", query = "SELECT s FROM Show s WHERE s.createdBy = :createdBy"),
        @NamedQuery(name = "Show.findByNetworks", query = "SELECT s FROM Show s WHERE s.networks = :networks"),
        @NamedQuery(name = "Show.findByOriginCountries", query = "SELECT s FROM Show s WHERE s.originCountries = :originCountries"),
        @NamedQuery(name = "Show.findByProductionCountries", query = "SELECT s FROM Show s WHERE s.productionCountries = :productionCountries"),
        @NamedQuery(name = "Show.findByAdult", query = "SELECT s FROM Show s WHERE s.adult = :adult"),
        @NamedQuery(name = "Show.findByInProduction", query = "SELECT s FROM Show s WHERE s.inProduction = :inProduction"),
        @NamedQuery(name = "Show.findByImdbId", query = "SELECT s FROM Show s WHERE s.imdbId = :imdbId"),
        @NamedQuery(name = "Show.findByType", query = "SELECT s FROM Show s WHERE s.type = :type")
})
public class Show implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private byte adult;

    private double budget;

    @Lob
    private String cast;

    private String createdBy;

    @Lob
    private String crew;

    private LocalDate firstAirDate;

    private String homepage;

    private int idTmdb;

    private String imdbId;

    private byte inProduction;

    private LocalDate lastAirDate;

    private String name;

    private String networks;

    private int numOfEpisodes;

    private int numOfSeasons;

    private String originalLanguage;

    private String originalName;

    private String originCountries;

    @Lob
    private String overview;

    private String posterPath;

    private String productionCountries;

    private double revenue;

    private int runtime;

    private String status;

    private String type;

    private double voteAverage;

    private int voteCount;

    @OneToMany(mappedBy="show")
    private List<ShowGenre> showGenres;

    @OneToMany(mappedBy="show")
    private List<OnTheAirShow> onTheAirShows;

    @OneToMany(mappedBy="show", fetch=FetchType.LAZY)
    private List<ShowFavorite> showFavorites;

    @OneToMany(mappedBy="show", fetch=FetchType.LAZY)
    private List<ShowReview> showReviews;

    @OneToOne
    @JoinColumn(name="id", referencedColumnName="showId")
    private Air2dayShow air2dayShow;

    public Show() {
    }

    public Show(LocalDate firstAirDate, int idTmdb, String name, String originalLanguage, String originalName,
			String originCountries, String overview, double voteAverage, int voteCount) {
		super();
		this.firstAirDate = firstAirDate;
		this.idTmdb = idTmdb;
		this.name = name;
		this.originalLanguage = originalLanguage;
		this.originalName = originalName;
		this.originCountries = originCountries;
		this.overview = overview;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
	}



	public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getAdult() {
        return this.adult;
    }

    public void setAdult(byte adult) {
        this.adult = adult;
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getCast() {
        return this.cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCrew() {
        return this.crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public LocalDate getFirstAirDate() {
        return this.firstAirDate;
    }

    public void setFirstAirDate(LocalDate firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getHomepage() {
        return this.homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getIdTmdb() {
        return this.idTmdb;
    }

    public void setIdTmdb(int idTmdb) {
        this.idTmdb = idTmdb;
    }

    public String getImdbId() {
        return this.imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public byte getInProduction() {
        return this.inProduction;
    }

    public void setInProduction(byte inProduction) {
        this.inProduction = inProduction;
    }

    public LocalDate getLastAirDate() {
        return this.lastAirDate;
    }

    public void setLastAirDate(LocalDate lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworks() {
        return this.networks;
    }

    public void setNetworks(String networks) {
        this.networks = networks;
    }

    public int getNumOfEpisodes() {
        return this.numOfEpisodes;
    }

    public void setNumOfEpisodes(int numOfEpisodes) {
        this.numOfEpisodes = numOfEpisodes;
    }

    public int getNumOfSeasons() {
        return this.numOfSeasons;
    }

    public void setNumOfSeasons(int numOfSeasons) {
        this.numOfSeasons = numOfSeasons;
    }

    public String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOriginCountries() {
        return this.originCountries;
    }

    public void setOriginCountries(String originCountries) {
        this.originCountries = originCountries;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return this.posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getProductionCountries() {
        return this.productionCountries;
    }

    public void setProductionCountries(String productionCountries) {
        this.productionCountries = productionCountries;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getVoteAverage() {
        return this.voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return this.voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public List<ShowGenre> getShowGenres() {
        return this.showGenres;
    }

    public void setShowGenres(List<ShowGenre> showGenres) {
        this.showGenres = showGenres;
    }

    public ShowGenre addShowGenre(ShowGenre showGenre) {
        getShowGenres().add(showGenre);
        showGenre.setShow(this);

        return showGenre;
    }

    public ShowGenre removeShowGenre(ShowGenre showGenre) {
        getShowGenres().remove(showGenre);
        showGenre.setShow(null);

        return showGenre;
    }

    public List<OnTheAirShow> getOnTheAirShows() {
        return this.onTheAirShows;
    }

    public void setOnTheAirShows(List<OnTheAirShow> onTheAirShows) {
        this.onTheAirShows = onTheAirShows;
    }

    public OnTheAirShow addOnTheAirShow(OnTheAirShow onTheAirShow) {
        getOnTheAirShows().add(onTheAirShow);
        onTheAirShow.setShow(this);

        return onTheAirShow;
    }

    public OnTheAirShow removeOnTheAirShow(OnTheAirShow onTheAirShow) {
        getOnTheAirShows().remove(onTheAirShow);
        onTheAirShow.setShow(null);

        return onTheAirShow;
    }

    public List<ShowFavorite> getShowFavorites() {
        return this.showFavorites;
    }

    public void setShowFavorites(List<ShowFavorite> showFavorites) {
        this.showFavorites = showFavorites;
    }

    public ShowFavorite addShowFavorite(ShowFavorite showFavorite) {
        getShowFavorites().add(showFavorite);
        showFavorite.setShow(this);

        return showFavorite;
    }

    public ShowFavorite removeShowFavorite(ShowFavorite showFavorite) {
        getShowFavorites().remove(showFavorite);
        showFavorite.setShow(null);

        return showFavorite;
    }

    public List<ShowReview> getShowReviews() {
        return this.showReviews;
    }

    public void setShowReviews(List<ShowReview> showReviews) {
        this.showReviews = showReviews;
    }

    public ShowReview addShowReview(ShowReview showReview) {
        getShowReviews().add(showReview);
        showReview.setShow(this);

        return showReview;
    }

    public ShowReview removeShowReview(ShowReview showReview) {
        getShowReviews().remove(showReview);
        showReview.setShow(null);

        return showReview;
    }

    public Air2dayShow getAir2dayShow() {
        return this.air2dayShow;
    }

    public void setAir2dayShow(Air2dayShow air2dayShow) {
        this.air2dayShow = air2dayShow;
    }

}