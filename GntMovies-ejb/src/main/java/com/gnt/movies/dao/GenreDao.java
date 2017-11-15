package com.gnt.movies.dao;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.gnt.movies.entities.Genre;

public interface GenreDao {
	void createGenre(DataProviderHolder dataProviderHolder, Genre genre);
	void updateGenre(DataProviderHolder dataProviderHolder, Genre genre);
	void deleteGenre(DataProviderHolder dataProviderHolder, Genre genre);
	Genre findGenreById(DataProviderHolder dataProviderHolder, Integer id);
	Genre findGenreByName(DataProviderHolder dataProviderHolder, String name);
	List<?> findAllGenres(DataProviderHolder dataProviderHolder);
	ConcurrentHashMap<Integer,String>findAllGenreNames(DataProviderHolder dataProviderHolder);
}
