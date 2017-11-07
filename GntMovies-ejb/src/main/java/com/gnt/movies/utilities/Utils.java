package com.gnt.movies.utilities;

public class Utils 
{
	public static String API_KEY = "?api_key=eaf5fc7d22157774a158a75a3ed6fe9c";
	
	/** URLs for TheMovieDB**/
	
	/** URL example for getting movie details: https://api.themoviedb.org/3/movie/120?api_key=eaf5fc7d22157774a158a75a3ed6fe9c&language=en-US **/
	/** URL example for getting show details: https://api.themoviedb.org/3/movie/120?api_key=eaf5fc7d22157774a158a75a3ed6fe9c&language=en-US **/
	public static String GENERAL_MOVIE_URL = "https://api.themoviedb.org/3/movie/";
	public static String GENERAL_SHOW_URL = "https://api.themoviedb.org/3/tv/";
	/** URL example for upcoming movies: https://api.themoviedb.org/3/movie/upcoming?api_key=eaf5fc7d22157774a158a75a3ed6fe9c&language=en-US&page=1**/
	public static String UPCOMING_MOVIES_URL = "https://api.themoviedb.org/3/movie/upcoming";
	/** URL example for now playing movies: https://api.themoviedb.org/3/movie/now_playing?api_key=eaf5fc7d22157774a158a75a3ed6fe9c&language=en-US&page=1**/
	public static String NOW_PLAYING_MOVIES_URL = "https://api.themoviedb.org/3/movie/now_playing";
	/** URL example for air2day shows: https://api.themoviedb.org/3/tv/airing_today?api_key=eaf5fc7d22157774a158a75a3ed6fe9c&language=en-US&page=1**/
	public static String AIR2DAY_SHOWS_URL = "https://api.themoviedb.org/3/tv/airing_today";
	/** URL example for on the air shows: https://api.themoviedb.org/3/tv/airing_today?api_key=eaf5fc7d22157774a158a75a3ed6fe9c&language=en-US&page=1**/
	public static String ON_THE_AIR_SHOWS_URL = "https://api.themoviedb.org/3/tv/on_the_air";
	/** URL example for getting cast and crew for movies: https://api.themoviedb.org/3/movie/120/credits?api_key=eaf5fc7d22157774a158a75a3ed6fe9c **/
	public static String CREW_CAST_MOVIES_URL = "/credits";
	
	public static String LANGUAGE_FOR_URL = "&language=en-US1";
	public static String NUMBER_PAGE_FOR_URL = "&page=";
	
	/** ================================================================================================================================================== **/
	/** User named queries**/
	public static String USER_FIND_BY_ID = "User.findById";
	public static String USER_FIND_BY_USERNAME = "User.findByUsername";
	public static String USER_FIND_BY_EMAIL = "User.findByEmail";
	public static String USER_FIND_BY_PASSWORD ="User.findByPassword";
	public static String USER_FIND_BY_AGE = "User.findByAge";
	
	/** Upcoming movie queries**/
	public static String UPCOMING_MOVIE_FIND_BY_ID = "UpcomingMovie.findById";
	public static String UPCOMING_MOVIE_FIND_BY_MOVIE_ID = "UpcomingMovie.findByMovieId";
	public static String UPCOMING_MOVIE_FIND_BY_IDTMDB = "UpcomingMovie.findByIdTmdb";
	public static String UPCOMING_MOVIE_FIND_ALL = "UpcomingMovie.findAll";
	
	/** Show review queries**/
	public static String SHOW_REVIEW_FIND_BY_ID = "ShowReview.findById";
	public static String SHOW_REVIEW_FIND_BY_RATING = "ShowReview.findByRating";
	public static String SHOW_REVIEW_FIND_BY_USER_ID = "ShowReview.findByUserId";
	public static String SHOW_REVIEW_FIND_BY_SHOW_ID = "ShowReview.findByShowId";
	
