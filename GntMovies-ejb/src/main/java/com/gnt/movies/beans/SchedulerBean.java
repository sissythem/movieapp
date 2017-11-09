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

		for (ApiNewMovie upcomingMovieAPI : upcomingMoviesAPI) {
			if (upcomingMovieBean.findMovieByIdTmdb(upcomingMovieAPI.getId()) == null) {

				UpcomingMovie newUpcomingMovie = upcomingMovieBean.createUpcomingMovieFromAPI(upcomingMovieAPI);
				Movie newMovie = movieBean.createMovieFromAPI(upcomingMovieAPI);

				ApiMovieDetails movieDetails = updateMovieWithDetailsFromAPI(newMovie);
				newUpcomingMovie.setMovie(newMovie);
				upcomingMovieBean.addUpcomingMovie(newMovie, newUpcomingMovie);
				addMovieGenres(movieDetails);
			}
		}
	}

	@Schedule(dayOfWeek = "*", hour = "0")
	public void checkNowPlayingMovies() {

		ArrayList<ApiNewMovie> nowPlayingMoviesAPI = APIClient.getNowPlayingMoviesFromAPI();

		for (ApiNewMovie upcomingMovieAPI : nowPlayingMoviesAPI) {
			if (nowPlayingMovieBean.findMovieByIdTmdb(upcomingMovieAPI.getId()) == null) {

				ApiMovieDetails movieDetails = new ApiMovieDetails();
				NowPlayingMovie newNowPlayingMovie = nowPlayingMovieBean.createNowPlayingMovieFromAPI(upcomingMovieAPI);

				if (movieBean.findMovieByIdTmdb(upcomingMovieAPI.getId()) == null) {

					Movie newMovie = movieBean.createMovieFromAPI(upcomingMovieAPI);
					movieDetails = updateMovieWithDetailsFromAPI(newMovie);

					movieBean.addMovie(newMovie);
					nowPlayingMovieBean.addNowPlayingMovie(newNowPlayingMovie);
				} else {
					Movie movie = movieBean.findMovieByIdTmdb(upcomingMovieAPI.getId());
					movieDetails = APIClient.getMovieDetailsFromAPI(movie.getIdTmdb());
					newNowPlayingMovie.setMovie(movie);
					nowPlayingMovieBean.addNowPlayingMovie(newNowPlayingMovie);
				}
				addMovieGenres(movieDetails);
			}
		}
	}

	@Schedule(dayOfWeek = "*", hour = "0")
	public void checkOnTheAirShow() {

		ArrayList<ApiNewShow> onTheAirShowsAPI = APIClient.getOnTheAirShowsFromAPI();

		for (ApiNewShow onTheAirShowAPI : onTheAirShowsAPI) {
			if (onTheAirShowBean.findOnTheAirShowByIdTmdb(onTheAirShowAPI.getId()) == null) {

				ApiShowDetails showDetails = new ApiShowDetails();
				OnTheAirShow onTheAirShow = onTheAirShowBean.createOnTheAirShowFromAPI(onTheAirShowAPI.getId());

				if (showBean.findShowByIdTmdb(onTheAirShowAPI.getId()) == null) {

					Show newShow = showBean.createShowFromAPI(onTheAirShowAPI);
					showDetails = updateShowWithDetailsFromAPI(newShow);
					showBean.addShow(newShow);
					onTheAirShowBean.addOnTheAirShow(onTheAirShow);

				} else {
					Show show = showBean.findShowByIdTmdb(onTheAirShowAPI.getId());
					showDetails = APIClient.getShowDetailsFromAPI(show.getIdTmdb());
					onTheAirShow.setShow(show);
					onTheAirShowBean.addOnTheAirShow(onTheAirShow);
				}
				addShowGenres(showDetails);
			}
		}
	}

	@Schedule(dayOfWeek = "*", hour = "0")
	public void checkAir2dayShows() {

		ArrayList<ApiNewShow> apiNewShow = APIClient.getAir2dayShowsFromAPI();

		for (ApiNewShow newShowAPI : apiNewShow) {
			if (air2dayShowBean.findAir2dayShowByIdTmdb(newShowAPI.getId()) == null) {

				ApiShowDetails showDetails = new ApiShowDetails();
				Air2dayShow air2dayShow = air2dayShowBean.createAir2dayShowFromAPI(newShowAPI.getId());

				if (showBean.findShowByIdTmdb(newShowAPI.getId()) == null) {

					Show newShow = showBean.createShowFromAPI(newShowAPI);
					showDetails = updateShowWithDetailsFromAPI(newShow);

					showBean.addShow(newShow);
					air2dayShowBean.addAir2DayShow(air2dayShow);

				} else {
					Show show = showBean.findShowByIdTmdb(newShowAPI.getId());
					showDetails = APIClient.getShowDetailsFromAPI(show.getIdTmdb());
					air2dayShow.setShow(show);
					air2dayShowBean.addAir2DayShow(air2dayShow);
				}
				addShowGenres(showDetails);
			}
		}
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

	public void addMovieGenres(ApiMovieDetails movieDetails) {
		ArrayList<ApiGenres> apiGenres = movieDetails.getGenresAPI();
		for (ApiGenres genreAPI : apiGenres) {
			if (genreBean.findGenreByName(genreAPI.getName()) == null) {
				Genre genre = new Genre(genreAPI.getName());
				genreBean.addGenre(genre);
			}
			Movie movie = movieBean.findMovieByIdTmdb(movieDetails.getId());
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
