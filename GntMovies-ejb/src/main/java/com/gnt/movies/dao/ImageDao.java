package com.gnt.movies.dao;

import com.gnt.movies.entities.Image;

public interface ImageDao {
	void createImage(DataProviderHolder dataProviderHolder, Image image);
	void updateImage(DataProviderHolder dataProviderHolder, Image image);
	void deleteImage(DataProviderHolder dataProviderHolder, Image image);
	Image findImageById(DataProviderHolder dataProviderHolder, Integer id);
}
