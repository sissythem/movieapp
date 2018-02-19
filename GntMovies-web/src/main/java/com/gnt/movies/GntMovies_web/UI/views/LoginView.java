package com.gnt.movies.GntMovies_web.UI.views;

import com.gnt.movies.GntMovies_web.UI.panels.LoginPanel;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.GntMovies_web.UI.views.MainView;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LoginView extends VerticalLayout implements View {
	private LoginPanel loginPanel;
	private MainView mainView;
	
	public LoginView(MainView mainView) {
		this.mainView=mainView;
		loginPanel = new LoginPanel(this);
		setSizeFull();
		addStyleName(Utilities.SCSS_LOGIN_IMAGE);
		addComponent(loginPanel);
		setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
	}
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
}
