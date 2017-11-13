package com.gnt.movies.beans;

import java.util.ArrayList;
import java.util.HashSet;

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
	
	static HashSet<String> genres;
	
    public GenreBean() {
        
    }
    
    public Genre findGenreByName(String name) {
    	return genreDao.findGenreByName(this, name);
    }

    public void addGenre(Genre genre) {
    	logger.info("addGenre genre with name="+genre.getName());
    	genreDao.createGenre(this, genre);
    }
    
    public ArrayList<Genre> getAllGenres(){
    	return genreDao.findAllGenres(this);
    }

	public HashSet<String> getAllGenreNames() {
		
		return genreDao.findAllGenreNames(this);
	}
	
	

	public void updateGenres(ArrayList<ApiGenre> genresApi) {
		genres = getAllGenreNames();

		for (ApiGenre apiGenre : genresApi) {
			if (!genreExists(apiGenre)) {
				addGenre(apiGenre);
			}
		}
	}

	private boolean genreExists(ApiGenre apiGenre) {
		if (!genres.contains(apiGenre.getName()))
			return false;
		else
			return true;
	}

	private void addGenre(ApiGenre genreAPI) {
		Genre genre = new Genre(genreAPI.getName());
		addGenre(genre);
		genres.add(genreAPI.getName());
	}
}
