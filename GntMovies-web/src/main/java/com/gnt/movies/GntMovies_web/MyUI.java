package com.gnt.movies.GntMovies_web;

import javax.inject.Inject;

import com.gnt.movies.beans.SchedulerBean;
import com.gnt.movies.beans.UserBean;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
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
	@Inject private SchedulerBean schedulerBean;
	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
        MainLayout layout = new MainLayout();
        setContent(layout);
    }

	public static MyUI get(){
		return (MyUI)UI.getCurrent();
	}	
	
	public UserBean getUserBean() {
		return userBean;
	}
	public SchedulerBean getSchedulerBean() {
		return schedulerBean;
	}

//    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
//    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
//    public static class MyUIServlet extends VaadinServlet {
//		private static final long serialVersionUID = 1L;
//    }
}
