package com.gnt.movies.GntMovies_web.UI.layouts;

import com.gnt.movies.GntMovies_web.UI.MyUI;
import com.gnt.movies.GntMovies_web.UI.textfields.SearchField;
import com.gnt.movies.GntMovies_web.UI.utils.OnDiscardWindowClickListener;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.GntMovies_web.UI.views.MenuView;
import com.gnt.movies.GntMovies_web.UI.views.MenuView.MenuSelection;
import com.gnt.movies.GntMovies_web.UI.windows.DiscardChangesWindow;
import com.gnt.movies.GntMovies_web.UI.windows.NewMovieWindow;
import com.gnt.movies.GntMovies_web.UI.windows.NewShowWindow;
import com.gnt.movies.GntMovies_web.UI.windows.NewUserWindow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class SearchMenu extends HorizontalLayout {
	private MenuView menuView;
	private Button addNew;
	private SearchField searchField;

	public SearchMenu(MenuView menuView) {
		this.menuView = menuView;
		this.searchField = new SearchField(this);
		searchField.setEnabled(false);
		initaddNewButton();
		setSizeFull();
		addComponents(searchField, addNew);
		setComponentAlignment(searchField, Alignment.TOP_LEFT);
		setComponentAlignment(addNew, Alignment.TOP_RIGHT);
	}

	private void initaddNewButton() {
		this.addNew = new Button();
		this.addNew.setCaption(Utilities.ADD_NEW_BUTTON);
		this.addNew.setIcon(VaadinIcons.PLUS_CIRCLE);
		this.addNew.setStyleName(Utilities.BUTTON_BORDERLESS);
		this.addNew.addClickListener(event->createNewItems());
		addNew.setEnabled(false);
	}
	
	private boolean checkIfIsFieldChanged() {
		return menuView.getGridFormLayout().getUserFormLayout().getUserForm().isFieldChanged() ||
				menuView.getGridFormLayout().getMovieFormLayout().getMovieForm().isFieldChanged() ||
				menuView.getGridFormLayout().getShowFormLayout().getShowForm.isFieldChanged;
	}
	
	private void showDiscardChangesWindow(MenuSelection selectedItem) {
		new DiscardChangesWindow(new OnDiscardWindowClickListener() {
			@Override
			public void onClickDiscard() {
				menuView.getGridFormLayout().getUserFormLayout().getUserForm().setFieldChanged(false);
				menuView.getGridFormLayout().getMovieFormLayout().getMovieForm().setFieldChanged(false);
				menuView.getGridFormLayout().getShowFormLayout().getShowForm().setFieldChanged(false);
				if (selectedItem.equals(MenuSelection.MOVIE)) {
					menuView.getGridFormLayout().removeSplitComponents();
					menuView.getGridFormLayout().addMovieGrid();
					menuView.getGridFormLayout().getMovieGrid().deselectAll();
					createNewMovieWindow();
					
				} else if (selectedItem.equals(MenuSelection.SHOW)){
					menuView.getGridFormLayout().removeSplitComponents();
					menuView.getGridFormLayout().addShowGrid();
					menuView.getGridFormLayout().getShowGrid().deselectAll();
					createNewShowWindow();
				} else if (selectedItem.equals(MenuSelection.USER)){
					menuView.getGridFormLayout().removeSplitComponents();
					menuView.getGridFormLayout().addUserGrid();
					menuView.getGridFormLayout().getUserGrid().deselectAll();
					createNewUserWindow();
				}
			}

			@Override
			public void onClickCancel() {}
		}).show();
	}
	
	private void createNewMovieWindow() {
		NewMovieWindow window = new NewMovieWindow(this);
		MyUI.get().addWindow(window);
	}
	
	private void createNewShowWindow() {
		NewShowWindow window = new NewShowWindow(this);
		MyUI.get().addWindow(window);
	}
	
	private void createNewUserWindow() {
		NewUserWindow window = new NewUserWindow(this);
		MyUI.get().addWindow(window);
	}
	
	private void createNewItems() {
		MenuSelection selectedItem = menuView.getItemSelected();
		if (selectedItem.equals(MenuSelection.MOVIE)) {
			if (checkIfIsFieldChanged()) {
				showDiscardChangesWindow(selectedItem);
			} else {
				createNewMovieWindow();
			}
		} else if (selectedItem.equals(MenuSelection.SHOW)) {
			if (checkIfIsFieldChanged()) {
				showDiscardChangesWindow(selectedItem);
			} else {
				createNewShowWindow();
			}
		} else if (selectedItem.equals(MenuSelection.USER)) {
			if (checkIfIsFieldChanged()) {
				showDiscardChangesWindow(selectedItem);
			} else {
				createNewUserWindow();
			}
		}
	}

	public Button getAddNew() {
		return addNew;
	}

	public void setAddNew(Button addNew) {
		this.addNew = addNew;
	}

	public MenuView getMenuView() {
		return menuView;
	}

	public void setMenuView(MenuView menuView) {
		this.menuView = menuView;
	}

	public SearchField getSearchField() {
		return searchField;
	}

	public void setSearchField(SearchField searchField) {
		this.searchField = searchField;
	}
}
