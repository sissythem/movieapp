package com.gnt.movies.dao;

import java.util.HashSet;
import java.util.List;

import com.gnt.movies.entities.Air2dayShow;

public interface Air2dayShowDao {
	void createAir2dayShow(DataProviderHolder dataProviderHolder, Air2dayShow air2dayShow);
	void updateAir2dayShow(DataProviderHolder dataProviderHolder, Air2dayShow air2dayShow);
	void deleteAir2dayShow(DataProviderHolder dataProviderHolder, Air2dayShow air2dayShow);
	Air2dayShow findAir2dayShowById(DataProviderHolder dataProviderHolder, Integer id);
	Air2dayShow findByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
	Air2dayShow findByShowId(DataProviderHolder dataProviderHolder, Integer showId);
	List<Air2dayShow> findAll(DataProviderHolder dataProviderHolder);
	HashSet<Integer> getAllIdTmdb(DataProviderHolder dataProviderHolder);
	void deleteAir2dayShowByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
}
