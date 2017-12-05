package com.gnt.appobjects;

import com.google.gson.JsonElement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Created by sissy on 12/5/17.
 */

public class Movie implements Serializable,Comparable<Movie> {

    private Integer id;
    private Boolean adult;
    private Double budget;
    private JsonElement cast;
    private JsonElement crew;
    private String homepage;
    private Integer idTmdb;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String posterPath;
    private JsonElement productionCountries;
    private LocalDate releaseDate;
    private Double revenue;
    private Integer runtime;
    private String status;
    private String title;
    private Double voteAverage;
    private Integer voteCount;
    private HashSet<Genre> genres;
    private ArrayList<Image> images;
    private ArrayList<MovieFavorite> movieFavorites;
    private ArrayList<MovieReview> movieReviews;
    private NowPlayingMovie nowPlayingMovie;
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

    public HashSet<Genre> getGenres() {
        if (genres == null) {
            genres = new HashSet<>();
        }
        return this.genres;
    }

    public void setGenres(HashSet<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre) {
        getGenres().add(genre);
    }

    public void removeGenre(Genre genre) {
        getGenres().remove(genre);
    }

    public ArrayList<MovieFavorite> getMovieFavorites() {
        if (movieFavorites == null) {
            movieFavorites = new ArrayList<>();
        }
        return this.movieFavorites;
    }

    public void setMovieFavorites(ArrayList<MovieFavorite> movieFavorites) {
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

    public ArrayList<MovieReview> getMovieReviews() {
        if (movieReviews == null) {
            movieReviews = new ArrayList<>();
        }
        return this.movieReviews;
    }

    public void setMovieReviews(ArrayList<MovieReview> movieReviews) {
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

    public ArrayList<Image> getImages() {
        if (images == null) {
            images = new ArrayList<>();
        }
        return images;
    }

    public void setImages(ArrayList<Image> images) {
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
                rating = rating + ((ArrayList<MovieReview>) movieReviews).get(i).getRating();
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
