package com.gnt.movies.GntMovies_web.UI.layouts;

import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ShowFormLayout extends VerticalLayout {
	private GridFormPanel gridFormPanel;
	
	public ShowFormLayout(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}
	
	public GridFormPanel getGridFormPanel() {
		return gridFormPanel;
	}
	
	public void setGridFormPanel(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}

}
