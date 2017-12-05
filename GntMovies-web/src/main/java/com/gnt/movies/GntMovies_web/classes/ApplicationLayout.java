package com.gnt.movies.GntMovies_web.classes;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class ApplicationLayout extends HorizontalLayout {
	private AppMenuBar menuBar;
	private Button userButton;
	
	public ApplicationLayout() {
		menuBar = new AppMenuBar();
		userButton = new Button("User");
		
		addComponents(menuBar, userButton);
		setComponentAlignment(menuBar, Alignment.MIDDLE_LEFT);
		setComponentAlignment(userButton, Alignment.MIDDLE_RIGHT);
	}

	public AppMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(AppMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public Button getUserButton() {
		return userButton;
	}

	public void setUserButton(Button userButton) {
		this.userButton = userButton;
	}
}
