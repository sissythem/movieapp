package com.gnt.movies.beans;

import java.time.LocalDate;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gnt.movies.dao.DataProviderHolder;
import com.gnt.movies.dao.JpaDao;
import com.gnt.movies.dao.ShowDao;
import com.gnt.movies.entities.Show;
import com.gnt.movies.theMovieDB.ApiNewShow;
import com.gnt.movies.theMovieDB.ApiShowDetails;

/**
 * Session Bean implementation class ShowBean
 */
@Stateless
@LocalBean
public class ShowBean implements DataProviderHolder{

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	@JpaDao
	@Named("ShowDaoImpl")
	ShowDao showDao;
	
    public ShowBean() {
    }
    
    @Override
	public EntityManager getEntityManager() {
		return em;
	}
    
    public Show createShowFromAPI(ApiNewShow apiNewShow) {
    	
    	return new Show(LocalDate.parse(apiNewShow.getFirstAirDate()), apiNewShow.getId(), apiNewShow.getName(), apiNewShow.getOriginalLanguage(),
    			apiNewShow.getOriginalName(), apiNewShow.getOriginCountry().toString(), apiNewShow.getOverview(), apiNewShow.getVoteAverage(),
    			apiNewShow.getVoteCount());
    }

    public void updateShowWithDetails(Show show, ApiShowDetails showDetails) {
    	show.setCreatedBy(showDetails.getCreatedBy().toString());
    	show.setHomepage(showDetails.getHomepage());
    	
    	byte inProduction;
    	if(showDetails.isInProduction()) inProduction=1;
    	else inProduction=0;
    	show.setInProduction(inProduction);
    	
    	show.setLastAirDate(LocalDate.parse(showDetails.getLastAirDate()));
    	show.setNetworks(showDetails.getNetworks().toString());
    	show.setNumOfEpisodes(showDetails.getEpisodesNum());
    	show.setNumOfSeasons(showDetails.getEpisodesNum());
    	
    	show.setProductionCountries(showDetails.getProductionCountriesAPI().toString());
    	show.setStatus(showDetails.getStatus());
    	show.setType(showDetails.getType());
	}
    
    public void addShow(Show show) {
    	showDao.createShow(this, show);
    }
    
    public Show findShowByIdTmdb(int idTmdb) {
    	return showDao.findShowByIdTmdb(this, idTmdb);
    }
    
    public Show findShowByName(String name) {
    	return showDao.findShowByName(this, name);
    }
    
    public Show findShowById(int id) {
    	return showDao.findShowById(this, id);
    }
}
