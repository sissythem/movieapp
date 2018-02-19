package com.gnt.movies.GntMovies_web.UI.panels;

import com.gnt.movies.GntMovies_web.UI.forms.LoginForm;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.GntMovies_web.UI.views.LoginView;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LoginPanel extends Panel {
	private HorizontalSplitPanel hsplit;
	private LoginForm loginForm;
	private LoginView loginView;
	
	private static final ThemeResource LOGO_ICON = new ThemeResource(Utilities.LOGO_ICON_PATH);
	
	public LoginPanel(LoginView loginView) {
		super("Welcome");
		this.loginView=loginView;
		setWidth(650, Unit.PIXELS);
		setHeight(400, Unit.PIXELS);
		createHorizontalSplit();
		this.loginForm = new LoginForm(this);
		VerticalLayout logo = createLeftComponent();
		hsplit.setFirstComponent(logo);
		hsplit.setSecondComponent(loginForm);
		hsplit.setLocked(true);
		setContent(hsplit);
	}

	private void createHorizontalSplit() {
		hsplit = new HorizontalSplitPanel();
		hsplit.setSplitPosition(50, Sizeable.Unit.PERCENTAGE);
	}
	
	private VerticalLayout createLeftComponent() {
		VerticalLayout vl = new VerticalLayout();
		Image icon = new Image();
		icon.setIcon(LOGO_ICON);
		Label name = new Label(Utilities.APP_LOGO);
		name.setStyleName(Utilities.SCSS_LOGIN_FORM_TEXT);
		vl.addComponents(icon, name);
		vl.setComponentAlignment(icon, Alignment.MIDDLE_CENTER);
		vl.setComponentAlignment(name, Alignment.MIDDLE_CENTER);
		//TODO the below does not work. Background is not transparent
		//vl.setStyleName("leftpanel");
		return vl;
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}
}
