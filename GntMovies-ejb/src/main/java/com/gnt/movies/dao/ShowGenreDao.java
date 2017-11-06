package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.ShowGenre;

public interface ShowGenreDao {
	void createShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre);
	void updateShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre);
	void deleteShowGenre(DataProviderHolder dataProviderHolder, ShowGenre showGenre);
	ShowGenre findShowGenreById(DataProviderHolder dataProviderHolder, Integer id);
	List<ShowGenre> findByShowId(DataProviderHolder dataProviderHolder, Integer showId);
	List<ShowGenre> findByGenreId(DataProviderHolder dataProviderHolder, Integer genreId);
}