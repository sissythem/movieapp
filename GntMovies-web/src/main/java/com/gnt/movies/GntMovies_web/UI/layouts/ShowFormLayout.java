package com.gnt.movies.GntMovies_web.UI.layouts;

import com.gnt.movies.GntMovies_web.UI.forms.ShowForm;
import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ShowFormLayout extends VerticalLayout {
	private GridFormPanel gridFormPanel;
	private ShowForm showForm;
	private EditButtons buttons;
	
	public ShowFormLayout() {
		this.showForm = new ShowForm(this);
		this.buttons = new EditButtons(this);
		initLayout();
	}
	
	public ShowFormLayout(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
		this.showForm = new ShowForm(this);
		this.buttons = new EditButtons(this, gridFormPanel);
		initLayout();
	}
	
	private void initLayout() {
		setSizeFull();
		setMargin(false);
		addComponents(showForm, buttons);
		setComponentAlignment(showForm, Alignment.TOP_CENTER);
		setComponentAlignment(buttons, Alignment.BOTTOM_CENTER);
		setExpandRatio(showForm, 10);
		setExpandRatio(buttons, 1);
		setMargin(new MarginInfo(false, false, true, false));
	}

	public GridFormPanel getGridFormPanel() {
		return gridFormPanel;
	}
	
	public void setGridFormPanel(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}

	public ShowForm getShowForm() {
		return showForm;
	}

	public void setShowForm(ShowForm showForm) {
		this.showForm = showForm;
	}

	public EditButtons getButtons() {
		return buttons;
	}

	public void setButtons(EditButtons buttons) {
		this.buttons = buttons;
	}

}
