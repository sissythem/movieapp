package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.ShowFavorite;

public interface ShowFavoriteDao {
	void createShowFavorite(DataProviderHolder dataProviderHolder, ShowFavorite showFavorite);
	void updateShowFavorite(DataProviderHolder dataProviderHolder, ShowFavorite showFavorite);
	void deleteShowFavorite(DataProviderHolder dataProviderHolder, ShowFavorite showFavorite);
	ShowFavorite findShowFavoriteById(DataProviderHolder dataProviderHolder, Integer id);
	List<Object> findByUserId(DataProviderHolder dataProviderHolder, Integer userId);
	List<Object> findByShowId(DataProviderHolder dataProviderHolder, Integer showId);
}

