package com.gnt.movies.dao;

import com.gnt.movies.entities.Air2dayShow;

public interface Air2dayShowDao {
	void createAir2dayShow(DataProviderHolder dataProviderHolder, Air2dayShow air2dayShow);
	void updateAir2dayShow(DataProviderHolder dataProviderHolder, Air2dayShow air2dayShow);
	void deleteAir2dayShow(DataProviderHolder dataProviderHolder, Air2dayShow air2dayShow);
	Air2dayShow findAir2dayShowById(DataProviderHolder dataProviderHolder, Integer id);
}
