package com.gnt.movies.dto;

import java.io.Serializable;

import com.gnt.movies.entities.Movie;

public class MovieListItemDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String originalTitle;
	private String posterPath;
	private double voteAverage;
    private int voteCount;
    private double averageRating;
    
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
	public int getId() {
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

	public double getAverageRating() {
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
}
