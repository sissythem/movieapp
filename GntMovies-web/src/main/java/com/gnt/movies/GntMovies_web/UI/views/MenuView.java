package com.gnt.movies.GntMovies_web.UI.views;

import com.gnt.movies.GntMovies_web.UI.layouts.SearchMenu;
import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MenuView extends VerticalLayout{
	private HomeView homeView;
	private SearchMenu searchMenu;
	private GridFormPanel gridFormPanel;
	private MenuSelection itemSelected;
	
	public enum MenuSelection{
		USER, SHOW, MOVIE, NONE
	}
	
	public MenuView(HomeView homeView) {
		setSizeFull();
		setMargin(true);
		this.homeView = homeView;
		this.searchMenu = new SearchMenu(this);
		this.gridFormPanel = new GridFormPanel(this);
		addComponents(searchMenu, gridFormPanel);
		setComponentAlignment(searchMenu, Alignment.TOP_CENTER);
		setComponentAlignment(gridFormPanel, Alignment.MIDDLE_CENTER);
		setExpandRatio(searchMenu, 1);
		setExpandRatio(gridFormPanel, 10);
	}

	public HomeView getHomeView() {
		return homeView;
	}

	public void setHomeView(HomeView homeView) {
		this.homeView = homeView;
	}

	public SearchMenu getSearchMenu() {
		return searchMenu;
	}

	public void setSearchMenu(SearchMenu searchMenu) {
		this.searchMenu = searchMenu;
	}

	public GridFormPanel getGridFormLayout() {
		return gridFormPanel;
	}

	public void setGridFormLayout(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}

	public MenuSelection getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(MenuSelection itemSelected) {
		this.itemSelected = itemSelected;
	}
}
