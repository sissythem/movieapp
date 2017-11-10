package com.gnt.movies.beans;

import java.util.ArrayList;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.MovieGenre;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.ApiGenre;
import com.gnt.movies.theMovieDB.ApiMovieDetails;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.utilities.APIClient;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
public class SchedulerBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(SchedulerBean.class);
	@PersistenceContext
	private EntityManager em;

	@EJB
	UpcomingMovieBean upcomingMovieBean;

	@EJB
	NowPlayingMovieBean nowPlayingMovieBean;

	@EJB
	OnTheAirShowBean onTheAirShowBean;

	@EJB
	Air2dayShowBean air2dayShowBean;

	@EJB
	MovieBean movieBean;

	@EJB
	ShowBean showBean;

	@EJB
	GenreBean genreBean;

	@EJB
	MovieGenreBean movieGenreBean;

	@EJB
	ShowGenreBean showGenreBean;

	public SchedulerBean() {

	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	private static boolean flag = false;
	@Schedule(dayOfWeek = "*", hour = "*", minute = "*/1",second="*")
	public void getUpcomingMovies() {
		if(flag)
			return;
		flag=true;
		logger.info("Scheduler checking for upcomming movies");
		ArrayList<ApiNewMovie> upcomingMoviesAPI = APIClient.getUpcomingMoviesFromAPI();
		for (ApiNewMovie upcomingMovieAPI : upcomingMoviesAPI) {
			checkUpcomingMovie(upcomingMovieAPI);
			break;
		}
		logger.info("Done checking for upcomming movies");
		flag = false;
	}

	// @Schedule(dayOfWeek = "*", hour = "*", minute = "*")
	// public void test() {
	// logger.info("Scheduler...");
	// }

	/*
	 * @Schedule(dayOfWeek = "*", hour = "*", minute = "*") public void
	 * checkUpcomingMovies() {
	 * logger.info("Scheduler checking for upcomming movies");
	 * ArrayList<ApiNewMovie> upcomingMoviesAPI =
	 * APIClient.getUpcomingMoviesFromAPI(); ArrayList<Integer> idTmdbs = new
	 * ArrayList<>(); for (ApiNewMovie upcomingMovieAPI : upcomingMoviesAPI) {
	 * idTmdbs.add(upcomingMovieAPI.getId()); if
	 * (upcomingMovieBean.findMovieByIdTmdb(upcomingMovieAPI.getId()) == null) {
	 * addNewUpcomingMovies(upcomingMovieAPI); } }
	 * upcomingMovieBean.checkUpcomingMoviesToBeDeleted(idTmdbs); }
	 */
	// @Schedule(dayOfWeek = "*", hour = "0")
	// public void checkNowPlayingMovies() {
	//
	// ArrayList<ApiNewMovie> nowPlayingMoviesAPI =
	// APIClient.getNowPlayingMoviesFromAPI();
	// ArrayList<Integer> idTmdbs = new ArrayList<>();
	// for (ApiNewMovie nowPlayingMovieAPI : nowPlayingMoviesAPI) {
	// if (nowPlayingMovieBean.findMovieByIdTmdb(nowPlayingMovieAPI.getId()) ==
	// null) {
	// addNewNowPlayingMovies(nowPlayingMovieAPI);
	// }
	// }
	// nowPlayingMovieBean.checkNowPlayingMoviesToBeDeleted(idTmdbs);
	// }
	//
	// @Schedule(dayOfWeek = "*", hour = "0")
	// public void checkOnTheAirShow() {
	//
	// ArrayList<ApiNewShow> onTheAirShowsAPI = APIClient.getOnTheAirShowsFromAPI();
	//
	// for (ApiNewShow onTheAirShowAPI : onTheAirShowsAPI) {
	// if (onTheAirShowBean.findOnTheAirShowByIdTmdb(onTheAirShowAPI.getId()) ==
	// null) {
	// addNewOnTheAirShows(onTheAirShowAPI);
	// }
	// }
	// }
	//
	// @Schedule(dayOfWeek = "*", hour = "0")
	// public void checkAir2dayShows() {
	//
	// ArrayList<ApiNewShow> apiNewShow = APIClient.getAir2dayShowsFromAPI();
	//
	// for (ApiNewShow newShowAPI : apiNewShow) {
	// if (air2dayShowBean.findAir2dayShowByIdTmdb(newShowAPI.getId()) == null) {
	// addNewAir2dayShows(newShowAPI);
	// }
	// }
	// }
	//

	//
	// public ApiShowDetails updateShowWithDetailsFromAPI(Show show) {
	// ApiShowDetails showDetails =
	// APIClient.getShowDetailsFromAPI(show.getIdTmdb());
	// showBean.updateShowWithDetails(show, showDetails);
	// return showDetails;
	// }
	//
	public void checkUpcomingMovie(ApiNewMovie movieAPI) {
		logger.info("Adding movie with tmdbId=" + movieAPI.getId());
//check if movie exists....
//check if delete upcomming....
		UpcomingMovie upcomingMovie = upcomingMovieBean.createUpcomingMovieFromAPI(movieAPI);

		Movie movie = addNewMovie(movieAPI);
		upcomingMovie.setMovie(movie);
		upcomingMovieBean.addUpcomingMovie(upcomingMovie);

		// UpcomingMovie.setMovie(movie);
		// upcomingMovieBean.addUpcomingMovie(UpcomingMovie);
	}

	/*
	 * public void addNewUpcomingMovies(ApiNewMovie newMovieAPI) {
	 * logger.info("Adding movie with tmdbId="+newMovieAPI.getId()); UpcomingMovie
	 * newUpcomingMovie = upcomingMovieBean.createUpcomingMovieFromAPI(newMovieAPI);
	 * Movie newMovie = addNewMovieWithGenres(newMovieAPI);
	 * newUpcomingMovie.setMovie(newMovie);
	 * upcomingMovieBean.addUpcomingMovie(newUpcomingMovie); }
	 *///
		// public void addNewNowPlayingMovies(ApiNewMovie newMovieAPI) {
		// NowPlayingMovie newNowPlayingMovie =
		// nowPlayingMovieBean.createNowPlayingMovieFromAPI(newMovieAPI);
		// Movie movie;
		// if (movieBean.findMovieByIdTmdb(newMovieAPI.getId()) == null) {
		// movie = addNewMovieWithGenres(newMovieAPI);
		// } else {
		// movie = movieBean.findMovieByIdTmdb(newMovieAPI.getId());
		// }
		// newNowPlayingMovie.setMovie(movie);
		// nowPlayingMovieBean.addNowPlayingMovie(newNowPlayingMovie);
		// }
		//
	// public Movie addNewMovieWithGenres(ApiNewMovie newMovieAPI) {
	// logger.info("addNewMovieWithGenres movie with tmdbId=" +
	// newMovieAPI.getId());
	// Movie newMovie = movieBean.createMovieFromAPI(newMovieAPI);
	//
	// ApiMovieDetails movieDetails = updateMovieWithDetailsFromAPI(newMovie);
	// movieBean.addMovie(newMovie);
	// newMovie.setId(movieBean.findMovieByIdTmdb(newMovie.getIdTmdb()).getId());
	// return newMovie;
	// }

	public Movie addNewMovie(ApiNewMovie movieApi) {
		logger.info("addNewMovieWithGenres movie with tmdbId=" + movieApi.getId());
		Movie movie = movieBean.createMovieFromAPI(movieApi);

		ApiMovieDetails movieDetails = APIClient.getMovieDetailsFromAPI(movie.getIdTmdb());
		updateGenres(movieDetails.getGenresAPI());
		
		
		movieBean.addMovie(movie);
		movieBean.updateMovieWithDetails(movie, movieDetails);
		for (MovieGenre genre : movie.getMovieGenres()) {
			movieGenreBean.addMovieGenre(genre);
		}
		return movie;
	}

	/*public void addNewOnTheAirShows(ApiNewShow newShowAPI) {
		OnTheAirShow newOnTheAirShow = onTheAirShowBean.createOnTheAirShowFromAPI(newShowAPI.getId());
		Show show;
		if (showBean.findShowByIdTmdb(newShowAPI.getId()) == null) {

			show = addNewShowWithGenres(newShowAPI);

		} else {
			show = showBean.findShowByIdTmdb(newShowAPI.getId());
		}
		newOnTheAirShow.setShow(show);
		onTheAirShowBean.addOnTheAirShow(newOnTheAirShow);
	}

	public void addNewAir2dayShows(ApiNewShow newShowAPI) {
		Air2dayShow newAir2dayShow = air2dayShowBean.createAir2dayShowFromAPI(newShowAPI.getId());
		Show show;
		if (showBean.findShowByIdTmdb(newShowAPI.getId()) == null) {
			show = addNewShowWithGenres(newShowAPI);

		} else {
			show = showBean.findShowByIdTmdb(newShowAPI.getId());
		}
		newAir2dayShow.setShow(show);
		air2dayShowBean.addAir2DayShow(newAir2dayShow);
	}

	public Show addNewShowWithGenres(ApiNewShow newShowAPI) {
		Show newShow = showBean.createShowFromAPI(newShowAPI);
		ApiShowDetails showDetails = updateShowWithDetailsFromAPI(newShow);
		addShowGenres(showDetails);
		showBean.addShow(newShow);
		newShow.setId(showBean.findShowByIdTmdb(newShow.getIdTmdb()).getId());
		return newShow;
	}

	public void addMovieGenres(ApiMovieDetails movieDetails, Movie movie) {
		logger.info("addMovieGenres movie with tmdbId=" + movie.getIdTmdb() + " and id=" + movie.getId());
		ArrayList<ApiGenre> apiGenre = movieDetails.getGenresAPI();
		for (ApiGenre genreAPI : apiGenre) {
			Genre genre = genreBean.findGenreByName(genreAPI.getName());
			MovieGenre movieGenre = new MovieGenre(movie, genre);
			movieGenreBean.addMovieGenre(movieGenre);
		}
	}*/

	HashSet<String> genres;
	private void updateGenres(ArrayList<ApiGenre> genresApi) {
		genres = genreBean.getAllGenreNames();
		
		for (ApiGenre apiGenre : genresApi) {
			if(!genreExists(apiGenre)) {
				addGenre(apiGenre);
			}
		}
	}
	
	private boolean genreExists(ApiGenre apiGenre) {
			if (!genres.contains(apiGenre.getName()))
				return false;
			else return true;
	}

	private void addGenre(ApiGenre genreAPI) {
		Genre genre = new Genre(genreAPI.getName());
		genreBean.addGenre(genre);
		genres.add(genreAPI.getName());
	}
	//
	// public void addShowGenres(ApiShowDetails showDetails) {
	// ArrayList<ApiGenre> apiGenres = showDetails.getGenresAPI();
	//
	// for (ApiGenre genreAPI : apiGenres) {
	// if (genreBean.findGenreByName(genreAPI.getName()) == null) {
	//
	// Genre genre = new Genre(genreAPI.getName());
	// genreBean.addGenre(genre);
	// }
	// Show show = showBean.findShowByIdTmdb(showDetails.getId());
	// Genre genre = genreBean.findGenreByName(genreAPI.getName());
	// ShowGenre showGenre = new ShowGenre(show, genre);
	// showGenreBean.addShowGenre(showGenre);
	// }
	// }

}
