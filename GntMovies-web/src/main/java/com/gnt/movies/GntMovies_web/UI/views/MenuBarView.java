package com.gnt.movies.GntMovies_web.UI.views;

import java.util.ArrayList;
import java.util.List;

import com.gnt.movies.GntMovies_web.UI.utils.OnDiscardWindowClickListener;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.GntMovies_web.UI.views.MenuView.MenuSelection;
import com.gnt.movies.GntMovies_web.UI.windows.DiscardChangesWindow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class MenuBarView extends CustomComponent{
	private HomeView homeView;
	private MenuView menuView;
	private ValoMenuItemButton movies;
	private ValoMenuItemButton shows;
	private ValoMenuItemButton users;
	private ValoMenuItemButton logout;
	private List<ValoMenuItemButton> menuBarButtonsList = new ArrayList<>();

	public MenuBarView(HomeView homeView, MenuView menuView) {
		this.homeView = homeView;
		this.menuView = menuView;
		setCompositionRoot(buildContent());
	}

	public Component buildContent() {
		final CssLayout menuContent = new CssLayout();
		menuContent.addStyleName(Utilities.SCSS_MENU_IMAGE);
		menuContent.addStyleName(Utilities.SCSS_SIDEBAR);
		menuContent.addStyleName(ValoTheme.MENU_PART);
		menuContent.addStyleName(Utilities.VALO_NO_VERTICAL);
		menuContent.addStyleName(Utilities.VALO_NO_HORIZONTAL);
		menuContent.setHeight("100%");
		menuContent.setWidth(null);
		menuContent.addComponent(buildTitle());
		menuContent.addComponent(buildToggleButton());
		menuContent.addComponent(buildMenuItems());
		return menuContent;
	}

	private Component buildTitle() {
		Label logo = new Label(Utilities.APP_LOGO, ContentMode.HTML);
		logo.setSizeUndefined();
		logo.addStyleName(Utilities.SCSS_MENU_TITLE);
		VerticalLayout logoWrapper = new VerticalLayout(logo);
		logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
		logoWrapper.setSpacing(false);
		return logoWrapper;
	}

	private Component buildToggleButton() {
		Button valoMenuToggleButton = new Button(Utilities.MENU_BUTTON);
		valoMenuToggleButton.addClickListener(event-> {
				if (getCompositionRoot().getStyleName().contains(Utilities.STYLE_VISIBLE)) {
					getCompositionRoot().removeStyleName(Utilities.STYLE_VISIBLE);
				} else {
					getCompositionRoot().addStyleName(Utilities.STYLE_VISIBLE);
				}
		});
		valoMenuToggleButton.setIcon(VaadinIcons.LIST);
		valoMenuToggleButton.addStyleName(Utilities.VALO_MENU_TOGGLE);
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
		return valoMenuToggleButton;
	}

	private Component buildMenuItems() {
		CssLayout menuItemsLayout = new CssLayout();
		menuItemsLayout.addStyleName(Utilities.VALO_MENU_ITEMS);
		movies = new ValoMenuItemButton(Utilities.MOVIES_BUTTON);
		shows = new ValoMenuItemButton(Utilities.SHOWS_BUTTON);
		users = new ValoMenuItemButton(Utilities.USERS_BUTTON);
		logout = new ValoMenuItemButton(Utilities.LOGOUT_BUTTON);
		addButtonsToList();
		setupMovieBtn();
		setupUsersBtn();
		setupShowBtn();
		setupLogoutBtn();
		menuItemsLayout.addComponents(movies, shows, users, logout);
		return menuItemsLayout;
	}
	
	private void addButtonsToList() {
		menuBarButtonsList.add(movies);
		menuBarButtonsList.add(shows);
		menuBarButtonsList.add(users);
		menuBarButtonsList.add(logout);
	}
	
	private void setupMovieBtn() {
		movies.setHeight(50, Unit.PIXELS);
		movies.addClickListener(e -> checkForMovieChanges(e));
		movies.addStyleName(Utilities.SCSS_MENU_ITEM);
		movies.setIcon(VaadinIcons.MOVIE);
	}
	
	private void setupShowBtn() {
		shows.setHeight(50, Unit.PIXELS);
		shows.addClickListener(e -> checkForShowChanges(e));
		shows.addStyleName(Utilities.SCSS_MENU_ITEM);
		shows.setIcon(VaadinIcons.FILE_MOVIE);
	}
	
	private void setupUsersBtn() {
		users.setHeight(50, Unit.PIXELS);
		users.addClickListener(e -> checkForUserChanges(e));
		users.addStyleName(Utilities.SCSS_MENU_ITEM);
		users.setIcon(VaadinIcons.USER);
	}
	
	private void setupLogoutBtn() {
		logout.setHeight(50, Unit.PIXELS);
		logout.addClickListener(e -> logout());
		logout.addStyleName(Utilities.SCSS_MENU_ITEM);
		logout.setIcon(VaadinIcons.SIGN_OUT);
	}	

	public final class ValoMenuItemButton extends Button {
		private final String viewName;

		public ValoMenuItemButton(final String name) {
			this.viewName = name;
			setPrimaryStyleName(Utilities.VALO_MENU_ITEM);
			setCaption(viewName);
		}
	}

	public void checkForMovieChanges(ClickEvent e) {
		if (checkIfIsFieldChanged()) {
			showDiscardChangesWindow(MenuSelection.MOVIE, e);
		} else {
			makeButtonSelected(e);
			showMovieView();
		}
	}

	public void checkForUserChanges(ClickEvent e) {
		if (checkIfIsFieldChanged()) {
			showDiscardChangesWindow(MenuSelection.USER, e);
		} else {
			makeButtonSelected(e);
			showUserView();
		}
	}
	
	public void checkForShowChanges(ClickEvent e) {
		if (checkIfIsFieldChanged()) {
			showDiscardChangesWindow(MenuSelection.SHOW, e);
		} else {
			makeButtonSelected(e);
			showShowView();
		}
	}
		
	public void logout() {
		if (checkIfIsFieldChanged()) {
			showDiscardChangesWindow(null, null);
		} else {
			getUI().getSession().setAttribute(Utilities.SESSION_USER, null);
			homeView.getMainView().checkForComponent();
		}
	}
	
	private void makeButtonSelected(ClickEvent e) {
		if (e != null) {
			menuBarButtonsList.forEach((button) -> 
				button.setStyleName("selectedstyle", e.getButton().getCaption().equals(button.getCaption()))
				);
		}
	}
	
	private boolean checkIfIsFieldChanged() {
		return menuView.getGridFormLayout().getUserFormLayout().getUserForm().isFieldChanged() ||
				menuView.getGridFormLayout().getMovieFormLayout().getMovieForm().isFieldChanged() ||
				menuView.getGridFormLayout().getShowFormLayout().getShowForm().isFieldChanged();
	}
	
	private void showDiscardChangesWindow(MenuSelection menuSelection, ClickEvent e) {
		new DiscardChangesWindow(new OnDiscardWindowClickListener() {
			@Override
			public void onClickDiscard() {
				menuView.getGridFormLayout().getUserFormLayout().getUserForm().setFieldChanged(false);
				menuView.getGridFormLayout().getMovieFormLayout().getMovieForm().setFieldChanged(false);
				menuView.getGridFormLayout().getShowFormLayout().getShowForm().setFieldChanged(false);
				makeButtonSelected(e);
				if (MenuSelection.MOVIE.equals(menuSelection)) {
					showMovieView();
				} else if (MenuSelection.USER.equals(menuSelection)) {
					showUserView();
				} else if (MenuSelection.SHOW.equals(menuSelection)) {
					showShowView();
				} 
				else {
					getUI().getSession().setAttribute(Utilities.SESSION_USER, null);
					homeView.getMainView().checkForComponent();
				}
			}

			@Override
			public void onClickCancel() {}

		}).show();
	}
	
	private void showMovieView() {
		menuView.getGridFormLayout().removeSplitComponents();
		menuView.getGridFormLayout().addMovieGrid();
		menuView.getGridFormLayout().getMovieGrid().deselectAll();
		menuView.setItemSelected(MenuSelection.MOVIE);
		enableSearchAndAdd();
	}
	
	private void showShowView() {
		menuView.getGridFormLayout().removeSplitComponents();
		menuView.getGridFormLayout().addShowGrid();
		menuView.getGridFormLayout().getShowGrid().deselectAll();
		menuView.setItemSelected(MenuSelection.SHOW);
		enableSearchAndAdd();
	}
	
	private void showUserView() {
		menuView.getGridFormLayout().removeSplitComponents();
		menuView.getGridFormLayout().addUserGrid();
		menuView.getGridFormLayout().getUserGrid().deselectAll();
		menuView.setItemSelected(MenuSelection.USER);
		enableSearchAndAdd();
	}	
	
	private void enableSearchAndAdd() {
		menuView.getSearchMenu().getSearchField().setEnabled(true);
		menuView.getSearchMenu().getAddNew().setEnabled(true);
	}
	
	public HomeView getHomeView() {
		return homeView;
	}

	public void setHomeView(HomeView homeView) {
		this.homeView = homeView;
	}

	public MenuView getMenuView() {
		return menuView;
	}

	public void setMenuView(MenuView menuView) {
		this.menuView = menuView;
	}
}
