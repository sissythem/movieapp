package com.gnt.movies.beans;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.TransactionTimeout;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.theMovieDB.ApiNewMovie;
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.utilities.APIClient;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
@TransactionTimeout(value=1,unit=TimeUnit.HOURS)
public class SchedulerBean implements DataProviderHolder 
{
	private static final Logger logger = LoggerFactory.getLogger(SchedulerBean.class);
	
	@PersistenceContext
	private EntityManager em;

	@EJB
	private UpcomingMovieBean upcomingMovieBean;

	@EJB
	private NowPlayingMovieBean nowPlayingMovieBean;

	@EJB
	private OnTheAirShowBean onTheAirShowBean;

	@EJB
	private Air2dayShowBean air2dayShowBean;
	
	public SchedulerBean() {

	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	private static boolean flag = false;
	
	@Schedule(dayOfWeek = "*", hour = "*", minute = "*/1",persistent=false)
	public void update() {
		logger.info("Scheduler updating database!");
		APIClient.setTimer();
		getUpcomingMovies();
		getNowPlayingMovies();
		getOnTheAirShows();
		getAir2dayShows();
		APIClient.unsetTimer();
		logger.info("Scheduler finished updating database!");
	}
	
	private void getUpcomingMovies() {
		if(flag)
			return;
		flag=true;
		
		logger.info("Scheduler checking for upcomming movies");
		HashSet<ApiNewMovie> upcomingMoviesAPI = ApiCalls.getUpcomingMovies();
		upcomingMoviesAPI.stream().parallel().forEach(e->upcomingMovieBean.checkUpcomingMovie(e));
		upcomingMovieBean.removeOldNotUpMovies(upcomingMoviesAPI);
		logger.info("Done checking for upcomming movies");
		
		flag = false;
	}
	
	private void  getNowPlayingMovies() {
		if(flag)
			return;
		flag=true;
		logger.info("Scheduler checking for now playing movies");
		HashSet<ApiNewMovie> nowPlayingMoviesAPI = ApiCalls.getNowPlayingMovies();
		nowPlayingMoviesAPI.stream().parallel().forEach(e->nowPlayingMovieBean.checkNowPlayingMovie(e));
		nowPlayingMovieBean.removeOldNotNowPlayingMovies(nowPlayingMoviesAPI);
		logger.info("Done checking for now playing movies");
		
		flag = false;
	}
	
	private void getOnTheAirShows() {
		if(flag)
			return;
		flag=true;
		logger.info("Scheduler checking for on the air shows");
		HashSet<ApiNewShow> onTheAirShowsAPI = ApiCalls.getOnTheAirShows();
		onTheAirShowsAPI.stream().parallel().forEach(e->onTheAirShowBean.checkOnTheAirShow(e));
		onTheAirShowBean.removeOldNotOnTheAirShows(onTheAirShowsAPI);
		logger.info("Done checking for on the air shows");
		flag = false;
	}
	
	private void getAir2dayShows() {
		if(flag)
			return;
		flag=true;
		logger.info("Scheduler checking for air today shows");
		HashSet<ApiNewShow> air2dayShowsAPI = ApiCalls.getAir2dayShows();
		air2dayShowsAPI.stream().parallel().forEach(e->air2dayShowBean.checkAir2dayShow(e));
		air2dayShowBean.removeOldNotAir2dayShow(air2dayShowsAPI);
		logger.info("Done checking for air2day shows");
		flag = false;
	}
}
