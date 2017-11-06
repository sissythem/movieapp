package com.gnt.movies.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:15:24.815+0200")
@StaticMetamodel(MovieFavorite.class)
public class MovieFavorite_ {
	public static volatile SingularAttribute<MovieFavorite, Integer> id;
	public static volatile SingularAttribute<MovieFavorite, Movie> movie;
	public static volatile SingularAttribute<MovieFavorite, User> user;
}
