package com.gnt.movies.entities;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:24:15.029+0200")
@StaticMetamodel(Movie.class)
public class Movie_ {
	public static volatile SingularAttribute<Movie, Integer> id;
	public static volatile SingularAttribute<Movie, Byte> adult;
	public static volatile SingularAttribute<Movie, Double> budget;
	public static volatile SingularAttribute<Movie, String> cast;
	public static volatile SingularAttribute<Movie, String> creator;
	public static volatile SingularAttribute<Movie, String> crew;
	public static volatile SingularAttribute<Movie, String> homepage;
	public static volatile SingularAttribute<Movie, Integer> idTmdb;
	public static volatile SingularAttribute<Movie, String> imdbId;
	public static volatile SingularAttribute<Movie, String> originalLanguage;
	public static volatile SingularAttribute<Movie, String> originalTitle;
	public static volatile SingularAttribute<Movie, String> overview;
	public static volatile SingularAttribute<Movie, String> posterPath;
	public static volatile SingularAttribute<Movie, String> productionCountries;
	public static volatile SingularAttribute<Movie, LocalDate> releaseDate;
	public static volatile SingularAttribute<Movie, Double> revenue;
	public static volatile SingularAttribute<Movie, Integer> runtime;
	public static volatile SingularAttribute<Movie, String> status;
	public static volatile SingularAttribute<Movie, String> title;
	public static volatile SingularAttribute<Movie, Double> voteAverage;
	public static volatile SingularAttribute<Movie, Integer> voteCount;
	public static volatile ListAttribute<Movie, MovieGenre> movieGenres;
	public static volatile ListAttribute<Movie, MovieFavorite> movieFavorites;
	public static volatile ListAttribute<Movie, MovieReview> movieReviews;
	public static volatile SingularAttribute<Movie, NowPlayingMovie> nowPlayingMovie;
	public static volatile SingularAttribute<Movie, UpcomingMovie> upcomingMovie;
}
