package com.gnt.movies.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:15:24.833+0200")
@StaticMetamodel(MovieReview.class)
public class MovieReview_ {
	public static volatile SingularAttribute<MovieReview, Integer> id;
	public static volatile SingularAttribute<MovieReview, String> comment;
	public static volatile SingularAttribute<MovieReview, Integer> rating;
	public static volatile SingularAttribute<MovieReview, Movie> movie;
	public static volatile SingularAttribute<MovieReview, User> user;
}
