package com.gnt.movies.entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:24:15.094+0200")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, Integer> age;
	public static volatile SingularAttribute<User, LocalDate> birthdate;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> photo;
	public static volatile SingularAttribute<User, Timestamp> registrationDate;
	public static volatile SingularAttribute<User, String> username;
	public static volatile ListAttribute<User, MovieFavorite> movieFavorites;
	public static volatile ListAttribute<User, MovieReview> movieReviews;
	public static volatile ListAttribute<User, ShowFavorite> showFavorites;
	public static volatile ListAttribute<User, ShowReview> showReviews;
}
