package com.gnt.movies.beans;

import java.time.LocalDate;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.UpcomingMovie;
import com.gnt.movies.theMovieDB.UpcomingMovieAPI;

/**
 * Session Bean implementation class UpcomingMoviesAPIBean
 */
@Stateless
@LocalBean
public class UpcomingMoviesAPIBean {

    public UpcomingMoviesAPIBean() {
        
    }

    public Movie createMovieFromAPI(UpcomingMovieAPI upcomingMovie) {
		return new Movie(upcomingMovie.isAdult(), upcomingMovie.getId(), upcomingMovie.getReleaseDate(), upcomingMovie.getOriginalLanguage(), 
				upcomingMovie.getOriginalTitle(), upcomingMovie.getOverview(), upcomingMovie.getTitle(), upcomingMovie.getVoteAverage(), upcomingMovie.getVoteCount());
	}

    public UpcomingMovie createUpcomingMovieFromAPI(UpcomingMovieAPI upcomingMovie) {
    	return new UpcomingMovie(upcomingMovie.getId());
	}
}
