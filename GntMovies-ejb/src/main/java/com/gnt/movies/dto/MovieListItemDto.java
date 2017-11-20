package com.gnt.movies.dto;

import java.io.Serializable;
import java.util.Comparator;

import com.gnt.movies.entities.Movie;

public class MovieListItemDto implements Serializable, Comparable<MovieListItemDto> {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String originalTitle;
	private String posterPath;
	private Double voteAverage;
    private int voteCount;
    private Double averageRating;
    
    public MovieListItemDto() {
    	
    }
    
	public MovieListItemDto(int id, String originalTitle, String posterPath, double voteAverage, int voteCount) {
		super();
		this.id = id;
		this.originalTitle = originalTitle;
		this.posterPath = posterPath;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
	}
	
	public MovieListItemDto(Movie movie) {
		super();
		this.id = movie.getId();
		this.originalTitle=movie.getOriginalTitle();
		this.posterPath = movie.getPosterPath();
		this.voteAverage = movie.getVoteAverage();
		this.voteCount = movie.getVoteCount();
		this.averageRating = movie.getAverageRating();
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

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
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
		MovieListItemDto other = (MovieListItemDto) object;

		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	/** Default compareTo, sorting based on our rating **/
	@Override
	public int compareTo(MovieListItemDto movie) {
		Double averageRating = this.getAverageRating();
		Double otherAverageRating = movie.getAverageRating();
		return -averageRating.compareTo(otherAverageRating);
	}

	/** Compare and sort movies based on vote average of the movie db **/
	public static Comparator<MovieListItemDto> VoteAverageComparator = new Comparator<MovieListItemDto>() {

		@Override
		public int compare(MovieListItemDto movie1, MovieListItemDto movie2) {
			return movie1.getVoteAverage().compareTo(movie2.getVoteAverage());
		}
	};
}
