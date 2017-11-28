package com.gnt.movies.GntMovies_web;

import java.awt.Menu;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private Menu menu;

	public MainLayout(MyUI ui) {
		CssLayout viewContainer = new CssLayout();
		viewContainer.addStyleName("valo-content");
		viewContainer.setSizeFull();
		addComponent(new MovieGrid());
		setSizeFull();
	}
}
