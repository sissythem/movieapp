package com.gnt.movies.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gnt.movies.dto.MovieListItemDto;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

/**
 * The persistent class for the movies database table.
 *	private Integer id;
	private String originalTitle;

 */
@Entity
@Table(name = "movies")
@NamedQueries({ 
//		@NamedQuery(name = "Movie.findAll", query = "SELECT m.id, m.originalTitle, m.posterPath, m.voteAverage, m.voteCount FROM Movie m"),
		@NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.id = :id"),
		@NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title"),
		@NamedQuery(name = "Movie.findByOriginalTitle", query = "SELECT m FROM Movie m WHERE m.originalTitle = :originalTitle"),
		@NamedQuery(name = "Movie.findByIdTmdb", query = "SELECT m FROM Movie m WHERE m.idTmdb = :idTmdb"),
		@NamedQuery(name = "Movie.findByBudget", query = "SELECT m FROM Movie m WHERE m.budget = :budget"),
		@NamedQuery(name = "Movie.findByRevenue", query = "SELECT m FROM Movie m WHERE m.revenue = :revenue"),
		@NamedQuery(name = "Movie.findByRuntime", query = "SELECT m FROM Movie m WHERE m.runtime = :runtime"),
		@NamedQuery(name = "Movie.findByStatus", query = "SELECT m FROM Movie m WHERE m.status = :status"),
		@NamedQuery(name = "Movie.findByReleaseDate", query = "SELECT m FROM Movie m WHERE m.releaseDate = :releaseDate"),
		@NamedQuery(name = "Movie.findByVoteAverage", query = "SELECT m FROM Movie m WHERE m.voteAverage = :voteAverage"),
		@NamedQuery(name = "Movie.findByVoteCount", query = "SELECT m FROM Movie m WHERE m.voteCount = :voteCount"),
		@NamedQuery(name = "Movie.findByOriginalLanguage", query = "SELECT m FROM Movie m WHERE m.originalLanguage = :originalLanguage"),
		@NamedQuery(name = "Movie.findByAdult", query = "SELECT m FROM Movie m WHERE m.adult = :adult"),
		@NamedQuery(name = "Movie.findByImdbId", query = "SELECT m FROM Movie m WHERE m.imdbId = :imdbId") 
})
@NamedNativeQueries({
	@NamedNativeQuery(name = "Movie.findAll", query = "SELECT m.id, m.originalTitle, m.posterPath, m.voteAverage, m.voteCount FROM movies as m", resultSetMapping = "MovieListItemDto")
})
@SqlResultSetMapping(name = "MovieListItemDto", classes = {
		@ConstructorResult(targetClass = MovieListItemDto.class, columns = { @ColumnResult(name = "id"),
				@ColumnResult(name = "originalTitle"), @ColumnResult(name = "posterPath"),
				@ColumnResult(name = "voteAverage"), @ColumnResult(name = "voteCount") 
		}) 
})
public class Movie implements Serializable, Comparable<Movie> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SerializedName("myid")
	private Integer id;
	@SerializedName("adult")
	private Boolean adult;
	private Double budget;
	private JsonElement cast;
	private JsonElement crew;
	private String homepage;
	@SerializedName("id")
	private Integer idTmdb;
	private String imdbId;
	@SerializedName("original_language")
	private String originalLanguage;
	@SerializedName("original_title")
	private String originalTitle;
	@SerializedName("overview")
	private String overview;
	@SerializedName("poster_path")
	private String posterPath;
	private JsonElement productionCountries;
	@SerializedName("release_date")
	private LocalDate releaseDate;
	private Double revenue;
	private Integer runtime;
	private String status;
	@SerializedName("title")
	private String title;
	@SerializedName("vote_average")
	private Double voteAverage;
	@SerializedName("vote_count")
	private Integer voteCount;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "movie_genres", joinColumns = {
			@JoinColumn(name = "movieId", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "genreId", referencedColumnName = "id") })
	private Set<Genre> genres;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "movie_images", joinColumns = {
			@JoinColumn(name = "movieId", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "imageId", referencedColumnName = "id") })
	private List<Image> images;

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
	private List<MovieFavorite> movieFavorites;

	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
	private List<MovieReview> movieReviews;

	@Transient
	private NowPlayingMovie nowPlayingMovie;

	@Transient
	private UpcomingMovie upcomingMovie;

	public Movie() {
	}

	public Movie(Boolean adult, Integer idTmdb, String releaseDate, String originalLanguage, String originalTitle,
			String overview, String title, double voteAverage, Integer voteCount, String posterPath) {
		super();
		this.adult = adult;
		this.idTmdb = idTmdb;
		this.originalLanguage = originalLanguage;
		this.originalTitle = originalTitle;
		this.overview = overview;
		this.releaseDate = LocalDate.parse(releaseDate);
		this.title = title;
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

	public Boolean getAdult() {
		return this.adult;
	}

	public void setAdult(Boolean adult) {
		this.adult = adult;
	}

	public Double getBudget() {
		return this.budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public JsonElement getCast() {
		return this.cast;
	}

	public void setCast(JsonElement cast) {
		this.cast = cast;
	}

	public JsonElement getCrew() {
		return this.crew;
	}

	public void setCrew(JsonElement crew) {
		this.crew = crew;
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

	public String getImdbId() {
		return this.imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getOriginalLanguage() {
		return this.originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalTitle() {
		return this.originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
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

	public JsonElement getProductionCountries() {
		return this.productionCountries;
	}

	public void setProductionCountries(JsonElement productionCountries) {
		this.productionCountries = productionCountries;
	}

	public LocalDate getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Double getRevenue() {
		return this.revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public Integer getRuntime() {
		return this.runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getVoteAverage() {
		return this.voteAverage;
	}

	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public Integer getVoteCount() {
		return this.voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public Set<Genre> getGenres() {
		if (genres == null) {
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

	public List<MovieFavorite> getMovieFavorites() {
		if (movieFavorites == null) {
			movieFavorites = new ArrayList<>();
		}
		return this.movieFavorites;
	}

	public void setMovieFavorites(List<MovieFavorite> movieFavorites) {
		this.movieFavorites = movieFavorites;
	}

	public MovieFavorite addMovieFavorite(MovieFavorite movieFavorite) {
		getMovieFavorites().add(movieFavorite);
		movieFavorite.setMovie(this);

		return movieFavorite;
	}

	public MovieFavorite removeMovieFavorite(MovieFavorite movieFavorite) {
		getMovieFavorites().remove(movieFavorite);
		movieFavorite.setMovie(null);

		return movieFavorite;
	}

	public List<MovieReview> getMovieReviews() {
		if (movieReviews == null) {
			movieReviews = new ArrayList<>();
		}
		return this.movieReviews;
	}

	public void setMovieReviews(List<MovieReview> movieReviews) {
		this.movieReviews = movieReviews;
	}

	public MovieReview addMovieReview(MovieReview movieReview) {
		getMovieReviews().add(movieReview);
		movieReview.setMovie(this);

		return movieReview;
	}

	public MovieReview removeMovieReview(MovieReview movieReview) {
		getMovieReviews().remove(movieReview);
		movieReview.setMovie(null);

		return movieReview;
	}

	public NowPlayingMovie getNowPlayingMovie() {
		return this.nowPlayingMovie;
	}

	public void setNowPlayingMovie(NowPlayingMovie nowPlayingMovie) {
		this.nowPlayingMovie = nowPlayingMovie;
	}

	public UpcomingMovie getUpcomingMovie() {
		return this.upcomingMovie;
	}

	public void setUpcomingMovie(UpcomingMovie upcomingMovie) {
		this.upcomingMovie = upcomingMovie;
	}

	public List<Image> getImages() {
		if (images == null) {
			images = new ArrayList<>();
		}
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void addImage(Image image) {
		getImages().add(image);
	}

	public void removeImages(Image image) {
		getImages().remove(image);
	}

	/** Count our average rating based on ratings and comments only in our app **/
	public double getAverageRating() {
		double rating = 0.0;
		double averageRating;
		if (movieReviews == null) {
			averageRating = 0.0;
		} else {
			for (int i = 0; i < movieReviews.size(); i++) {
				rating = rating + ((List<MovieReview>) movieReviews).get(i).getRating();
			}
			averageRating = rating / movieReviews.size();
		}
		return averageRating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTmdb == null) ? 0 : idTmdb.hashCode());
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
		Movie other = (Movie) obj;
		if (idTmdb == null) {
			if (other.idTmdb != null)
				return false;
		} else if (!idTmdb.equals(other.idTmdb))
			return false;
		return true;
	}

	/** Default compareTo, sorting based on our rating **/
	@Override
	public int compareTo(Movie movie) {
		Double averageRating = this.getAverageRating();
		Double otherAverageRating = movie.getAverageRating();
		return -averageRating.compareTo(otherAverageRating);
	}

	/** Compare and sort movies based on vote average of the movie db **/
	public static Comparator<Movie> VoteAverageComparator = new Comparator<Movie>() {

		@Override
		public int compare(Movie movie1, Movie movie2) {
			return movie1.getVoteAverage().compareTo(movie2.getVoteAverage());
		}
	};
}