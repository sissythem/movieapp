package com.gnt.movies.dao;

import java.util.ArrayList;
import java.util.HashSet;

import com.gnt.movies.entities.Genre;

public interface GenreDao {
	void createGenre(DataProviderHolder dataProviderHolder, Genre genre);
	void updateGenre(DataProviderHolder dataProviderHolder, Genre genre);
	void deleteGenre(DataProviderHolder dataProviderHolder, Genre genre);
	Genre findGenreById(DataProviderHolder dataProviderHolder, Integer id);
	Genre findGenreByName(DataProviderHolder dataProviderHolder, String name);
	ArrayList<Genre>findAllGenres(DataProviderHolder dataProviderHolder);
	HashSet<String>findAllGenreNames(DataProviderHolder dataProviderHolder);
}
