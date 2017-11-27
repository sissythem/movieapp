package com.gnt.movies.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

/**
 * The persistent class for the shows database table.
 *
 */
@Entity
@Table(name = "shows")
@NamedQueries({ @NamedQuery(name = "Show.findAll", query = "SELECT s FROM Show s"),
		@NamedQuery(name = "Show.findById", query = "SELECT s FROM Show s WHERE s.id = :id"),
		@NamedQuery(name = "Show.findByName", query = "SELECT s FROM Show s WHERE s.name = :name"),
		@NamedQuery(name = "Show.findByOriginalName", query = "SELECT s FROM Show s WHERE s.originalName = :originalName"),
		@NamedQuery(name = "Show.findByIdTmdb", query = "SELECT s FROM Show s WHERE s.idTmdb = :idTmdb"),
		@NamedQuery(name = "Show.findByHomepage", query = "SELECT s FROM Show s WHERE s.homepage = :homepage"),
//		@NamedQuery(name = "Show.findByRuntime", query = "SELECT s FROM Show s WHERE s.runtime = :runtime"),
		@NamedQuery(name = "Show.findByStatus", query = "SELECT s FROM Show s WHERE s.status = :status"),
		@NamedQuery(name = "Show.findByFirstAirDate", query = "SELECT s FROM Show s WHERE s.firstAirDate = :firstAirDate"),
		@NamedQuery(name = "Show.findByLastAirDate", query = "SELECT s FROM Show s WHERE s.lastAirDate = :lastAirDate"),
		@NamedQuery(name = "Show.findByVoteAverage", query = "SELECT s FROM Show s WHERE s.voteAverage = :voteAverage"),
		@NamedQuery(name = "Show.findByVoteCount", query = "SELECT s FROM Show s WHERE s.voteCount = :voteCount"),
		@NamedQuery(name = "Show.findByNumOfEpisodes", query = "SELECT s FROM Show s WHERE s.numOfEpisodes = :numOfEpisodes"),
		@NamedQuery(name = "Show.findByNumOfSeasons", query = "SELECT s FROM Show s WHERE s.numOfSeasons = :numOfSeasons"),
		@NamedQuery(name = "Show.findByOriginalLanguage", query = "SELECT s FROM Show s WHERE s.originalLanguage = :originalLanguage"),
//		@NamedQuery(name = "Show.findByCreatedBy", query = "SELECT s FROM Show s WHERE s.createdBy = :createdBy"),
//		@NamedQuery(name = "Show.findByNetworks", query = "SELECT s FROM Show s WHERE s.networks = :networks"),
//		@NamedQuery(name = "Show.findByOriginCountries", query = "SELECT s FROM Show s WHERE s.originCountries = :originCountries"),
		
		@NamedQuery(name = "Show.findByInProduction", query = "SELECT s FROM Show s WHERE s.inProduction = :inProduction"),
		@NamedQuery(name = "Show.findByType", query = "SELECT s FROM Show s WHERE s.type = :type") 
})
public class Show implements Serializable, Comparable<Show> {
	private static final long serialVersionUID = 1L;

	
	@SerializedName("poster_path")
	private String posterPath;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SerializedName("myid")
	private Integer id;

	@Lob
	private JsonElement cast;

	private JsonElement createdBy;

	@Lob
	private JsonElement crew;
	@SerializedName("first_air_date")
	private LocalDate firstAirDate;
	@Lob
	private String homepage;
	@SerializedName("id")
	private Integer idTmdb;

	private Boolean inProduction;

	private LocalDate lastAirDate;
	@SerializedName("name")
	private String name;

	private JsonElement networks;

	private Integer numOfEpisodes;

	private Integer numOfSeasons;
	
	@SerializedName("original_language")
	private String originalLanguage;
	@SerializedName("original_name")
	private String originalName;
	@SerializedName("origin_country")
	private JsonElement originCountries;

	@Lob
	@SerializedName("overview")
	private String overview;

	private JsonElement runtime;

	private String status;

