package com.gnt.appobjects;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by sissy on 12/9/17.
 */

public class ShowDto implements Serializable, Comparable<ShowDto> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private int numOfEpisodes;
    private int numOfSeasons;
    private Double voteAverage;
    private int voteCount;
    private Double averageRating;

    public ShowDto() {

    }

    public ShowDto(Integer id, String name, int numOfEpisodes, int numOfSeasons, double voteAverage, int voteCount) {
        super();
        this.id = id;
        this.name = name;
        this.numOfEpisodes = numOfEpisodes;
        this.numOfSeasons = numOfSeasons;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public ShowDto(Show show) {
        super();
        this.id = show.getId();
        this.name = show.getName();
        this.numOfEpisodes = show.getNumOfEpisodes();
        this.numOfSeasons = show.getNumOfSeasons();
        this.voteAverage = show.getVoteAverage();
        this.voteCount = show.getVoteCount();
        this.averageRating = show.getAverageRating();
    }

    public Integer getId() {
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
        ShowDto other = (ShowDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /** Default compareTo, sorting based on our rating **/
    @Override
    public int compareTo(ShowDto show) {
        Double averageRating = this.getAverageRating();
        Double otherAverageRating = show.getAverageRating();
        return -averageRating.compareTo(otherAverageRating);
    }

    /** Compare and sort movies based on vote average of the movie db **/
    public static Comparator<ShowDto> VoteAverageComparator = new Comparator<ShowDto>() {

        @Override
        public int compare(ShowDto show1, ShowDto show2) {
            return show1.getVoteAverage().compareTo(show2.getVoteAverage());
        }
    };
}
