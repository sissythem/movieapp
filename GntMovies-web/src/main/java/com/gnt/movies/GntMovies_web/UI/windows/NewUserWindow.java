package com.gnt.movies.GntMovies_web.UI.windows;

import com.gnt.movies.GntMovies_web.UI.layouts.SearchMenu;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class NewUserWindow extends Window {
	private SearchMenu searchMenu;
	
	public NewUserWindow(SearchMenu searchMenu) {
		this.searchMenu=searchMenu;
	}

	public SearchMenu getSearchMenu() {
		return searchMenu;
	}

	public void setSearchMenu(SearchMenu searchMenu) {
		this.searchMenu = searchMenu;
	}

}