	/** Show genre queries**/
	public static String SHOW_GENRE_FIND_BY_ID = "ShowGenre.findById";
	public static String SHOW_GENRE_FIND_BY_GENRE_ID = "ShowGenre.findByGenreId";
	public static String SHOW_GENRE_FIND_BY_SHOW_ID = "ShowGenre.findByShowId";
	
	/** Show favorite queries**/
	public static String SHOW_FAVORITE_FIND_BY_ID = "ShowFavorite.findById";
	public static String SHOW_FAVORITE_FIND_BY_USER_ID = "ShowFavorite.findByUserId";
	public static String SHOW_FAVORITE_FIND_BY_SHOW_ID = "ShowFavorite.findByShowId";
	
	/** Show named queries**/
	public static String SHOW_FIND_BY_ID = "Show.findById";
	public static String SHOW_FIND_BY_NAME = "Show.findByName";
	public static String SHOW_FIND_BY_IDTMDB = "Show.findByIdTmdb";
	
	/** Air2day show named queries**/
	public static String AIR2DAY_SHOW_FIND_BY_SHOW_ID = "Air2dayShow.findByShowId";
	public static String AIR2DAY_SHOW_FIND_BY_ID = "Air2dayShow.findById";
	public static String AIR2DAY_SHOW_FIND_BY_IDTMDB_ID = "Air2dayShow.findByIdTmdb";
	public static String AIR2DAY_SHOW_FIND_ALL = "Air2dayShow.findAll";
	
	/** On the air show named queries**/
	public static String ON_THE_AIR_SHOW_FIND_BY_ID = "OnTheAirShow.findById";
	public static String ON_THE_AIR_SHOW_FIND_BY_IDTMDB = "OnTheAirShow.findByIdTmdb";
	public static String ON_THE_AIR_SHOW_FIND_BY_SHOW_ID = "OnTheAirShow.findByShowId";
	public static String ON_THE_AIR_SHOW_FIND_ALL = "OnTheAirShow.findAll";
	
	/** Movie named queries**/
	public static String MOVIE_FIND_BY_ID = "Movie.findById";
	public static String MOVIE_FIND_BY_TITLE = "Movie.findByTitle";
	public static String MOVIE_FIND_BY_IDTMDB = "Movie.findByIdTmdb";
	
	/** Movie favorite named queries**/
	public static String MOVIE_FAVORITE_FIND_BY_ID = "MovieFavorite.findById";
	public static String MOVIE_FAVORITE_FIND_BY_MOVIE_ID = "MovieFavorite.findByMovieId";
	public static String MOVIE_FAVORITE_FIND_BY_USER_ID = "MovieFavorite.findByUserId";
	
	/** Movie review named queries**/
	public static String MOVIE_REVIEW_FIND_BY_ID = "MovieReview.findById";
	public static String MOVIE_REVIEW_FIND_BY_USER_ID = "MovieReview.findByUserId";
	public static String MOVIE_REVIEW_FIND_BY_MOVIE_ID = "MovieReview.findByMovieId";
	
	/** Now playing movie named queries**/
	public static String NOW_PLAYING_MOVIE_FIND_BY_ID = "NowPlayingMovie.findById";
	public static String NOW_PLAYING_MOVIE_FIND_BY_IDTMDB = "NowPlayingMovie.findByIdTmdb";
	public static String NOW_PLAYING_MOVIE_FIND_BY_MOVIE_ID = "NowPlayingMovie.findByMovieId";
	public static String NOW_PLAYING_MOVIE_FIND_ALL = "NowPlayingMovie.findAll";
	
	/** Genre named queries**/
	public static String GENRE_FIND_BY_NAME = "Genre.findByName";
	public static String GENRE_FIND_BY_ID = "Genre.findById";
	
	/** Movie genre named queries**/
	public static String MOVIE_GENRE_FIND_BY_ID = "MovieGenre.findById";
	public static String MOVIE_GENRE_FIND_BY_MOVIE_ID = "MovieGenre.findByMovieId";
	public static String MOVIE_GENRE_FIND_BY_GENRE_ID = "MovieGenre.findByGenreId";
	
}
