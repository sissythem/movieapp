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
    
    
    
	public MovieListItemDto(Integer id, String originalTitle, String posterPath, Double voteAverage, int voteCount) {
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
		MovieListItemDto other = (MovieListItemDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
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
