package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.Query;

import com.gnt.movies.entities.ShowGenre;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("ShowGenreDaoImpl")
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
		return (ShowGenre)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.SHOW_GENRE_FIND_BY_ID, "id", id);
	}
	
	@Override
	public List<ShowGenre> findByShowId(DataProviderHolder dataProviderHolder, Integer showId) {
		List<ShowGenre> showGenres = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_GENRE_FIND_BY_SHOW_ID);
		query.setParameter("showId", showId);
		showGenres = query.getResultList();
		return showGenres;
	}
	
	@Override
	public List<ShowGenre> findByGenreId(DataProviderHolder dataProviderHolder, Integer genreId) {
		List<ShowGenre> showGenres = new ArrayList<>();
		Query query = dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_GENRE_FIND_BY_GENRE_ID);
		query.setParameter("genreId", genreId);
		showGenres = query.getResultList();
		return showGenres;
	}
}

