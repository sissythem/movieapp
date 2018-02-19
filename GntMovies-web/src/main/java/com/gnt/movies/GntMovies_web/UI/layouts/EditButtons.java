package com.gnt.movies.GntMovies_web.UI.layouts;

import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class EditButtons extends HorizontalLayout {
	private UserFormLayout userFormLayout;
	private GridFormPanel gridFormPanel;
	
	public EditButtons(UserFormLayout userFormLayout) {
		this.userFormLayout=userFormLayout;
	}

	public EditButtons(UserFormLayout userFormLayout, GridFormPanel gridFormPanel) {
		this.userFormLayout=userFormLayout;
		this.gridFormPanel=gridFormPanel;
	}

	public UserFormLayout getUserFormLayout() {
		return userFormLayout;
	}

	public void setUserFormLayout(UserFormLayout userFormLayout) {
		this.userFormLayout = userFormLayout;
	}

	public GridFormPanel getGridFormPanel() {
		return gridFormPanel;
	}

	public void setGridFormPanel(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}

}
