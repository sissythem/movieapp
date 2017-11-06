package com.gnt.movies.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:15:24.873+0200")
@StaticMetamodel(ShowFavorite.class)
public class ShowFavorite_ {
	public static volatile SingularAttribute<ShowFavorite, Integer> id;
	public static volatile SingularAttribute<ShowFavorite, Show> show;
	public static volatile SingularAttribute<ShowFavorite, User> user;
}
