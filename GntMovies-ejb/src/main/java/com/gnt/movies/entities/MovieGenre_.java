package com.gnt.movies.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:15:24.824+0200")
@StaticMetamodel(MovieGenre.class)
public class MovieGenre_ {
	public static volatile SingularAttribute<MovieGenre, Integer> id;
	public static volatile SingularAttribute<MovieGenre, Genre> genre;
	public static volatile SingularAttribute<MovieGenre, Movie> movie;
}
