package com.gnt.movies.dao;

import com.gnt.movies.entities.Genre;

public class GenreDaoImpl extends AbstractDao implements GenreDao {

	@Override
	public void createGenre(DataProviderHolder dataProviderHolder, Genre genre) {
		createEntity(dataProviderHolder.getEntityManager(), genre);
	}

	@Override
	public void updateGenre(DataProviderHolder dataProviderHolder, Genre genre) {
		updateEntity(dataProviderHolder.getEntityManager(), genre);
	}

	@Override
	public void deleteGenre(DataProviderHolder dataProviderHolder, Genre genre) {
		removeEntity(dataProviderHolder.getEntityManager(), genre);
	}

	@Override
	public Genre findGenreById(DataProviderHolder dataProviderHolder, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
