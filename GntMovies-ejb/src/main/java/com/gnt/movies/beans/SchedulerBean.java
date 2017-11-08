package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.AbstractDao;
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
import com.gnt.movies.theMovieDB.GenresAPI;
import com.gnt.movies.theMovieDB.MovieDetailsAPI;
import com.gnt.movies.theMovieDB.NewShowsAPI;
import com.gnt.movies.theMovieDB.ShowDetailsAPI;
import com.gnt.movies.theMovieDB.UpcomingNowPlayingMovieAPI;
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

	@Schedule(dayOfWeek = "*", hour = "0")
	public void checkUpcomingMoviesToBeStored() {

		ArrayList<UpcomingNowPlayingMovieAPI> upcomingMoviesAPI = APIClient.getUpcomingMoviesFromAPI();

		for (UpcomingNowPlayingMovieAPI upcomingMovieAPI : upcomingMoviesAPI) {
			if (upcomingMovieBean.findMovieByIdTmdb(upcomingMovieAPI.getId()) == null) {

				UpcomingMovie newUpcomingMovie = upcomingMovieBean.createUpcomingMovieFromAPI(upcomingMovieAPI);
				Movie newMovie = movieBean.createMovieFromAPI(upcomingMovieAPI);

				MovieDetailsAPI movieDetails = updateMovieWithDetailsFromAPI(newMovie);
				upcomingMovieBean.addUpcomingMovie(newMovie, newUpcomingMovie);
				addMovieGenres(movieDetails);
			}
		}
	}

	@Schedule(dayOfWeek = "*", hour = "0")
	public void checkNowPlayingMoviesToBeStored() {

		ArrayList<UpcomingNowPlayingMovieAPI> nowPlayingMoviesAPI = APIClient.getNowPlayingMoviesFromAPI();

		for (UpcomingNowPlayingMovieAPI upcomingMovieAPI : nowPlayingMoviesAPI) {
			if (nowPlayingMovieBean.findMovieByIdTmdb(upcomingMovieAPI.getId()) == null) {

				MovieDetailsAPI movieDetails = new MovieDetailsAPI();
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
	public void checkOnTheAirShowToBeStored() {

		ArrayList<NewShowsAPI> onTheAirShowsAPI = APIClient.getOnTheAirShowsFromAPI();

		for (NewShowsAPI onTheAirShowAPI : onTheAirShowsAPI) {
			if (onTheAirShowBean.findOnTheAirShowByIdTmdb(onTheAirShowAPI.getId()) == null) {

				ShowDetailsAPI showDetails = new ShowDetailsAPI();
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
	public void checkAir2dayShowsToBeStored() {

		ArrayList<NewShowsAPI> newShowsAPI = APIClient.getAir2dayShowsFromAPI();

		for (NewShowsAPI newShowAPI : newShowsAPI) {
			if (air2dayShowBean.findAir2dayShowByIdTmdb(newShowAPI.getId()) == null) {

				ShowDetailsAPI showDetails = new ShowDetailsAPI();
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

	public MovieDetailsAPI updateMovieWithDetailsFromAPI(Movie movie) {

		MovieDetailsAPI movieDetails = APIClient.getMovieDetailsFromAPI(movie.getIdTmdb());
		movieBean.updateMovieWithDetails(movie, movieDetails);
		return movieDetails;
	}

	public ShowDetailsAPI updateShowWithDetailsFromAPI(Show show) {
		ShowDetailsAPI showDetails = APIClient.getShowDetailsFromAPI(show.getIdTmdb());
		showBean.updateShowWithDetails(show, showDetails);
		return showDetails;
	}

	public void addMovieGenres(MovieDetailsAPI movieDetails) {
		ArrayList<GenresAPI> genresAPI = movieDetails.getGenresAPI();
		for (GenresAPI genreAPI : genresAPI) {
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

	public void addShowGenres(ShowDetailsAPI showDetails) {
		ArrayList<GenresAPI> genresAPI = showDetails.getGenresAPI();

		for (GenresAPI genreAPI : genresAPI) {
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
