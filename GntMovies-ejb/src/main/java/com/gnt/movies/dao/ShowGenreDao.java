package com.gnt.movies.dao;

import java.util.ArrayList;

import com.gnt.movies.entities.ShowGenre;

public interface ShowGenreDao {
	void createShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre);
	void updateShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre);
	void deleteShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre);
	ShowGenre findShowGenreById(DataProviderHolder dataProviderHolder, Integer id);
	ArrayList<ShowGenre> findByShowId(DataProviderHolder dataProviderHolder, Integer showId);
	ArrayList<ShowGenre> findByGenreId(DataProviderHolder dataProviderHolder, Integer genreId);
}