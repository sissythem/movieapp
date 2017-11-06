package com.gnt.movies.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:15:24.893+0200")
@StaticMetamodel(ShowReview.class)
public class ShowReview_ {
	public static volatile SingularAttribute<ShowReview, Integer> id;
	public static volatile SingularAttribute<ShowReview, String> comment;
	public static volatile SingularAttribute<ShowReview, Integer> rating;
	public static volatile SingularAttribute<ShowReview, Show> show;
	public static volatile SingularAttribute<ShowReview, User> user;
}
