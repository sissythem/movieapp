package com.gnt.movies.GntMovies_web.UI.layouts;

import com.gnt.movies.GntMovies_web.UI.forms.MovieForm;
import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MovieFormLayout extends VerticalLayout {
	private GridFormPanel gridFormPanel;
	private MovieForm movieForm;
	
	public MovieFormLayout(GridFormPanel gridFormPanel) {
		this.gridFormPanel=gridFormPanel;
		movieForm = new MovieForm(this);
	}
	public GridFormPanel getGridFormPanel() {
		return gridFormPanel;
	}
	public void setGridFormPanel(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}
	public MovieForm getMovieForm() {
		return movieForm;
	}
	public void setMovieForm(MovieForm movieForm) {
		this.movieForm = movieForm;
	}

}
