package com.gnt.movies.dao;

import java.util.ArrayList;

import com.gnt.movies.entities.ShowFavorite;

public interface ShowFavoriteDao {
	void createShowFavorite(DataProviderHolder dataProviderHolder, ShowFavorite showFavorite);
	void updateShowFavorite(DataProviderHolder dataProviderHolder, ShowFavorite showFavorite);
	void deleteShowFavorite(DataProviderHolder dataProviderHolder, ShowFavorite showFavorite);
	ShowFavorite findShowFavoriteById(DataProviderHolder dataProviderHolder, Integer id);
	ArrayList<ShowFavorite> findShowFavoriteByUserId(DataProviderHolder dataProviderHolder, Integer userId);
	ArrayList<ShowFavorite> findShowFavoriteByShowId(DataProviderHolder dataProviderHolder, Integer showId);
}

