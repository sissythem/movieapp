package com.gnt.movies.dao;

import javax.persistence.EntityManager;

public interface DataProviderHolder {
	EntityManager getEntityManager();
}
