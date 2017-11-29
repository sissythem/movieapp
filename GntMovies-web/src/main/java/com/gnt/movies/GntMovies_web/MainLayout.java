package com.gnt.movies.GntMovies_web;

import java.awt.Menu;

import com.gnt.movies.entities.Movie;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private Menu menu;

	public MainLayout(MyUI ui) {
		CssLayout viewContainer = new CssLayout();
		viewContainer.addStyleName("valo-content");
		viewContainer.setSizeFull();
		MovieGrid moviesGrid = new MovieGrid();
		MovieGrid UpMoviesGrid = new MovieGrid();
		MovieGrid NPMoviesGrid = new MovieGrid();
		ListDataProvider<Movie> dataProvider = DataProvider.ofCollection(MyUI.get().getMovieBean().getMovies());
		moviesGrid.setDataProvider(dataProvider);
		addComponent(moviesGrid);
		addComponent(UpMoviesGrid);
//		setSizeFull();
	}
}
