package com.gnt.movies.entities;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:24:15.061+0200")
@StaticMetamodel(Show.class)
public class Show_ {
	public static volatile SingularAttribute<Show, Integer> id;
	public static volatile SingularAttribute<Show, Byte> adult;
	public static volatile SingularAttribute<Show, Double> budget;
	public static volatile SingularAttribute<Show, String> cast;
	public static volatile SingularAttribute<Show, String> createdBy;
	public static volatile SingularAttribute<Show, String> crew;
	public static volatile SingularAttribute<Show, LocalDate> firstAirDate;
	public static volatile SingularAttribute<Show, String> homepage;
	public static volatile SingularAttribute<Show, Integer> idTmdb;
	public static volatile SingularAttribute<Show, String> imdbId;
	public static volatile SingularAttribute<Show, Byte> inProduction;
	public static volatile SingularAttribute<Show, LocalDate> lastAirDate;
	public static volatile SingularAttribute<Show, String> name;
	public static volatile SingularAttribute<Show, String> networks;
	public static volatile SingularAttribute<Show, Integer> numOfEpisodes;
	public static volatile SingularAttribute<Show, Integer> numOfSeasons;
	public static volatile SingularAttribute<Show, String> originalLanguage;
	public static volatile SingularAttribute<Show, String> originalName;
	public static volatile SingularAttribute<Show, String> originCountries;
	public static volatile SingularAttribute<Show, String> overview;
	public static volatile SingularAttribute<Show, String> posterPath;
	public static volatile SingularAttribute<Show, String> productionCountries;
	public static volatile SingularAttribute<Show, Double> revenue;
	public static volatile SingularAttribute<Show, Integer> runtime;
	public static volatile SingularAttribute<Show, String> status;
	public static volatile SingularAttribute<Show, String> type;
	public static volatile SingularAttribute<Show, Double> voteAverage;
	public static volatile SingularAttribute<Show, Integer> voteCount;
	public static volatile ListAttribute<Show, ShowGenre> showGenres;
	public static volatile ListAttribute<Show, OnTheAirShow> onTheAirShows;
	public static volatile ListAttribute<Show, ShowFavorite> showFavorites;
	public static volatile ListAttribute<Show, ShowReview> showReviews;
	public static volatile SingularAttribute<Show, Air2dayShow> air2dayShow;
}
