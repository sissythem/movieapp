package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.gnt.movies.entities.ShowGenre;
import com.gnt.movies.entities.ShowReview;

public class ShowGenreDaoImpl extends AbstractDao implements ShowGenreDao {

	@Override
	public void createShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre) {
		createEntity(dataProviderHolder.getEntityManager(), showGenre);
	}

	@Override
	public void updateShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre) {
		updateEntity(dataProviderHolder.getEntityManager(), showGenre);
	}

	@Override
	public void deleteShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre) {
		removeEntity(dataProviderHolder.getEntityManager(), showGenre);
	}

	@Override
	public ShowGenre findShowGenreById(DataProviderHolder dataProviderHolder, Integer id) {
		ShowGenre showGenre = new ShowGenre();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("ShowGenre.findById");
		query.setParameter("id", id);
		showGenre = (ShowGenre)query.getSingleResult();
		return showGenre;
	}
	
	@Override
	public List<ShowGenre> findByShowId(DataProviderHolder dataProviderHolder, Integer showId) {
		List<ShowGenre> showGenres = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("ShowGenre.findByShowId");
		query.setParameter("showId", showId);
		showGenres = query.getResultList();
		return showGenres;
	}
	@Override
	public List<ShowGenre> findByGenreId(DataProviderHolder dataProviderHolder, Integer genreId) {
		List<ShowGenre> showGenres = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery("ShowGenre.findByGenreId");
		query.setParameter("genreId", genreId);
		showGenres = query.getResultList();
		return showGenres;
	}

}

