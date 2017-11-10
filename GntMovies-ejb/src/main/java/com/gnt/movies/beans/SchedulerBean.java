package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.entities.Air2dayShow;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.MovieGenre;
import com.gnt.movies.entities.NowPlayingMovie;
import com.gnt.movies.entities.OnTheAirShow;
import com.gnt.movies.entities.Show;
import com.gnt.movies.entities.ShowGenre;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.ApiGenres;
import com.gnt.movies.theMovieDB.ApiMovieDetails;
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.theMovieDB.ApiShowDetails;
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

	@Schedule(dayOfWeek="*", hour="*",minute="*")
    public void test() {
		logger.info("Scheduler...");
    }

	@Schedule(dayOfWeek = "*", hour = "*",minute="*")
	public void checkUpcomingMovies() {
		
		logger.info("Scheduler checking for upcomming movies");
		ArrayList<ApiNewMovie> upcomingMoviesAPI = APIClient.getUpcomingMoviesFromAPI();
		ArrayList<Integer> idTmdbs = new ArrayList<>();
		for (ApiNewMovie upcomingMovieAPI : upcomingMoviesAPI) {
			idTmdbs.add(upcomingMovieAPI.getId());
			if (upcomingMovieBean.findMovieByIdTmdb(upcomingMovieAPI.getId()) == null) {
				addNewUpcomingMovies(upcomingMovieAPI);
			}
		}
		upcomingMovieBean.checkUpcomingMoviesToBeDeleted(idTmdbs);
	}

	@Schedule(dayOfWeek = "*", hour = "0")
	public void checkNowPlayingMovies() {
		
		logger.info("Scheduler checking for now playing movies");
		ArrayList<ApiNewMovie> nowPlayingMoviesAPI = APIClient.getNowPlayingMoviesFromAPI();
		ArrayList<Integer> idTmdbs = new ArrayList<>();
		for (ApiNewMovie nowPlayingMovieAPI : nowPlayingMoviesAPI) {
			idTmdbs.add(nowPlayingMovieAPI.getId());
			if (nowPlayingMovieBean.findMovieByIdTmdb(nowPlayingMovieAPI.getId()) == null) {
				addNewNowPlayingMovies(nowPlayingMovieAPI);
			}
		}
		nowPlayingMovieBean.checkNowPlayingMoviesToBeDeleted(idTmdbs);
	}

	@Schedule(dayOfWeek = "*", hour = "0")
	public void checkOnTheAirShow() {
		
		logger.info("Scheduler checking for on the air shows");
		ArrayList<ApiNewShow> onTheAirShowsAPI = APIClient.getOnTheAirShowsFromAPI();
		ArrayList<Integer> idTmdbs = new ArrayList<>();

		for (ApiNewShow onTheAirShowAPI : onTheAirShowsAPI) {
			idTmdbs.add(onTheAirShowAPI.getId());
			if (onTheAirShowBean.findOnTheAirShowByIdTmdb(onTheAirShowAPI.getId()) == null) {
				addNewOnTheAirShows(onTheAirShowAPI);
			}
		}
		onTheAirShowBean.checkOnTheAirShowsToBeDeleted(idTmdbs);
	}

	@Schedule(dayOfWeek = "*", hour = "0")
	public void checkAir2dayShows() {

		logger.info("Scheduler checking for air today shows");
		ArrayList<ApiNewShow> apiNewShow = APIClient.getAir2dayShowsFromAPI();
		ArrayList<Integer> idTmdbs = new ArrayList<>();

		for (ApiNewShow newShowAPI : apiNewShow) {
			idTmdbs.add(newShowAPI.getId());
			if (air2dayShowBean.findAir2dayShowByIdTmdb(newShowAPI.getId()) == null) {
				addNewAir2dayShows(newShowAPI);
			}
		}
		air2dayShowBean.checkAir2dayShowsToBeDeleted(idTmdbs);
	}

	public ApiMovieDetails updateMovieWithDetailsFromAPI(Movie movie) {

		ApiMovieDetails movieDetails = APIClient.getMovieDetailsFromAPI(movie.getIdTmdb());
		movieBean.updateMovieWithDetails(movie, movieDetails);
		return movieDetails;
	}

	public ApiShowDetails updateShowWithDetailsFromAPI(Show show) {
		ApiShowDetails showDetails = APIClient.getShowDetailsFromAPI(show.getIdTmdb());
		showBean.updateShowWithDetails(show, showDetails);
		return showDetails;
	}
	
	public void addNewUpcomingMovies(ApiNewMovie newMovieAPI) {
		UpcomingMovie newUpcomingMovie = new UpcomingMovie();
		Movie newMovie = addNewMovieWithGenres(newMovieAPI);
		newMovie.setUpcomingMovie(newUpcomingMovie);
		movieBean.addMovie(newMovie);
	}
	
	public void addNewNowPlayingMovies(ApiNewMovie newMovieAPI) {
		NowPlayingMovie newNowPlayingMovie = nowPlayingMovieBean.createNowPlayingMovieFromAPI(newMovieAPI);
		Movie movie;
		if (movieBean.findMovieByIdTmdb(newMovieAPI.getId()) == null) {
			movie = addNewMovieWithGenres(newMovieAPI);
		}
		else {
			movie = movieBean.findMovieByIdTmdb(newMovieAPI.getId());
		}
		newNowPlayingMovie.setMovie(movie);
		nowPlayingMovieBean.addNowPlayingMovie(newNowPlayingMovie);
	}
	
	public Movie addNewMovieWithGenres(ApiNewMovie newMovieAPI) {
		Movie newMovie = movieBean.createMovieFromAPI(newMovieAPI);
		ApiMovieDetails movieDetails = updateMovieWithDetailsFromAPI(newMovie);
		movieBean.addMovie(newMovie);
		newMovie.setId(movieBean.findMovieByIdTmdb(newMovie.getIdTmdb()).getId());
		addMovieGenres(movieDetails, newMovie);
		return newMovie;
	}
	
	public void addNewOnTheAirShows(ApiNewShow newShowAPI) {
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
		ArrayList<ApiGenres> apiGenres = movieDetails.getGenresAPI();
		for (ApiGenres genreAPI : apiGenres) {
			if (genreBean.findGenreByName(genreAPI.getName()) == null) {
				Genre genre = new Genre(genreAPI.getName());
				genreBean.addGenre(genre);
			}
			Genre genre = genreBean.findGenreByName(genreAPI.getName());
			MovieGenre movieGenre = new MovieGenre(movie, genre);
			movieGenreBean.addMovieGenre(movieGenre);
		}
	}

	public void addShowGenres(ApiShowDetails showDetails) {
		ArrayList<ApiGenres> apiGenres = showDetails.getGenresAPI();

		for (ApiGenres genreAPI : apiGenres) {
			if (genreBean.findGenreByName(genreAPI.getName()) == null) {

				Genre genre = new Genre(genreAPI.getName());
				genreBean.addGenre(genre);
			}
			Show show = showBean.findShowByIdTmdb(showDetails.getId());
			Genre genre = genreBean.findGenreByName(genreAPI.getName());
			ShowGenre showGenre = new ShowGenre(show, genre);
			showGenreBean.addShowGenre(showGenre);
		}
	}

}
