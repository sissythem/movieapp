package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import com.gnt.movies.entities.Genre;

public interface GenreDao {
	void createGenre(DataProviderHolder dataProviderHolder, Genre genre);
	void updateGenre(DataProviderHolder dataProviderHolder, Genre genre);
	void deleteGenre(DataProviderHolder dataProviderHolder, Genre genre);
	Genre findGenreById(DataProviderHolder dataProviderHolder, Integer id);
	Genre findGenreByName(DataProviderHolder dataProviderHolder, String name);
	ArrayList<Genre> findAllGenres(DataProviderHolder dataProviderHolder);
	ConcurrentHashMap<Integer,String>findAllGenreNames(DataProviderHolder dataProviderHolder);
}
