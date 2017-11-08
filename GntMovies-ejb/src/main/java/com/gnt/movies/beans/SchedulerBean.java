package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.entities.Air2dayShow;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.MovieGenre;
import com.gnt.movies.entities.NowPlayingMovie;
import com.gnt.movies.entities.Show;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.GenresAPI;
import com.gnt.movies.theMovieDB.MovieDetailsAPI;
import com.gnt.movies.theMovieDB.NewShowsAPI;
import com.gnt.movies.theMovieDB.ShowDetailsAPI;
import com.gnt.movies.theMovieDB.UpcomingNowPlayingMovieAPI;
import com.gnt.movies.utilities.APIClient;


@Stateless
@LocalBean
public class SchedulerBean implements DataProviderHolder {
	
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
    
    //TODO set timer
    
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
    			}
    			else {
    				Movie movie = movieBean.findMovieByIdTmdb(upcomingMovieAPI.getId());
    				movieDetails = APIClient.getMovieDetailsFromAPI(movie.getIdTmdb());
    				newNowPlayingMovie.setMovie(movie);
    				nowPlayingMovieBean.addNowPlayingMovie(newNowPlayingMovie);
    			}
    			addMovieGenres(movieDetails);
    		}
    	}
    }
    
    public void checkAir2dayShowsToBeStored() {
    	
    	ArrayList<NewShowsAPI> newShowsAPI = APIClient.getAir2dayShowsFromAPI();
    	
    	for(NewShowsAPI newShowAPI : newShowsAPI) {
    		if(air2dayShowBean.findAir2dayShowByIdTmdb(newShowAPI.getId()) == null) {
    			Air2dayShow air2dayShow = air2dayShowBean.createAir2dayShowFromAPI(newShowAPI.getId());
    			if(showBean.findShowByIdTmdb(newShowAPI.getId()) == null) {
    				Show newShow = showBean.createShowFromAPI(newShowAPI);
    				updateShowWithDetailsFromAPI(newShow);
    				showBean.addShow(newShow);
    				air2dayShowBean.addAir2dayShow(air2dayShow);
    			}
    			else {
    				Show show = showBean.findShowByIdTmdb(newShowAPI.getId());
    				air2dayShow.setShow(show);
    				air2dayShowBean.addAir2dayShow(air2dayShow);
    			}
    		}
    	}
    }
    
    public MovieDetailsAPI updateMovieWithDetailsFromAPI(Movie movie) {
    	
    	MovieDetailsAPI movieDetails = APIClient.getMovieDetailsFromAPI(movie.getIdTmdb());
    	movieBean.updateMovieWithDetails(movie, movieDetails);
    	return movieDetails;
    }
    
    public void updateShowWithDetailsFromAPI(Show show) {
    	ShowDetailsAPI showDetails = APIClient.getShowDetailsFromAPI(show.getIdTmdb());
    	showBean.updateShowWithDetails(show, showDetails);
    }
    
    public void addMovieGenres(MovieDetailsAPI movieDetails) {
    	ArrayList<GenresAPI> genresAPI = movieDetails.getGenresAPI();
    	for(GenresAPI genreAPI : genresAPI) {
    		if(genreBean.findGenreByName(genreAPI.getName()) == null) {
    			Genre genre = new Genre(genreAPI.getName());
    			genreBean.addGenre(genre);
    		}
    		Movie movie = movieBean.findMovieByIdTmdb(movieDetails.getId());
    		Genre genre = genreBean.findGenreByName(genreAPI.getName());
    		MovieGenre movieGenre = new MovieGenre(movie, genre);
    	}
    }
}
