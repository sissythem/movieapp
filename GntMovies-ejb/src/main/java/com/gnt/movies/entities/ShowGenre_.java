package com.gnt.movies.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:15:24.883+0200")
@StaticMetamodel(ShowGenre.class)
public class ShowGenre_ {
	public static volatile SingularAttribute<ShowGenre, Integer> id;
	public static volatile SingularAttribute<ShowGenre, Genre> genre;
	public static volatile SingularAttribute<ShowGenre, Show> show;
}
