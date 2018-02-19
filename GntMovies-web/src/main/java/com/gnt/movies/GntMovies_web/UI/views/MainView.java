package com.gnt.movies.GntMovies_web.UI.views;

import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MainView extends VerticalLayout {

	private LoginView loginView;
	private HomeView homeView;
	
	public MainView() {
		setSizeFull();
		setMargin(false);
		loginView = new LoginView(this);
		homeView = new HomeView(this);
		addComponent(loginView);
		loginView.setSizeFull();
	}
	
	public void checkForComponent() {
		removeAllComponents();
		if(getSession().getAttribute(Utilities.SESSION_USER)==null) {
			addComponent(loginView);
			loginView.setSizeFull();
		} else {
			addComponent(homeView);
			homeView.setSizeFull();
		}
	}
	
	public LoginView getLoginView() {
		return loginView;
	}
	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}
	public HomeView getHomeView() {
		return homeView;
	}
	public void setHomeView(HomeView homeView) {
		this.homeView = homeView;
	}
	
}
