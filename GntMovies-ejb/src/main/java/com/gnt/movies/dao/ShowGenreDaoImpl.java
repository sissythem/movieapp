package com.gnt.movies.dao;

import java.util.List;

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
		return (ShowGenre)getSingleResult(dataProviderHolder.getEntityManager(), Utils.SHOW_GENRE_FIND_BY_ID, id);
	}
	
	@Override
	public List<Object> findByShowId(DataProviderHolder dataProviderHolder, Integer showId) {
		return (List<Object>)findListEntities(dataProviderHolder, "showId", showId.toString(), Utils.SHOW_GENRE_FIND_BY_SHOW_ID);
	}
	
	@Override
	public List<Object> findByGenreId(DataProviderHolder dataProviderHolder, Integer genreId) {
		return (List<Object>)findListEntities(dataProviderHolder, "genreId", genreId.toString(), Utils.SHOW_GENRE_FIND_BY_GENRE_ID);
	}

}

