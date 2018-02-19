package com.gnt.movies.GntMovies_web.UI.views;

import com.vaadin.navigator.View;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class HomeView extends HorizontalLayout implements View {
	private MainView mainView;
	private MenuBarView menuBarView;
	private MenuView menuView;
	
	public HomeView(MainView mainView) {
		setSizeFull();
		setMargin(false);
		this.mainView=mainView;
		this.menuView = new MenuView(this);
		this.menuBarView = new MenuBarView(this, menuView);
		addComponents(menuBarView, menuView);
		menuBarView.setWidth(menuBarView.buildContent().getWidth(), menuBarView.buildContent().getWidthUnits());
		menuBarView.setHeight(100, Unit.PERCENTAGE);
		menuView.setHeight(100, Unit.PERCENTAGE);
		setExpandRatio(menuView, 1);
	}

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public MenuBarView getMenuBarView() {
		return menuBarView;
	}

	public void setMenuBarView(MenuBarView menuBarView) {
		this.menuBarView = menuBarView;
	}

	public MenuView getMenuView() {
		return menuView;
	}

	public void setMenuView(MenuView menuView) {
		this.menuView = menuView;
	}	
	
}
