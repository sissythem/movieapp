package com.gnt.movies.beans;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.ejb3.annotation.TransactionTimeout;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.Show;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.ApiClient;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

@Stateless
@LocalBean
@TransactionTimeout(value = 1, unit = TimeUnit.HOURS)
public class SchedulerBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(SchedulerBean.class);

	@PersistenceContext
	private EntityManager em;

	@EJB
	private GenreBean genreBean;

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

	@Schedule(dayOfWeek = "*", hour = "*", minute = "*/1", persistent = false)
	@TransactionAttribute(TransactionAttributeType.NEVER)
	private void update() {
		if (flag)
			return;
		flag = true;
		logger.info("Scheduler updating database!");
		ApiClient.setTimer();
		getGenres();
		getUpcomingMovies();
		// getNowPlayingMovies();
		// getOnTheAirShows();
		getAir2dayShows();
		ApiClient.unsetTimer();
		logger.info("Scheduler finished updating database!");
		flag = false;
	}

	private void getGenres() {
		HashSet<Genre> genres = ApiCalls.getGenres();
		genreBean.addGenres(genres);
	}

	@PostConstruct
	private void init() {
		ApiClient.init();
		UpcomingMovieBean.init();
		NowPlayingMovieBean.init();
		Air2dayShowBean.init();
		OnTheAirShowBean.init();
	}

	private void getUpcomingMovies() {
		// if(flag)
		// return;
		// flag=true;
		upcomingMovieBean.findAllIdTmdb();
		logger.info("Scheduler checking for upcomming movies");
		HashSet<Movie> upcomingMoviesAPI = ApiCalls.getUpcomingMovies();

		ExecutorService executor = Executors.newFixedThreadPool(40);
		for (Movie movie : upcomingMoviesAPI) {

			Runnable worker = () -> {
				upcomingMovieBean.checkUpcomingMovie(movie);
			};
			executor.execute(worker);
		}

		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e1) {
			logger.info("GetUpcomingMovies: Executor interrupted ");
			e1.printStackTrace();
		}
		System.out.println("Finished all Executors threads");

		upcomingMovieBean.removeOldNotUpMovies(upcomingMoviesAPI);
		logger.info("Done checking for upcomming movies");

		// flag = false;
	}

	private void getNowPlayingMovies() {
		// if(flag)
		// return;
		// flag=true;
		nowPlayingMovieBean.findAllIdTmdb();
		logger.info("Scheduler checking for now playing movies");
		HashSet<Movie> nowPlayingMoviesAPI = ApiCalls.getNowPlayingMovies();
//		nowPlayingMoviesAPI.stream().parallel().forEach(e -> nowPlayingMovieBean.checkNowPlayingMovie(e));
		ExecutorService executor = Executors.newFixedThreadPool(40);
		for (Movie movie : nowPlayingMoviesAPI) {

			Runnable worker = () -> {
				nowPlayingMovieBean.checkNowPlayingMovie(movie);
			};
			executor.execute(worker);
		}

		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e1) {
			logger.info("GetUpcomingMovies: Executor interrupted ");
			e1.printStackTrace();
		}
		System.out.println("Finished all Executors threads");
		nowPlayingMovieBean.removeOldNotNowPlayingMovies(nowPlayingMoviesAPI);
		logger.info("Done checking for now playing movies");

		// flag = false;
	}

	private void getOnTheAirShows() {
		// if(flag)
		// return;
		// flag=true;
		onTheAirShowBean.findAllIdTmdb();
		logger.info("Scheduler checking for on the air shows");
		HashSet<Show> onTheAirShows = ApiCalls.getOnTheAirShows();
		onTheAirShows.stream().parallel().forEach(e -> onTheAirShowBean.checkOnTheAirShow(e));
		ExecutorService executor = Executors.newFixedThreadPool(40);
		for (Show show : onTheAirShows) {

			Runnable worker = () -> {
				onTheAirShowBean.checkOnTheAirShow(show);
			};
			executor.execute(worker);
		}

		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e1) {
			logger.info("GetUpcomingMovies: Executor interrupted ");
			e1.printStackTrace();
		}
		System.out.println("Finished all Executors threads");

		onTheAirShowBean.removeOldNotOnTheAirShows(onTheAirShows);
		logger.info("Done checking for on the air shows");
		// flag = false;
	}

	private void getAir2dayShows() {
		// if(flag)
		// return;
		// flag=true;
		air2dayShowBean.findAllIdTmdb();
		logger.info("Scheduler checking for air today shows");
		HashSet<Show> air2dayShowsAPI = ApiCalls.getAir2dayShows();
//		air2dayShowsAPI.stream().parallel().forEach(e -> air2dayShowBean.checkAir2dayShow(e));
		ExecutorService executor = Executors.newFixedThreadPool(40);
		for (Show show : air2dayShowsAPI) {

			Runnable worker = () -> {
				air2dayShowBean.checkAir2dayShow(show);
			};
			executor.execute(worker);
		}

		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e1) {
			logger.info("GetUpcomingMovies: Executor interrupted ");
			e1.printStackTrace();
		}
		System.out.println("Finished all Executors threads");

		air2dayShowBean.removeOldNotAir2dayShow(air2dayShowsAPI);
		logger.info("Done checking for air2day shows");
		// flag = false;
	}
}
