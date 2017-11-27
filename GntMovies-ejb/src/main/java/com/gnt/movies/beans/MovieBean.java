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
import com.gnt.movies.entities.Image;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.JsonUtils;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
	ImageBean imageBean;

	public MovieBean() {
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	private synchronized void updateMovieWithDetails(Movie movie, String movieDetails) {
		logger.info("updateMovieWithDetails tmdbId=" + movie.getIdTmdb());
		Gson gson = new Gson();
		JsonObject jo = JsonUtils.getJsonObjectFromString(movieDetails);
		movie.setBudget(JsonUtils.getDoubleFromJson("budget", jo));
		movie.setHomepage(JsonUtils.getStringFromJson("homepage", jo));
		movie.setProductionCountries(JsonUtils.getJsonArrayFromJson("production_countries", jo));
		movie.setRevenue(JsonUtils.getIntegerFromJson("revenue", jo));
		movie.setRuntime(JsonUtils.getIntegerFromJson("runtime", jo));
		movie.setStatus(JsonUtils.getStringFromJson("status", jo));
		movie.setTitle(JsonUtils.getStringFromJson("title", jo));
		movie.setImdbId(JsonUtils.getStringFromJson("imdb_id", jo));
		movie.setAdult(JsonUtils.getBooleanFromJson("adult", jo));
		JsonElement credits = jo.get("credits");
		movie.setCast(JsonUtils.getJsonArrayFromJson("cast", credits.getAsJsonObject()));
		movie.setCrew(JsonUtils.getJsonArrayFromJson("crew", credits.getAsJsonObject()));
		JsonElement images = jo.get("images");
		

		for (Image image : gson.fromJson(JsonUtils.getJsonArrayFromJson("backdrops", images.getAsJsonObject()),
				Image[].class)) {
			movie.addImage(image);
		}
		for (Image image : gson.fromJson(JsonUtils.getJsonArrayFromJson("posters", images.getAsJsonObject()),
				Image[].class)) {
			movie.addImage(image);
		}
		for (Genre genre : gson.fromJson(JsonUtils.getJsonArrayFromJson("genres", jo), Genre[].class)) {
			Genre g = genreBean.findGenreByName(genre.getName());
			movie.addGenre(g);
		}
	}
//	private synchronized void updateMovieWithDetails(Movie movie, ApiMovieDetails movieDetails) {
//		logger.info("updateMovieWithDetails tmdbId=" + movie.getIdTmdb());
//		Gson gson = new Gson();
//		movie.setBudget(movieDetails.getBudget());
//		movie.setHomepage(movieDetails.getHomepage());
//		movie.setProductionCountries(gson.toJson(movieDetails.getApiProductionCountries()));
//		movie.setRevenue(movieDetails.getRevenue());
//		movie.setRuntime(movieDetails.getRuntime());
//		movie.setStatus(movieDetails.getStatus());
//		movie.setTitle(movieDetails.getTitle());
//		movie.setImdbId(movieDetails.getImdbId());
//		movie.setAdult(movieDetails.isAdult());
//		movie.setAdult(true);
//		
//		if (movieDetails.getApiCredits() != null) {
//			if (movieDetails.getApiCredits().getCast() != null) {
//				movie.setCast(gson.toJson(movieDetails.getApiCredits().getCast()));
//			}
//			if (movieDetails.getApiCredits().getCrew() != null) {
//				movie.setCrew(gson.toJson(movieDetails.getApiCredits().getCrew()));
//			}
//		}
//		if (movieDetails.getApiImages() != null) {
//			movieDetails.setAllImages(movieDetails.getApiImages());
//			movieDetails.getAllImages().stream().forEach(image ->movie.addImage(image));
//		}
//		if (movieDetails.getGenres() != null) {
//			movieDetails.getGenres().stream().forEach(apiGenre -> {
//				Genre genre = genreBean.findGenreByName(apiGenre.getName());
//				movie.addGenre(genre);
//			});
//		}
//		
//	}

	public synchronized Movie getMovie(Movie movie) {
		logger.info("getMovie movie with tmdbId=" + movie.getIdTmdb());
		Movie movieFromDb = findMovieByIdTmdb(movie.getIdTmdb());
		if (movieFromDb == null)
			movieFromDb = addNewMovie(movie);
		return movieFromDb;
	}

	private Movie addNewMovie(Movie movie) {
		logger.info("addNewMovieWithGenres movie with tmdbId=" + movie.getIdTmdb());
		String movieDetailsJson = ApiCalls.getMovieDetailsFromAPI(movie.getIdTmdb());
		updateMovieWithDetails(movie, movieDetailsJson);
		
		movie.getImages().stream().forEach(image -> imageBean.addImage(image));
		insertMovieToDb(movie);
		return movie;
	}

	private void insertMovieToDb(Movie movie) {
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
