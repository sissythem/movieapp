package com.gnt.movies.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.GenreDao;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.entities.Genre;
import com.gnt.movies.theMovieDB.ApiGenre;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.LoggerFactory;

/**
 * Session Bean implementation class GenreBean
 */
@Stateless
@LocalBean
public class GenreBean implements DataProviderHolder {
	private static final Logger logger = LoggerFactory.getLogger(GenreBean.class);
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("GenreDaoImpl")
	GenreDao genreDao;
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	static ConcurrentHashMap<String,Genre> genres = new ConcurrentHashMap<>();

    public GenreBean() {
        
    }
    
    public Genre findGenreByName(String name) {
    	return genreDao.findGenreByName(this, name);
    }

    public synchronized void addGenre(Genre genre) {
    	logger.info("addGenre genre with name="+genre.getName());
    	genreDao.createGenre(this, genre);
    }
    
    public List<?> getAllGenres(){
    	return genreDao.findAllGenres(this);
    }
	
	public synchronized void updateGenres(ArrayList<ApiGenre> genresApi) {
		Genre g;
		for (Object o : getAllGenres()) {
			g= (Genre)o;
			genres.put(g.getName(),g);
		}
		
		for (ApiGenre apiGenre : genresApi) {
			if (!genreExists(apiGenre)) {
				Genre genre = addGenre(apiGenre);
				genres.put(genre.getName(),genre);
			}
		}
	}

	private synchronized boolean genreExists(ApiGenre apiGenre) {
		if (!genres.contains(apiGenre.getName()))
			return false;
		else
			return true;
	}

	//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private synchronized Genre addGenre(ApiGenre genreAPI) {
		Genre genre = new Genre(genreAPI.getName());
		addGenre(genre);
		return genre;
	}

	public Genre getGenre(String name) {
		return genres.get(name);
	}
	
	public synchronized void editGenre(Genre genre) {
		genreDao.updateGenre(this, genre);
	}
}
