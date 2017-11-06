package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.ShowGenre;

public interface ShowGenreDao {
	void createShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre);
	void updateShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre);
	void deleteShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre);
	ShowGenre findShowGenreById(DataProviderHolder dataProviderHolder, Integer id);
	List<Object> findByShowId(DataProviderHolder dataProviderHolder, Integer showId);
	List<Object> findByGenreId(DataProviderHolder dataProviderHolder, Integer genreId);
}