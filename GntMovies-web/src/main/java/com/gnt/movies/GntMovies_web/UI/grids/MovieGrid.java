package com.gnt.movies.GntMovies_web.UI.grids;

import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.gnt.movies.entities.Movie;
import com.vaadin.ui.Grid;

@SuppressWarnings("serial")
public class MovieGrid extends Grid<Movie> {
	private GridFormPanel gridFormPanel;
	
	public MovieGrid(GridFormPanel gridFormPanel) {
		this.gridFormPanel=gridFormPanel;
	}
	public GridFormPanel getGridFormPanel() {
		return gridFormPanel;
	}
	public void setGridFormPanel(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}

}
