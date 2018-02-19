package com.gnt.movies.GntMovies_web.UI.textfields;

import com.github.appreciated.material.MaterialTheme;
import com.gnt.movies.GntMovies_web.UI.layouts.SearchMenu;
import com.gnt.movies.GntMovies_web.UI.providers.MovieProvider;
import com.gnt.movies.GntMovies_web.UI.providers.ShowProvider;
import com.gnt.movies.GntMovies_web.UI.providers.UserProvider;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.GntMovies_web.UI.views.MenuView.MenuSelection;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class SearchField extends TextField {
private SearchMenu searchMenu;
	
	public SearchField(SearchMenu searchMenu) {
		this.setPlaceholder("Search");
		this.searchMenu = searchMenu;
		this.setIcon(VaadinIcons.SEARCH);
		setStyleName(MaterialTheme.TEXTFIELD_INLINE_ICON);
		addStyleName(Utilities.SCSS_SEARCH_FIELD);
		addValueChangeListener(event->filter());
		addFocusListener(event->addStyleName(MaterialTheme.TEXTFIELD_FLOATING));
		addBlurListener(event->removeStyleName(MaterialTheme.TEXTFIELD_FLOATING));
	}
	
	private void filter() {
		MenuSelection selectedItem = searchMenu.getMenuView().getItemSelected();
		if (selectedItem.equals(MenuSelection.MOVIE)) {
			MovieProvider provider = searchMenu.getMenuView().getGridFormLayout().getMovieGrid().getMovieProvider();
			provider.setFilter(this.getValue());
		}else if (selectedItem.equals(MenuSelection.SHOW)) {
			ShowProvider provider = searchMenu.getMenuView().getGridFormLayout().getShowGrid().getShowProvider();
			provider.setFilter(this.getValue());
		} else if (selectedItem.equals(MenuSelection.USER)) {
			UserProvider provider = searchMenu.getMenuView().getGridFormLayout().getUserGrid().getUserProvider();
			provider.setFilter(this.getValue());
		}
	}

	public SearchMenu getSearchMenu() {
		return searchMenu;
	}

	public void setSearchMenu(SearchMenu searchMenu) {
		this.searchMenu = searchMenu;
	}
}
