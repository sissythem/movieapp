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
import com.gnt.movies.entities.Image;
import com.gnt.movies.entities.Show;
import com.gnt.movies.utilities.ApiCalls;
import com.gnt.movies.utilities.JsonUtils;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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

	private void updateShowWithDetails(Show show, String showDetailsJson) {
		logger.info("updateShowWithDetails show id:" + show.getIdTmdb() + " " + showDetailsJson);
		Gson gson = new Gson();
		JsonObject jo = JsonUtils.getJsonObjectFromString(showDetailsJson);

		show.setCreatedBy(JsonUtils.getJsonArrayFromJson("created_by", jo));
		show.setHomepage(JsonUtils.getStringFromJson("homepage", jo));
		show.setInProduction(JsonUtils.getBooleanFromJson("in_production", jo));
		show.setNetworks(JsonUtils.getJsonArrayFromJson("networks", jo));
		logger.info("updateShowWithDetails show id:" + show.getIdTmdb() + " "
				+ JsonUtils.getIntegerFromJson("number_of_episodes", jo));
		show.setNumOfEpisodes(JsonUtils.getIntegerFromJson("number_of_episodes", jo));
		show.setNumOfSeasons(JsonUtils.getIntegerFromJson("number_of_seasons", jo));
		show.setRuntime(JsonUtils.getJsonArrayFromJson("episode_run_time", jo));
		show.setStatus(JsonUtils.getStringFromJson("status", jo));
		show.setType(JsonUtils.getStringFromJson("type", jo));
		JsonElement credits = jo.get("credits");
		show.setCast(JsonUtils.getJsonArrayFromJson("cast", credits.getAsJsonObject()));
		show.setCrew(JsonUtils.getJsonArrayFromJson("crew", credits.getAsJsonObject()));
		String date = JsonUtils.getStringFromJson("last_air_date", jo);
		if (date.length() > 9)
			show.setLastAirDate(LocalDate.parse(date));

		JsonElement images = jo.get("images");

		for (Image image : gson.fromJson(JsonUtils.getJsonArrayFromJson("backdrops", images.getAsJsonObject()),
				Image[].class)) {
			show.addImage(image);
		}
		for (Image image : gson.fromJson(JsonUtils.getJsonArrayFromJson("posters", images.getAsJsonObject()),
				Image[].class)) {
			show.addImage(image);
		}
		for (Genre genre : gson.fromJson(JsonUtils.getJsonArrayFromJson("genres", jo), Genre[].class)) {
			Genre g = genreBean.findGenreByName(genre.getName());
			show.addGenre(g);
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
		String showDetailsJson = ApiCalls.getShowDetailsFromAPI(show.getIdTmdb());
		updateShowWithDetails(show, showDetailsJson);

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
