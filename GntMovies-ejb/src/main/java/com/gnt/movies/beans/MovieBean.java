package com.gnt.movies.beans;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.MovieDao;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.MovieImage;
import com.gnt.movies.theMovieDB.ApiMovieDetails;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;
import com.google.gson.Gson;

@Stateless
@LocalBean
public class MovieBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(MovieBean.class);

	@PersistenceContext
	private EntityManager em;

	@Inject
	@JpaDao
	@Named("MovieDaoImpl")
	MovieDao movieDao;

	@EJB
	GenreBean genreBean;

	@EJB
	MovieImageBean movieImageBean;

	public MovieBean() {
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	private Movie createMovieFromAPI(ApiNewMovie upcomingMovie) {
		byte adult;
		if (upcomingMovie.isAdult())
			adult = 1;
		else
			adult = 0;
		return new Movie(adult, upcomingMovie.getId(), upcomingMovie.getReleaseDate(),
				upcomingMovie.getOriginalLanguage(), upcomingMovie.getOriginalTitle(), upcomingMovie.getOverview(),
				upcomingMovie.getTitle(), upcomingMovie.getVoteAverage(), upcomingMovie.getVoteCount(),
				upcomingMovie.getPoster_path());
	}

	private synchronized void updateMovieWithDetails(Movie movie, ApiMovieDetails movieDetails) {
		Gson gson = new Gson();
		movie.setBudget(movieDetails.getBudget());
		movie.setHomepage(movieDetails.getHomepage());
		movie.setProductionCountries(gson.toJson(movieDetails.getApiProductionCountries()));
		movie.setRevenue(movieDetails.getRevenue());
		movie.setRuntime(movieDetails.getRuntime());
		movie.setStatus(movieDetails.getStatus());
		movie.setTitle(movieDetails.getTitle());
		movie.setImdbId(movieDetails.getImdbId());
		if (movieDetails.getApiCredits() != null) {
			if (movieDetails.getApiCredits().getCast() != null) {
				movie.setCast(gson.toJson(movieDetails.getApiCredits().getCast()));
			}
			if (movieDetails.getApiCredits().getCrew() != null) {
				movie.setCrew(gson.toJson(movieDetails.getApiCredits().getCrew()));
			}
		}
		if (movieDetails.getApiImages() != null) {
			movieDetails.setAllImages(movieDetails.getApiImages());
			movieDetails.getAllImages().stream().forEach(apiImage -> {
				MovieImage movieImage = new MovieImage(movie, apiImage.getFilePath());
				movieImageBean.addMovieImage(movieImage);
				movie.addMovieImage(movieImage);
			});
		}
		if (movieDetails.getGenres() != null) {
			movieDetails.getGenres().stream().forEach(apiGenre -> {
				Genre genre = genreBean.findGenreByName(apiGenre.getName());
				movie.addGenre(genre);
			});
		}

	}

	private Movie addNewMovie(ApiNewMovie movieApi) {
		logger.info("addNewMovieWithGenres movie with tmdbId=" + movieApi.getId());
		Movie movie = createMovieFromAPI(movieApi);
		ApiMovieDetails movieDetails = ApiCalls.getMovieDetailsFromAPI(movie.getIdTmdb());
		updateMovieWithDetails(movie, movieDetails);
		addMovie(movie);
		return movie;
	}

	public synchronized Movie getMovie(ApiNewMovie apiNewMovie) {
		Movie movie = findMovieByIdTmdb(apiNewMovie.getId());
		if (movie == null)
			movie = addNewMovie(apiNewMovie);
		return movie;
	}

	public void addMovie(Movie movie) {
		logger.info("addMovie movie with tmdbId=" + movie.getIdTmdb());
		movieDao.createMovie(this, movie);
	}

	public void updateMovieInDataBase(Movie movie) {
		movieDao.updateMovie(this, movie);
	}

	public Movie findMovieByIdTmdb(Integer id) {
		return movieDao.findMovieByIdTmdb(this, id);
	}

	public Movie findMovieByTitle(String title) {
		return movieDao.findMovieByTitle(this, title);
	}

	public Movie findMovieById(int id) {
		return movieDao.findMovieById(this, id);
	}
}
