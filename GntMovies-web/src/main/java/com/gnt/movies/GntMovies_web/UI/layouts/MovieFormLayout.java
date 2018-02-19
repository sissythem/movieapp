package com.gnt.movies.GntMovies_web.UI.layouts;

import com.gnt.movies.GntMovies_web.UI.forms.MovieForm;
import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MovieFormLayout extends VerticalLayout {
	private GridFormPanel gridFormPanel;
	private MovieForm movieForm;
	private EditButtons buttons;
	
	public MovieFormLayout() {
		this.movieForm = new MovieForm(this);
		this.buttons = new EditButtons(this);
		initLayout();
	}
	
	public MovieFormLayout(GridFormPanel gridFormPanel) {
		this.gridFormPanel=gridFormPanel;
		this.movieForm = new MovieForm(this);
		this.buttons = new EditButtons(this, gridFormPanel);
		initLayout();
	}
	
	private void initLayout() {
 		setSizeFull();
		setMargin(false);
		addComponents(movieForm, buttons);
		setComponentAlignment(movieForm, Alignment.TOP_CENTER);
		setComponentAlignment(buttons, Alignment.BOTTOM_CENTER);
		setExpandRatio(movieForm, 10);
		setExpandRatio(buttons, 1);
		setMargin(new MarginInfo(false, false, true, false));
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
	public EditButtons getButtons() {
		return buttons;
	}
	public void setButtons(EditButtons buttons) {
		this.buttons = buttons;
	}

}
