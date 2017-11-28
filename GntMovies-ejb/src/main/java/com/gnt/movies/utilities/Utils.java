package com.gnt.movies.utilities;

public class Utils 
{
	public static String API_KEY = "?api_key=eaf5fc7d22157774a158a75a3ed6fe9c";
	
	/** URLs for TheMovieDB
	 * ====================
	 * **/
	
	/** URL example for getting movie details: https://api.themoviedb.org/3/movie/120?api_key=eaf5fc7d22157774a158a75a3ed6fe9c**/
	/** URL example for getting show details: https://api.themoviedb.org/3/tv/1418?api_key=eaf5fc7d22157774a158a75a3ed6fe9c**/

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
	public static String CREW_CAST_URL = ",credits";
	/** URL example for getting cast and crew for movies: https://api.themoviedb.org/3/movie/157336?api_key=eaf5fc7d22157774a158a75a3ed6fe9c&append_to_response=images **/
	public static String IMAGES_URL = "&append_to_response=images";
	/** URL example for getting all movie genres: https://api.themoviedb.org/3/genre/movie/list?api_key=eaf5fc7d22157774a158a75a3ed6fe9c&language=en-US
	 * */
	public static String MOVIE_GENRES = "https://api.themoviedb.org/3/genre/movie/list";
	/** URL example for getting all show genres: https://api.themoviedb.org/3/genre/tv/list?api_key=eaf5fc7d22157774a158a75a3ed6fe9c&language=en-US
	 * */
	public static String SHOW_GENRES = "https://api.themoviedb.org/3/genre/tv/list";
	public static String LANGUAGE_FOR_URL = "&language=en-US1";
	public static String NUMBER_PAGE_FOR_URL = "&page=";
	
	/** ================================================================================================================================================== **/
	/** User named queries**/
	public static String USER_FIND_BY_ID = "User.findById";
	public static String USER_FIND_BY_USERNAME = "User.findByUsername";
	public static String USER_FIND_BY_EMAIL = "User.findByEmail";
	public static String USER_FIND_BY_PASSWORD ="User.findByPassword";
	public static String USER_FIND_BY_AGE = "User.findByAge";
	public static String USER_CHECK_USERNAME = "User.checkUsername";
	public static String USER_CHECK_EMAIL = "User.checkEmail";
	public static String USER_LOGIN = "User.login";
	public static String USER_LOGIN_TOKEN = "User.loginWithToken";
	
	/** Upcoming movie queries**/
	public static String UPCOMING_MOVIE_FIND_BY_ID = "UpcomingMovie.findById";
	public static String UPCOMING_MOVIE_FIND_BY_MOVIE_ID = "UpcomingMovie.findByMovieId";
	public static String UPCOMING_MOVIE_FIND_BY_IDTMDB = "UpcomingMovie.findByIdTmdb";
	public static String UPCOMING_MOVIE_FIND_ALL = "UpcomingMovie.findAll";
	public static String UPCOMING_MOVIE_GET_ALL_IDTMDB = "UpcomingMovie.getAllIdTmdb";
	public static String UPCOMING_MOVIE_DELETE_BY_IDTMDB = "UpcomingMovie.deleteByIdTmdb";
	
	/** Show review queries**/
	public static String SHOW_REVIEW_FIND_BY_ID = "ShowReview.findById";
	public static String SHOW_REVIEW_FIND_BY_RATING = "ShowReview.findByRating";
	public static String SHOW_REVIEW_FIND_BY_USER_ID = "ShowReview.findByUserId";
	public static String SHOW_REVIEW_FIND_BY_SHOW_ID = "ShowReview.findByShowId";
	public static String SHOW_REVIEW_FIND_ALL = "ShowReview.findAll";
	
	/** Show genre queries**/
	public static String SHOW_GENRE_FIND_BY_ID = "ShowGenre.findById";
	public static String SHOW_GENRE_FIND_BY_GENRE_ID = "ShowGenre.findByGenreId";
	public static String SHOW_GENRE_FIND_BY_SHOW_ID = "ShowGenre.findByShowId";
	
	/** Show favorite queries**/
	public static String SHOW_FAVORITE_FIND_BY_ID = "ShowFavorite.findById";
	public static String SHOW_FAVORITE_FIND_BY_USER_ID = "ShowFavorite.findByUserId";
	public static String SHOW_FAVORITE_FIND_BY_SHOW_ID = "ShowFavorite.findByShowId";
	public static String SHOW_FAVORITE_FIND_ALL = "ShowFavorite.findAll";
	
	/** Show named queries**/
	public static String SHOW_FIND_BY_ID = "Show.findById";
	public static String SHOW_FIND_BY_NAME = "Show.findByName";
	public static String SHOW_FIND_BY_IDTMDB = "Show.findByIdTmdb";
	
