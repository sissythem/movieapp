package com.gnt.movies.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the movies database table.
 *
 */
@Entity
@Table(name="movies")
@NamedQueries({
        @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
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
        @NamedQuery(name = "Movie.findByCreator", query = "SELECT m FROM Movie m WHERE m.creator = :creator"),
        @NamedQuery(name = "Movie.findByProductionCountries", query = "SELECT m FROM Movie m WHERE m.productionCountries = :productionCountries"),
        @NamedQuery(name = "Movie.findByAdult", query = "SELECT m FROM Movie m WHERE m.adult = :adult"),
        @NamedQuery(name = "Movie.findByImdbId", query = "SELECT m FROM Movie m WHERE m.imdbId = :imdbId")
})
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private byte adult;

    private double budget;

    @Lob
    private String cast;

    private String creator;

    @Lob
    private String crew;

    private String homepage;

    private int idTmdb;

    private String imdbId;

    private String originalLanguage;

    private String originalTitle;

    @Lob
    private String overview;

    private String posterPath;

    private String productionCountries;

    private LocalDate releaseDate;

    private double revenue;

    private int runtime;

    private String status;

    private String title;

    private double voteAverage;

    private int voteCount;
    
    @OneToMany(mappedBy="movie")
    private List<MovieGenre> movieGenres;
    
    @OneToMany(mappedBy="movie", fetch=FetchType.LAZY)
    private List<MovieImage> movieImages;

    @OneToMany(mappedBy="movie", fetch=FetchType.LAZY)
    private List<MovieFavorite> movieFavorites;

    @OneToMany(mappedBy="movie", fetch=FetchType.LAZY)
    private List<MovieReview> movieReviews;

    @Transient    
    private NowPlayingMovie nowPlayingMovie;

    @Transient
    private UpcomingMovie upcomingMovie;

    public Movie() {
    }

    public Movie(byte adult, int idTmdb, String releaseDate, String originalLanguage, String originalTitle, String overview, String title, double voteAverage, 
    		int voteCount) {
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
		this.movieGenres = new ArrayList<>();
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

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCrew() {
        return this.crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
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

    public String getProductionCountries() {
        return this.productionCountries;
    }

    public void setProductionCountries(String productionCountries) {
        this.productionCountries = productionCountries;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<MovieGenre> getMovieGenres() {
        return this.movieGenres;
    }

    public void setMovieGenres(List<MovieGenre> movieGenres) {
        this.movieGenres = movieGenres;
    }

    public MovieGenre addMovieGenre(MovieGenre movieGenre) {
        getMovieGenres().add(movieGenre);
        movieGenre.setMovie(this);

        return movieGenre;
    }

    public MovieGenre removeMovieGenre(MovieGenre movieGenre) {
        getMovieGenres().remove(movieGenre);
        movieGenre.setMovie(null);

        return movieGenre;
    }

    public List<MovieFavorite> getMovieFavorites() {
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
    
    public List<MovieImage> getMovieImages() {
        return this.movieImages;
    }

    public void setMovieImages(List<MovieImage> movieImages) {
        this.movieImages = movieImages;
    }

    public MovieImage addMovieImage(MovieImage movieImage) {
        getMovieImages().add(movieImage);
        movieImage.setMovie(this);

        return movieImage;
    }

    public MovieImage removeMovieImages(MovieImage movieImage) {
        getMovieImages().remove(movieImage);
        movieImage.setMovie(null);

        return movieImage;
    }
    
    /** Count our average rating based on ratings and comments only in our app **/
    public double getAverageRating(){
        double rating = 0.0;
        double averageRating;
        if(movieReviews == null){
            averageRating=0.0;
        } else {
            for(int i=0;i<movieReviews.size();i++){
                rating = rating + ((List<MovieReview>)movieReviews).get(i).getRating();
            }
            averageRating = rating/movieReviews.size();
        }
        return averageRating;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}