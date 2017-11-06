package com.gnt.movies.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-06T11:15:24.843+0200")
@StaticMetamodel(NowPlayingMovie.class)
public class NowPlayingMovie_ {
	public static volatile SingularAttribute<NowPlayingMovie, Integer> id;
	public static volatile SingularAttribute<NowPlayingMovie, Integer> idTmdb;
	public static volatile SingularAttribute<NowPlayingMovie, Movie> movie;
}