	/** Air2day show named queries**/
	public static String AIR2DAY_SHOW_FIND_BY_SHOW_ID = "Air2dayShow.findByShowId";
	public static String AIR2DAY_SHOW_FIND_BY_ID = "Air2dayShow.findById";
	public static String AIR2DAY_SHOW_FIND_BY_IDTMDB_ID = "Air2dayShow.findByIdTmdb";
	public static String AIR2DAY_SHOW_FIND_ALL = "Air2dayShow.findAll";
	public static String AIR2DAY_SHOW_GET_ALL_IDTMDB = "Air2dayShow.getAllIdTmdb";
	public static String AIR2DAY_SHOW_DELETE_BY_IDTMDB = "Air2dayShow.deleteByIdTmdb";
	
	/** On the air show named queries**/
	public static String ON_THE_AIR_SHOW_FIND_BY_ID = "OnTheAirShow.findById";
	public static String ON_THE_AIR_SHOW_FIND_BY_IDTMDB = "OnTheAirShow.findByIdTmdb";
	public static String ON_THE_AIR_SHOW_FIND_BY_SHOW_ID = "OnTheAirShow.findByShowId";
	public static String ON_THE_AIR_SHOW_FIND_ALL = "OnTheAirShow.findAll";
	public static String ON_THE_AIR_SHOW_GET_ALL_IDTMDB = "OnTheAirShow.getAllIdTmdb";
	public static String ON_THE_AIR_DELETE_BY_IDTMDB = "OnTheAirShow.deleteByIdTmdb";
	
	/** Movie named queries**/
	public static String MOVIE_FIND_ALL = "Movie.findAll";
	public static String MOVIE_FIND_BY_ID = "Movie.findById";
	public static String MOVIE_FIND_BY_TITLE = "Movie.findByTitle";
	public static String MOVIE_FIND_BY_IDTMDB = "Movie.findByIdTmdb";
	
	/** Movie favorite named queries**/
	public static String MOVIE_FAVORITE_FIND_BY_ID = "MovieFavorite.findById";
	public static String MOVIE_FAVORITE_FIND_BY_MOVIE_ID = "MovieFavorite.findByMovieId";
	public static String MOVIE_FAVORITE_FIND_BY_USER_ID = "MovieFavorite.findByUserId";
	public static String MOVIE_FAVORITE_FIND_ALL = "MovieFavorite.findAll";
	
	/** Movie review named queries**/
	public static String MOVIE_REVIEW_FIND_BY_ID = "MovieReview.findById";
	public static String MOVIE_REVIEW_FIND_BY_USER_ID = "MovieReview.findByUserId";
	public static String MOVIE_REVIEW_FIND_BY_MOVIE_ID = "MovieReview.findByMovieId";
	public static String MOVIE_REVIEW_FIND_ALL = "MovieReview.findAll";
	public static String MOVIE_REVIEW = "MovieReview.findMovieReview";
	
	/** Now playing movie named queries**/
	public static String NOW_PLAYING_MOVIE_FIND_BY_ID = "NowPlayingMovie.findById";
	public static String NOW_PLAYING_MOVIE_FIND_BY_IDTMDB = "NowPlayingMovie.findByIdTmdb";
	public static String NOW_PLAYING_MOVIE_FIND_BY_MOVIE_ID = "NowPlayingMovie.findByMovieId";
	public static String NOW_PLAYING_MOVIE_FIND_ALL = "NowPlayingMovie.findAll";
	public static String NOW_PLAYING_MOVIE_GET_ALL_IDTMDB = "NowPlayingMovie.getAllIdTmdb";
	public static String NOW_PLAYING_MOVIE_DELETE_BY_IDTMDB = "NowPlayingMovie.deleteByIdTmdb";
	
	/** Genre named queries**/
	public static String GENRE_FIND_BY_NAME = "Genre.findByName";
	public static String GENRE_FIND_BY_ID = "Genre.findById";
	public static String GENRE_FIND_ALL = "Genre.findAll";
	public static String GENRE_FIND_ALL_NAMES= "Genre.findAllNames";
	
	/** Movie genre named queries**/
	public static String MOVIE_GENRE_FIND_BY_ID = "MovieGenre.findById";
	public static String MOVIE_GENRE_FIND_BY_MOVIE_ID = "MovieGenre.findByMovieId";
	public static String MOVIE_GENRE_FIND_BY_GENRE_ID = "MovieGenre.findByGenreId";
	
	/** Images named queries**/
	public static String IMAGE_FIND_BY_ID = "Image.findById";
	
}
