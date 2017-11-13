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
import com.gnt.movies.entities.MovieGenre;
import com.gnt.movies.theMovieDB.ApiGenre;
import com.gnt.movies.theMovieDB.ApiMovieDetails;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.utilities.APIClient;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;
import com.google.gson.Gson;

@Stateless
@LocalBean
public class MovieBean implements DataProviderHolder{
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
	MovieGenreBean movieGenreBean;
	
	APIClient apiClient = new APIClient();
	
    public MovieBean() {
    	
    }

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	public Movie createMovieFromAPI(ApiNewMovie upcomingMovie) 
    {
    	byte adult;
    	if(upcomingMovie.isAdult()) adult=1;
    	else adult=0;
		return new Movie(adult, upcomingMovie.getId(), upcomingMovie.getReleaseDate(), upcomingMovie.getOriginalLanguage(), upcomingMovie.getOriginalTitle(), 
				upcomingMovie.getOverview(), upcomingMovie.getTitle(), upcomingMovie.getVoteAverage(), upcomingMovie.getVoteCount());
	}
	
	public void updateMovieWithDetails(Movie movie, ApiMovieDetails movieDetails) {
		movie.setBudget(movieDetails.getBudget());
		movie.setHomepage(movieDetails.getHomepage());
		movie.setProductionCountries(movieDetails.getProductionCountriesAPI().toString());
		movie.setRevenue(movieDetails.getRevenue());
		movie.setRuntime(movieDetails.getRuntime());
		movie.setStatus(movieDetails.getStatus());
		movie.setTitle(movieDetails.getTitle());
		movie.setImdbId(movieDetails.getImdbId());
		Gson gson = new Gson();
		movie.setCast(gson.toJson(movieDetails.getCast()));
		movie.setCrew(gson.toJson(movieDetails.getCrew()));
		
		
		
		for (ApiGenre apiGenre : movieDetails.getGenresAPI()) {
			Genre genre = genreBean.findGenreByName(apiGenre.getName());
			MovieGenre movieGenre = new MovieGenre(movie,genre);
			movie.addMovieGenre(movieGenre);
		}
		
		
	}
	
	public Movie addNewMovie(ApiNewMovie movieApi) {
		logger.info("addNewMovieWithGenres movie with tmdbId=" + movieApi.getId());
		Movie movie = createMovieFromAPI(movieApi);

		ApiMovieDetails movieDetails = apiClient.getMovieDetailsFromAPI(movie.getIdTmdb());
		genreBean.updateGenres(movieDetails.getGenresAPI());

		updateMovieWithDetails(movie, movieDetails);
		addMovie(movie);
		for (MovieGenre movieGenre : movie.getMovieGenres()) {
			movieGenreBean.addMovieGenre(movieGenre);
		}
		return movie;
	}
	
	public void addMovie(Movie movie) {
		logger.info("addMovie movie with tmdbId="+movie.getIdTmdb());
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
