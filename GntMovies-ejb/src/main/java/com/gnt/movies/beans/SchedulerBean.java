package com.gnt.movies.beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.theMovieDB.ApiNewMovie;
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
	private UpcomingMovieBean upcomingMovieBean;

	@EJB
	private NowPlayingMovieBean nowPlayingMovieBean;

	@EJB
	private OnTheAirShowBean onTheAirShowBean;

	@EJB
	private Air2dayShowBean air2dayShowBean;

	APIClient apiClient = new APIClient();
	
	public SchedulerBean() {

	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	private static boolean flag = false;
	
	@Schedule(dayOfWeek = "*", hour = "*", minute = "*/1",second="*",persistent=false)
	public void update() {
		getUpcomingMovies();
		getNowPlayingMovies();
	}
	int i = 1;
	public void getUpcomingMovies() {
		if(flag)
			return;
		flag=true;
		
		logger.info("Scheduler checking for upcomming movies");
		upcomingMovieBean.findAllIdTmdb();
		ArrayList<ApiNewMovie> upcomingMoviesAPI = apiClient.getUpcomingMoviesFromAPI();
		
		for (ApiNewMovie upcomingMovieAPI : upcomingMoviesAPI) {
			if(i==10) {
				break;
			}
			i++;
			upcomingMovieBean.checkUpcomingMovie(upcomingMovieAPI);
		}
		upcomingMovieBean.removeOldNotUpMovies(upcomingMoviesAPI);
		logger.info("Done checking for upcomming movies");
		
		flag = false;
	}
	
	@Schedule(dayOfWeek = "*", hour = "*", minute = "*/1",second="*",persistent=false)
	public void  getNowPlayingMovies() {
		if(flag)
			return;
		flag=true;
		logger.info("Scheduler checking for now playing movies");
		nowPlayingMovieBean.findAllIdTmdb();
		ArrayList<ApiNewMovie> nowPlayingMoviesAPI = apiClient.getNowPlayingMoviesFromAPI();
		i=1;
		for(ApiNewMovie newMovieApi : nowPlayingMoviesAPI) {
			if(i==10) {
				break;
			}
			i++;
			nowPlayingMovieBean.checkNowPlayingMovie(newMovieApi);
		}
		nowPlayingMovieBean.removeOldNotNowPlayingMovies(nowPlayingMoviesAPI);
		flag = false;
	}
	/*
	@Schedule(dayOfWeek = "*", hour = "0")
	public void checkAir2dayShows() {
	 	ArrayList<ApiNewShow> apiNewShow = APIClient.getAir2dayShowsFromAPI();

		for (ApiNewShow newShowAPI : apiNewShow) {
 			if (air2dayShowBean.findAir2dayShowByIdTmdb(newShowAPI.getId()) == null) {
 				addNewAir2dayShows(newShowAPI);
	 		}
		}
	 }

	public ApiShowDetails updateShowWithDetailsFromAPI(Show show) {
		ApiShowDetails showDetails =
	 	APIClient.getShowDetailsFromAPI(show.getIdTmdb());
		showBean.updateShowWithDetails(show, showDetails);
		return showDetails;
 	}

	public void addNewOnTheAirShows(ApiNewShow newShowAPI) {
		OnTheAirShow newOnTheAirShow = onTheAirShowBean.createOnTheAirShowFromAPI(newShowAPI.getId());
		Show show;
		if (showBean.findShowByIdTmdb(newShowAPI.getId()) == null) {

			show = addNewShowWithGenres(newShowAPI);

		} else {
			show = showBean.findShowByIdTmdb(newShowAPI.getId());
		}
		newOnTheAirShow.setShow(show);
		onTheAirShowBean.addOnTheAirShow(newOnTheAirShow);
	}

	public void addNewAir2dayShows(ApiNewShow newShowAPI) {
		Air2dayShow newAir2dayShow = air2dayShowBean.createAir2dayShowFromAPI(newShowAPI.getId());
		Show show;
		if (showBean.findShowByIdTmdb(newShowAPI.getId()) == null) {
			show = addNewShowWithGenres(newShowAPI);

		} else {
			show = showBean.findShowByIdTmdb(newShowAPI.getId());
		}
		newAir2dayShow.setShow(show);
		air2dayShowBean.addAir2DayShow(newAir2dayShow);
	}

	public Show addNewShowWithGenres(ApiNewShow newShowAPI) {
		Show newShow = showBean.createShowFromAPI(newShowAPI);
		ApiShowDetails showDetails = updateShowWithDetailsFromAPI(newShow);
		addShowGenres(showDetails);
		showBean.addShow(newShow);
		newShow.setId(showBean.findShowByIdTmdb(newShow.getIdTmdb()).getId());
		return newShow;
	}

	 public void addShowGenres(ApiShowDetails showDetails) {
	 ArrayList<ApiGenre> apiGenres = showDetails.getGenresAPI();
	
	 for (ApiGenre genreAPI : apiGenres) {
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
*/
}