	private String type;
	@SerializedName("vote_average")
	private Double voteAverage;
	@SerializedName("vote_count")
	private Integer voteCount;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "show_genres", joinColumns = {
			@JoinColumn(name = "showId", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "genreId", referencedColumnName = "id") }
	)
	private Set<Genre> genres;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "show_images", joinColumns = {
			@JoinColumn(name = "showId", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "imageId", referencedColumnName = "id") }
	)
	private List<Image> images;
	
	@OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
	private List<ShowFavorite> showFavorites;

	@OneToMany(mappedBy = "show", fetch = FetchType.LAZY)
	private List<ShowReview> showReviews;
	
	@Transient
	private OnTheAirShow onTheAirShows;

	@Transient
	private Air2dayShow air2dayShow;

	public Show() {
	}

	public Show(LocalDate firstAirDate, Integer idTmdb, String name, String originalLanguage, String originalName,
			JsonArray originCountries, String overview, Double voteAverage, Integer voteCount, String posterPath) {
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
		this.posterPath = posterPath;
		this.genres = new HashSet<>();
		this.images = new ArrayList<>();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JsonElement getCast() {
		return this.cast;
	}

	public void setCast(JsonElement cast) {
		this.cast = cast;
	}

	public JsonElement getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(JsonElement createdBy) {
		this.createdBy = createdBy;
	}

	public JsonElement getCrew() {
		return this.crew;
	}

	public void setCrew(JsonElement crew) {
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

	public Integer getIdTmdb() {
		return this.idTmdb;
	}

	public void setIdTmdb(Integer idTmdb) {
		this.idTmdb = idTmdb;
	}

	public Boolean getInProduction() {
		return this.inProduction;
	}

	public void setInProduction(Boolean inProduction) {
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

	public JsonElement getNetworks() {
		return this.networks;
	}

	public void setNetworks(JsonElement networks) {
		this.networks = networks;
	}

	public Integer getNumOfEpisodes() {
		return this.numOfEpisodes;
	}

	public void setNumOfEpisodes(Integer numOfEpisodes) {
		this.numOfEpisodes = numOfEpisodes;
	}

	public Integer getNumOfSeasons() {
		return this.numOfSeasons;
	}

	public void setNumOfSeasons(Integer numOfSeasons) {
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

	public JsonElement getOriginCountries() {
		return this.originCountries;
	}

	public void setOriginCountries(JsonArray originCountries) {
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

	public JsonElement getRuntime() {
		return this.runtime;
	}

	public void setRuntime(JsonElement runtime) {
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

	public Double getVoteAverage() {
		return this.voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public Integer getVoteCount() {
		return this.voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public Set<Genre> getGenres() {
		if(genres==null) {
			genres = new HashSet<>();
		}
		return this.genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public void addGenre(Genre genre) {
		getGenres().add(genre);
	}

	public void removeGenre(Genre genre) {
		getGenres().remove(genre);
	}

	public OnTheAirShow getOnTheAirShows() {
		return this.onTheAirShows;
	}

	public void setOnTheAirShows(OnTheAirShow onTheAirShows) {
		this.onTheAirShows = onTheAirShows;
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

	public List<Image> getImages() {
		if(images ==null) {
			images = new ArrayList<>();
		}
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void addImage(Image image) {
		getImages().add(image);
			}

	public void removeImage(Image image) {
		getImages().remove(image);
	}

	public Double getAverageRating() {
		Double rating = 0.0;
		Double averageRating;
		if (showReviews == null) {
			averageRating = 0.0;
		} else {
			for (Integer i = 0; i < showReviews.size(); i++) {
				rating = rating + ((List<ShowReview>) showReviews).get(i).getRating();
			}
			averageRating = rating / showReviews.size();
		}
		return averageRating;
	}

	

	@Override
	public int hashCode() {
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + idTmdb;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Show other = (Show) obj;
		if (idTmdb != other.idTmdb)
			return false;
		return true;
	}

	/** Default sorting for shows, based on our rating **/
	@Override
	public int compareTo(Show show) {
		Double averageRating = this.getAverageRating();
		Double otherAverageRating = show.getAverageRating();
		return -averageRating.compareTo(otherAverageRating);
	}
	/** Compare and sort shows based on vote average of the movie db **/
	public static Comparator<Show> VoteAverageComparator = new Comparator<Show>() {

        @Override
        public int compare(Show show1, Show show2) {
            return show1.getVoteAverage().compareTo(show2.getVoteAverage());
        }
    };

}