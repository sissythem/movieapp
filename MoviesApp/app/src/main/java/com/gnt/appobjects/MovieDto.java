package com.gnt.appobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Created by sissy on 12/9/17.
 */

public class MovieDto implements Serializable, Comparable<MovieDto> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String originalTitle;
    private String posterPath;
    private Double voteAverage;
    private int voteCount;
    private double averageRating;
    private HashSet<Genre> genres;
    private String overview;

    public MovieDto() {

    }

    public MovieDto(Integer id, String originalTitle, String posterPath, Double voteAverage, int voteCount, String overview) {
        super();
        this.id = id;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.overview = overview;
    }

    public MovieDto(Movie movie) {
        super();
        this.id = movie.getId();
        this.originalTitle=movie.getOriginalTitle();
        this.posterPath = movie.getPosterPath();
        this.voteAverage = movie.getVoteAverage();
        this.voteCount = movie.getVoteCount();
        this.averageRating = movie.getAverageRating();
        this.genres = movie.getGenres();
        this.overview = movie.getOverview();
    }
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public Double getVoteAverage() {
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
    public double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public HashSet<Genre> getGenres() {
        return genres;
    }

    public void setGenres(HashSet<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MovieListItemDto [id=");
        builder.append(id);
        builder.append(", originalTitle=");
        builder.append(originalTitle);
        builder.append(", posterPath=");
        builder.append(posterPath);
        builder.append(", voteAverage=");
        builder.append(voteAverage);
        builder.append(", voteCount=");
        builder.append(voteCount);
        builder.append(", averageRating=");
        builder.append(averageRating);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        MovieDto other = (MovieDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /** Default compareTo, sorting based on our rating **/
    @Override
    public int compareTo(MovieDto movie) {
        Double averageRating = this.getAverageRating();
        Double otherAverageRating = movie.getAverageRating();
        return -averageRating.compareTo(otherAverageRating);
    }

    /** Compare and sort movies based on vote average of the movie db **/
    public static Comparator<MovieDto> VoteAverageComparator = new Comparator<MovieDto>() {

        @Override
        public int compare(MovieDto movie1, MovieDto movie2) {
            return movie1.getVoteAverage().compareTo(movie2.getVoteAverage());
        }
    };
}
