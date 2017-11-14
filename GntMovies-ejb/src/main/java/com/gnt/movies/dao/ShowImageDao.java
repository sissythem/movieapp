package com.gnt.movies.dao;

import java.util.List;

import com.gnt.movies.entities.ShowImage;

public interface ShowImageDao {
	
	void createShowImage(DataProviderHolder dataProviderHolder, ShowImage showImage);
	void updateShowImage(DataProviderHolder dataProviderHolder, ShowImage showImage);
	void deleteShowImage(DataProviderHolder dataProviderHolder, ShowImage showImage);
	ShowImage findShowImageById(DataProviderHolder dataProviderHolder, Integer id);
	List<ShowImage> findByShowId(DataProviderHolder dataProviderHolder, Integer showId);

}
