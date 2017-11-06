package com.gnt.movies.dao;

import com.gnt.movies.entities.Show;

public interface ShowDao {
	void createShow(DataProviderHolder dataProviderHolder, Show show);
	void updateShow(DataProviderHolder dataProviderHolder, Show show);
	void deleteShow(DataProviderHolder dataProviderHolder, Show show);
	Show findShowById(DataProviderHolder dataProviderHolder, Integer id);
	Show findShowByName(DataProviderHolder dataProviderHolder, String name);
	Show findShowByIdTmdb(DataProviderHolder dataProviderHolder, Integer idTmdb);
}
