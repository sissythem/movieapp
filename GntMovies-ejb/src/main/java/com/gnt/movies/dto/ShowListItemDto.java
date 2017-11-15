package com.gnt.movies.dto;

import java.io.Serializable;

import com.gnt.movies.entities.Show;

public class ShowListItemDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private int numOfEpisodes;
    private int numOfSeasons;
    private double voteAverage;
    private int voteCount;
    private double averageRating;
    
    public ShowListItemDto() {
    	
    }
    
	public ShowListItemDto(int id, String name, int numOfEpisodes, int numOfSeasons, double voteAverage, int voteCount,
			double averageRating) {
		super();
		this.id = id;
		this.name = name;
		this.numOfEpisodes = numOfEpisodes;
		this.numOfSeasons = numOfSeasons;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.averageRating = averageRating;
	}
	
	public ShowListItemDto(Show show) {
		super();
		this.id = show.getId();
		this.name = show.getName();
		this.numOfEpisodes = show.getNumOfEpisodes();
		this.numOfSeasons = show.getNumOfSeasons();
		this.voteAverage = show.getVoteAverage();
		this.voteCount = show.getVoteCount();
		this.averageRating = show.getAverageRating();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumOfEpisodes() {
		return numOfEpisodes;
	}
	public void setNumOfEpisodes(int numOfEpisodes) {
		this.numOfEpisodes = numOfEpisodes;
	}
	public int getNumOfSeasons() {
		return numOfSeasons;
	}
	public void setNumOfSeasons(int numOfSeasons) {
		this.numOfSeasons = numOfSeasons;
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
		builder.append("ShowListItemDto [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", numOfEpisodes=");
		builder.append(numOfEpisodes);
		builder.append(", numOfSeasons=");
		builder.append(numOfSeasons);
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
