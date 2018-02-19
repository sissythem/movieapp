package com.gnt.movies.GntMovies_web.UI.panels;

import com.gnt.movies.GntMovies_web.UI.grids.MovieGrid;
import com.gnt.movies.GntMovies_web.UI.grids.ShowGrid;
import com.gnt.movies.GntMovies_web.UI.grids.UserGrid;
import com.gnt.movies.GntMovies_web.UI.layouts.MovieFormLayout;
import com.gnt.movies.GntMovies_web.UI.layouts.ShowFormLayout;
import com.gnt.movies.GntMovies_web.UI.layouts.UserFormLayout;
import com.gnt.movies.GntMovies_web.UI.views.MenuView;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
public class GridFormPanel extends Panel {
	private HorizontalSplitPanel hsplit;
	private MenuView menuView;
	private UserGrid userGrid;
	private MovieGrid movieGrid;
	private ShowGrid showGrid;
	private UserFormLayout userFormLayout;
	private MovieFormLayout movieFormLayout;
	private ShowFormLayout showFormLayout;
	public GridFormPanel(MenuView menuView) {
		super();
		setSizeFull();
		this.menuView = menuView;
		this.userGrid = new UserGrid(this);
		this.movieGrid = new MovieGrid(this);
		this.showGrid = new ShowGrid(this);
		this.userFormLayout = new UserFormLayout(this);
		this.movieFormLayout = new MovieFormLayout(this);
		this.showFormLayout = new ShowFormLayout(this);
		hsplit = new HorizontalSplitPanel();
		hsplit.setSplitPosition(77, Sizeable.Unit.PERCENTAGE);
		hsplit.setLocked(true);
	}
	
	public void addUserComponents() {
		hsplit.setFirstComponent(userGrid);
		hsplit.setSecondComponent(userFormLayout);
		setContent(hsplit);
	}
	
	public void addMovieComponents() {
		hsplit.setFirstComponent(movieGrid);
		hsplit.setSecondComponent(movieFormLayout);
		setContent(hsplit);
	}
	
	public void addShowComponents() {
		hsplit.setFirstComponent(showGrid);
		hsplit.setSecondComponent(showFormLayout);
		setContent(hsplit);
	}
	
	public void addUserGrid() {
		hsplit.setFirstComponent(userGrid);
		setContent(hsplit);
	}
	
	public void addMovieGrid() {
		hsplit.setFirstComponent(movieGrid);
		setContent(hsplit);
	}
	
	public void addShowGrid() {
		hsplit.setFirstComponent(showGrid);
		setContent(hsplit);
	}
	
	public void removeSplitComponents() {
		hsplit.removeAllComponents();
	}

	public HorizontalSplitPanel getHsplit() {
		return hsplit;
	}

	public void setHsplit(HorizontalSplitPanel hsplit) {
		this.hsplit = hsplit;
	}

	public MenuView getMenuView() {
		return menuView;
	}

	public void setMenuView(MenuView menuView) {
		this.menuView = menuView;
	}

	public UserGrid getUserGrid() {
		return userGrid;
	}

	public void setUserGrid(UserGrid userGrid) {
		this.userGrid = userGrid;
	}

	public MovieGrid getMovieGrid() {
		return movieGrid;
	}

	public void setMovieGrid(MovieGrid movieGrid) {
		this.movieGrid = movieGrid;
	}

	public UserFormLayout getUserFormLayout() {
		return userFormLayout;
	}

	public void setUserFormLayout(UserFormLayout userFormLayout) {
		this.userFormLayout = userFormLayout;
	}

	public MovieFormLayout getMovieFormLayout() {
		return movieFormLayout;
	}

	public void setMovieFormLayout(MovieFormLayout movieFormLayout) {
		this.movieFormLayout = movieFormLayout;
	}

	public ShowGrid getShowGrid() {
		return showGrid;
	}

	public void setShowGrid(ShowGrid showGrid) {
		this.showGrid = showGrid;
	}

	public ShowFormLayout getShowFormLayout() {
		return showFormLayout;
	}

	public void setShowFormLayout(ShowFormLayout showFormLayout) {
		this.showFormLayout = showFormLayout;
	}
	
}
