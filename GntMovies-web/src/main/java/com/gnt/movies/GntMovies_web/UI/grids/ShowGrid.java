package com.gnt.movies.GntMovies_web.UI.grids;

import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.gnt.movies.entities.Show;
import com.vaadin.ui.Grid;

@SuppressWarnings("serial")
public class ShowGrid extends Grid<Show> {
	private GridFormPanel gridFormPanel;
	
	public ShowGrid(GridFormPanel gridFormPanel) {
		this.gridFormPanel=gridFormPanel;
	}
	public GridFormPanel getGridFormPanel() {
		return gridFormPanel;
	}
	public void setGridFormPanel(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}

}
