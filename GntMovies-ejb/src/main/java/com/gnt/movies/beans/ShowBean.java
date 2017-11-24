package com.gnt.movies.beans;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.ShowDao;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.entities.Show;
import com.gnt.movies.theMovieDB.ApiShowDetails;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;
import com.google.gson.Gson;

@Stateless
@LocalBean
public class ShowBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(ShowBean.class);

	@PersistenceContext
	private EntityManager em;

	@Inject
	@JpaDao
	@Named("ShowDaoImpl")
	ShowDao showDao;

	@EJB
	GenreBean genreBean;

	@EJB
	ImageBean imageBean;

	public ShowBean() {
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	private void updateShowWithDetails(Show show, ApiShowDetails showDetails) {
		logger.info("updateShowWithDetails tmdbId=" + show.getIdTmdb());
		Gson gson = new Gson();
		show.setCreatedBy(gson.toJson(showDetails.getCreatedBy()));
		show.setHomepage(showDetails.getHomepage());
		show.setInProduction(showDetails.isInProduction());
		
		show.setLastAirDate(showDetails.getLastAirDate());
		
		show.setNetworks(gson.toJson(showDetails.getNetworks()));
		show.setNumOfEpisodes(showDetails.getEpisodesNum());
		show.setNumOfSeasons(showDetails.getEpisodesNum());
		show.setRuntime(showDetails.getRuntime());
		show.setProductionCountries(gson.toJson(showDetails.getProductionCountriesAPI()));
		show.setStatus(showDetails.getStatus());
		show.setType(showDetails.getType());

		if (showDetails.getCast() != null) {
			show.setCast(gson.toJson(showDetails.getCast()));
		}
		if (showDetails.getCrew() != null) {
			show.setCrew(gson.toJson(showDetails.getCrew()));
		}
		if (showDetails.getApiImages() != null) {
			showDetails.setAllImages(showDetails.getApiImages());
			showDetails.getAllImages().stream().forEach(image -> 
				show.addImage(image)
			);
		}
		if (showDetails.getGenres() != null) {
			showDetails.getGenres().stream().forEach(apiGenre -> {
				Genre genre = genreBean.findGenreByName(apiGenre.getName());
				show.addGenre(genre);
			});
		}

	}

	

	public Show getShow(Show show) {
		Show showFromDb = findShowByIdTmdb(show.getIdTmdb());
		if (showFromDb == null)
			showFromDb = addNewShow(show);
		return showFromDb;
	}
	
	public Show addNewShow(Show show) {
		logger.info("addNewShowWithGenres show with tmdbId=" + show.getIdTmdb());
		ApiShowDetails showDetails = ApiCalls.getShowDetailsFromAPI(show.getIdTmdb());
		updateShowWithDetails(show, showDetails);
		
		show.getImages().stream().forEach(image -> imageBean.addImage(image));
		insertShowToDb(show);
		return show;
	}
	public void insertShowToDb(Show show) {
		logger.info("addShow show with tmdbId=" + show.getIdTmdb());
		showDao.createShow(this, show);
	}

	public Show findShowByIdTmdb(int idTmdb) {
		return showDao.findShowByIdTmdb(this, idTmdb);
	}

	public Show findShowByName(String name) {
		return showDao.findShowByName(this, name);
	}

	public Show findShowById(int id) {
		return showDao.findShowById(this, id);
	}
}
