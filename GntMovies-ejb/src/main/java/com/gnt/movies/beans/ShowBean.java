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
import com.gnt.movies.theMovieDB.NewShowsAPI;
import com.gnt.movies.theMovieDB.ShowDetailsAPI;

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
    
    public Show createShowFromAPI(NewShowsAPI newShowsAPI) {
    	
    	return new Show(LocalDate.parse(newShowsAPI.getFirstAirDate()), newShowsAPI.getId(), newShowsAPI.getName(), newShowsAPI.getOriginalLanguage(),
    			newShowsAPI.getOriginalName(), newShowsAPI.getOriginCountry().toString(), newShowsAPI.getOverview(), newShowsAPI.getVoteAverage(),
    			newShowsAPI.getVoteCount());
    }

    public void updateShowWithDetails(Show show, ShowDetailsAPI showDetails) {
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
}
