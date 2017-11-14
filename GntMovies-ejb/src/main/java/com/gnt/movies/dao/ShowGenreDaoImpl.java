package com.gnt.movies.dao;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

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
	public ArrayList<ShowGenre> findByShowId(DataProviderHolder dataProviderHolder, Integer showId) {
		return (ArrayList<ShowGenre>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_GENRE_FIND_BY_SHOW_ID)
				.setParameter("showId", showId).getResultList();
	}
	
	@Override
	public ArrayList<ShowGenre> findByGenreId(DataProviderHolder dataProviderHolder, Integer genreId) {
		return (ArrayList<ShowGenre>) dataProviderHolder.getEntityManager().createNamedQuery(Utils.SHOW_GENRE_FIND_BY_GENRE_ID)
				.setParameter("genreId", genreId).getResultList();
	}
}

