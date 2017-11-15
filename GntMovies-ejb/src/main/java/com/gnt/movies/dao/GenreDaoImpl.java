package com.gnt.movies.dao;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.gnt.movies.entities.Genre;
import com.gnt.movies.utilities.Utils;

@JpaDao
@Dependent
@Named("GenreDaoImpl")
public class GenreDaoImpl extends AbstractDao implements GenreDao {

	@Override
	public synchronized void createGenre(DataProviderHolder dataProviderHolder, Genre genre) {
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
		return (Genre)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.GENRE_FIND_BY_ID, "id", id);
	}

	@Override
	public Genre findGenreByName(DataProviderHolder dataProviderHolder, String name) {
		return (Genre)findSingleEntity(dataProviderHolder.getEntityManager(), Utils.GENRE_FIND_BY_NAME, "name", name);
	}

	@Override
	public List<?> findAllGenres(DataProviderHolder dataProviderHolder) {
		return  dataProviderHolder.getEntityManager().createNamedQuery(Utils.GENRE_FIND_ALL).getResultList();
	}
	@Override
	public ConcurrentHashMap<Integer,String> findAllGenreNames(DataProviderHolder dataProviderHolder) {
		ConcurrentHashMap<Integer,String> map = new ConcurrentHashMap<>();
		Genre g;
		for (Object obj : dataProviderHolder.getEntityManager().createNamedQuery(Utils.GENRE_FIND_ALL_NAMES).getResultList()) {
			g = (Genre)obj;
			map.put(g.getId(),g.getName());
		}
		return map;
	}

}
