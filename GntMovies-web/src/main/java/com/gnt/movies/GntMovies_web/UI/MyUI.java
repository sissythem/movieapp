package com.gnt.movies.GntMovies_web.UI;

import javax.inject.Inject;

import com.gnt.movies.beans.MovieBean;
import com.gnt.movies.beans.NowPlayingMovieBean;
import com.gnt.movies.beans.UpcomingMovieBean;
import com.gnt.movies.beans.UserBean;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@CDIUI("")
public class MyUI extends UI {
	private static final long serialVersionUID = 1L;

	@Inject private UserBean userBean;
	@Inject private MovieBean movieBean;
	@Inject private UpcomingMovieBean upcomingMovieBean;
	@Inject private NowPlayingMovieBean nowPlayingMovieBean;
	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
        
        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("Gnt-Movies");
        
        
        setContent(new MainLayout(this));
    }

	public static MyUI get(){
		return (MyUI)UI.getCurrent();
	}	
	
	public UserBean getUserBean() {
		return userBean;
	}

	public MovieBean getMovieBean() {
		return movieBean;
	}

	public UpcomingMovieBean getUpcomingMovieBean() {
		return upcomingMovieBean;
	}

	public NowPlayingMovieBean getNowPlayingMovieBean() {
		return nowPlayingMovieBean;
	}

}
