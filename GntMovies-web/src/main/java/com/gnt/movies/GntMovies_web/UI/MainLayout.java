package com.gnt.movies.GntMovies_web.UI;

import com.gnt.movies.GntMovies_web.classes.ApplicationLayout;
import com.gnt.movies.GntMovies_web.classes.MovieGrid;
import com.gnt.movies.dto.MovieListItemDto;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends VerticalLayout {

	private static final long serialVersionUID = 1L;
//	private Menu menu;

	public MainLayout(MyUI ui) {
		
		CssLayout viewContainer = new CssLayout();
		viewContainer.addStyleName("valo-content");
		viewContainer.setSizeFull();
		createApplicationLayout();
		addMovieGrid();
		
	}
	
	private void createApplicationLayout() {
		ApplicationLayout appLayout = new ApplicationLayout();
		addComponent(appLayout);
	}
	
	private void addMovieGrid() {
		MovieGrid moviesGrid = new MovieGrid();
		MovieGrid upMoviesGrid = new MovieGrid();
		MovieGrid nPMoviesGrid = new MovieGrid();
		ListDataProvider<MovieListItemDto> movieProvider = DataProvider
				.ofCollection(MyUI.get().getMovieBean().getMovies());
		ListDataProvider<MovieListItemDto> upMovieProvider = DataProvider
				.ofCollection(MyUI.get().getUpcomingMovieBean().getMovies());
		ListDataProvider<MovieListItemDto> npMovieProvider = DataProvider
				.ofCollection(MyUI.get().getNowPlayingMovieBean().getMovies());
		moviesGrid.setDataProvider(movieProvider);
		upMoviesGrid.setDataProvider(upMovieProvider);
		nPMoviesGrid.setDataProvider(npMovieProvider);

		Label l1 = new Label("All Movies " + movieProvider.getItems().size());
		Label l2 = new Label("Upcoming Movies " + upMovieProvider.getItems().size());
		Label l3 = new Label("Now Playing Movies " + npMovieProvider.getItems().size());
		addComponent(l1);
		addComponent(moviesGrid);
		addComponent(l2);
		addComponent(upMoviesGrid);
		addComponent(l3);
		addComponent(nPMoviesGrid);
		// setSizeFull();
	}
}
