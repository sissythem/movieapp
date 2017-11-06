package com.gnt.movies.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:24:15.002+0200")
@StaticMetamodel(Genre.class)
public class Genre_ {
	public static volatile SingularAttribute<Genre, Integer> id;
	public static volatile SingularAttribute<Genre, String> name;
	public static volatile ListAttribute<Genre, MovieGenre> movieGenres;
	public static volatile ListAttribute<Genre, ShowGenre> showGenres;
}
