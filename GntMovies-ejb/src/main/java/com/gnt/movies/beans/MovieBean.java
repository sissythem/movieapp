package com.gnt.movies.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.MovieDao;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.theMovieDB.MovieDetailsAPI;
import com.gnt.movies.theMovieDB.UpcomingNowPlayingMovieAPI;

@Stateless
@LocalBean
public class MovieBean implements DataProviderHolder{
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	@JpaDao
	@Named("MovieDaoImpl")
	MovieDao movieDao;
	
    public MovieBean() {
    	
    }

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	public Movie createMovieFromAPI(UpcomingNowPlayingMovieAPI upcomingMovie) 
    {
    	byte adult;
    	//TODO when true adult=1 or 0???
    	if(upcomingMovie.isAdult())
    		adult=1;
    	else
    		adult=0;
		return new Movie(adult, upcomingMovie.getId(), upcomingMovie.getReleaseDate(), upcomingMovie.getOriginalLanguage(), upcomingMovie.getOriginalTitle(), 
				upcomingMovie.getOverview(), upcomingMovie.getTitle(), upcomingMovie.getVoteAverage(), upcomingMovie.getVoteCount());
	}
	
	public void updateMovieWithDetails(Movie movie, MovieDetailsAPI movieDetails) {
		movie.setBudget(movieDetails.getBudget());
		movie.setHomepage(movieDetails.getHomepage());
		movie.setProductionCountries(movieDetails.getProductionCountriesAPI().toString());
		movie.setRevenue(movieDetails.getRevenue());
		movie.setRuntime(movieDetails.getRuntime());
		movie.setStatus(movieDetails.getStatus());
		movie.setTitle(movieDetails.getTitle());
		movie.setImdbId(movieDetails.getImdbId());
	}
	
	public void addMovie(Movie movie) {
		movieDao.createMovie(this, movie);
	}
	
	public void updateMovieInDataBase(Movie movie) {
		movieDao.updateMovie(this, movie);
	}
	
	public Movie findMovieByIdTmdb(Integer id) {
		return movieDao.findMovieByIdTmdb(this, id);
	}
}
