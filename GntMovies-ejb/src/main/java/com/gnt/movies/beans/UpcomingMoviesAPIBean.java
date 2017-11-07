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

    public Movie createMovieFromAPI(UpcomingMovieAPI upcomingMovie) 
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

    public UpcomingMovie createUpcomingMovieFromAPI(UpcomingMovieAPI upcomingMovie) {
    	return new UpcomingMovie(upcomingMovie.getId());
	}
}
