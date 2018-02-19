package com.gnt.movies.GntMovies_web.UI.layouts;

import com.github.appreciated.material.MaterialTheme;
import com.gnt.movies.GntMovies_web.UI.MyUI;
import com.gnt.movies.GntMovies_web.UI.forms.MovieForm;
import com.gnt.movies.GntMovies_web.UI.forms.ShowForm;
import com.gnt.movies.GntMovies_web.UI.forms.UserForm;
import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.gnt.movies.GntMovies_web.UI.utils.OnDeleteWindowClickListener;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.GntMovies_web.UI.views.MenuView.MenuSelection;
import com.gnt.movies.GntMovies_web.UI.windows.DeleteWindow;
import com.gnt.movies.GntMovies_web.UI.windows.Message;
import com.gnt.movies.GntMovies_web.UI.windows.Message.MessageType;
import com.gnt.movies.entities.Movie;
import com.gnt.movies.entities.Show;
import com.gnt.movies.entities.User;
import com.gnt.movies.utilities.Logger;
import com.gnt.movies.utilities.Utils;
import com.vaadin.data.ValidationException;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class EditButtons extends HorizontalLayout {
	private static final Logger logger = Utils.getLogger();
	private static String startingMessageLog = "[" + EditButtons.class.getSimpleName() + "] ";

	private UserFormLayout userFormLayout;
	private MovieFormLayout movieFormLayout;
	private ShowFormLayout showFormLayout;
	private GridFormPanel gridFormPanel;
	private Button updateButton;
	private Button discardButton;
	private Button deleteButton;

	public EditButtons(UserFormLayout userFormLayout, GridFormPanel gridFormPanel) {
		this.userFormLayout = userFormLayout;
		this.gridFormPanel = gridFormPanel;
		initButtonsLayout();
	}

	public EditButtons(MovieFormLayout movieFormLayout, GridFormPanel gridFormPanel) {
		this.movieFormLayout = movieFormLayout;
		this.gridFormPanel = gridFormPanel;
		initButtonsLayout();
	}

	public EditButtons(ShowFormLayout showFormLayout, GridFormPanel gridFormPanel) {
		this.showFormLayout = showFormLayout;
		this.gridFormPanel = gridFormPanel;
		initButtonsLayout();
	}

	public EditButtons(MovieFormLayout movieFormLayout) {
		this.movieFormLayout = movieFormLayout;
		initButtonsForNewItems();
	}

	public EditButtons(ShowFormLayout showFormLayout) {
		this.showFormLayout = showFormLayout;
		initButtonsForNewItems();
	}

	public EditButtons(UserFormLayout userFormLayout) {
		this.userFormLayout = userFormLayout;
		initButtonsForNewItems();
	}

	private void initButtonsForNewItems() {
		setSizeFull();
		setMargin(false);
		this.updateButton = new Button(Utilities.CREATE_BUTTON);
		this.discardButton = new Button(Utilities.CANCEL_BUTTON);
		HorizontalLayout buttons = new HorizontalLayout(updateButton, discardButton);
		addComponents(buttons);
		setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);
		setMargin(new MarginInfo(false, false, true, true));
		updateButton.addStyleName(Utilities.SCSS_UPDATE_BUTTON);
	}

	private void discardClickListener() {
		MenuSelection selection = gridFormPanel.getMenuView().getItemSelected();
		if (selection.equals(MenuSelection.USER)) {
			User user = gridFormPanel.getUserGrid().getSelectedUser();
			gridFormPanel.getUserGrid().fillForm(user);
			gridFormPanel.getUserFormLayout().getUserForm().setFieldChanged(false);
		} else if (selection.equals(MenuSelection.MOVIE)) {
			Movie movie = gridFormPanel.getMovieGrid().getSelectedMovie();
			gridFormPanel.getMovieGrid().fillForm(movie);
			gridFormPanel.getMovieFormLayout().getMovieForm().setFieldChanged(false);
		} else if (selection.equals(MenuSelection.SHOW)) {
			Show show = gridFormPanel.getShowGrid().getSelectedShow();
			gridFormPanel.getShowGrid().fillForm(show);
			gridFormPanel.getShowFormLayout().getShowForm().setFieldChanged(false);
		}
	}

	private void initButtonsLayout() {
		setSizeFull();
		setMargin(false);
		this.updateButton = new Button(Utilities.UPDATE_BUTTON);
		this.discardButton = new Button(Utilities.DISCARD_BUTTON);
		this.deleteButton = new Button(Utilities.DELETE_BUTTON);
		updateButton.addClickListener(event -> onUpdate());
		discardButton.addClickListener(event -> discardClickListener());
		deleteButton.addClickListener(event -> onDelete());

		addComponents(updateButton, discardButton, deleteButton);
		setComponentAlignment(updateButton, Alignment.BOTTOM_LEFT);
		setComponentAlignment(discardButton, Alignment.BOTTOM_CENTER);
		setComponentAlignment(deleteButton, Alignment.BOTTOM_RIGHT);
		setMargin(new MarginInfo(false, true, false, true));
		setButtonsStyle();
	}

	private void onUpdate() {
		MenuSelection selectedItem = gridFormPanel.getMenuView().getItemSelected();
		if (selectedItem.equals(MenuSelection.MOVIE)) {
			updateMovie();
		} else if (selectedItem.equals(MenuSelection.USER)) {
			updateUser();
		} else if (selectedItem.equals(MenuSelection.SHOW)) {
			updateShow();
		}
	}

	private void updateMovie() {
		MovieForm movieForm = movieFormLayout.getMovieForm();

		Movie movieFromGrid = gridFormPanel.getMovieGrid().getSelectedMovie();
		Movie updatedMovie = new Movie();
		updatedMovie.setId(movieFromGrid.getId());
		try {
			movieForm.getBinder().writeBean(updatedMovie);
			MyUI.get().getMovieBean().updateMovieInDataBase(updatedMovie);
			movieForm.setFieldChanged(false);
			gridFormPanel.getMovieGrid().getMovieProvider().refreshAll();
		} catch (ValidationException e) {
			logger.error(startingMessageLog + "User is trying to update a movie with invalid data");
			Message.show("Error", "Movie could not be saved, please check error messages for each field.",
					MessageType.ERROR);
		}
	}

	private void updateShow() {
		ShowForm showForm = showFormLayout.getShowForm();

		Show showFromGrid = gridFormPanel.getShowGrid().getSelectedShow();
		Show updatedShow = new Show();
		updatedShow.setId(showFromGrid.getId());
		try {
			showForm.getBinder().writeBean(updatedShow);
			MyUI.get().getShowBean().updateShowInDataBase(updatedShow);
			showForm.setFieldChanged(false);
			gridFormPanel.getShowGrid().getShowProvider().refreshAll();
		} catch (ValidationException e) {
			logger.error(startingMessageLog + "User is trying to update a show with invalid data");
			Message.show("Error", "Show could not be saved, please check error messages for each field.",
					MessageType.ERROR);
		}
	}

	private void updateUser() {
		UserForm userForm = userFormLayout.getUserForm();
		User userGrid = gridFormPanel.getUserGrid().getSelectedUser();
		User user = new User(userForm.getNametf().getValue(), userForm.getLastnametf().getValue(),
				Integer.parseInt(userForm.getAgetf().getValue()), userForm.getEmailtf().getValue(),
				userForm.getUsernametf().getValue(), userForm.getPasswdtf().getValue());
		user.setId(userGrid.getId());
		try {
			userForm.getBinder().writeBean(user);
			MyUI.get().getUserBean().updateUser(user);
			userForm.setFieldChanged(false);
			gridFormPanel.getUserGrid().getUserProvider().refreshAll();
		} catch (ValidationException e) {
			logger.error(startingMessageLog + "User is trying to update a user with invalid data");
			Message.show("Error", "User could not be saved, please check error messages for each field.",
					MessageType.ERROR);
		}
	}

	private void onDelete() {
		new DeleteWindow(new OnDeleteWindowClickListener() {

			@Override
			public void onClickDelete() {
				MenuSelection selectedItem = gridFormPanel.getMenuView().getItemSelected();
				if (selectedItem.equals(MenuSelection.MOVIE)) {
					deleteMovie(selectedItem);
				} else if (selectedItem.equals(MenuSelection.USER)) {
					deleteUser(selectedItem);
				} else if(selectedItem.equals(MenuSelection.SHOW)) {
					deleteShow(selectedItem);
				}
			}
		}).show();
	}

	private void deleteMovie(MenuSelection selectedItem) {
		Movie movieGrid = gridFormPanel.getMovieGrid().getSelectedMovie();
		if (movieGrid != null) {
			MyUI.get().getMovieBean().deleteMovie(movieGrid);
			gridFormPanel.getMovieGrid().getMovieProvider().refreshAll();
			removeForm(selectedItem);
		} else {
			Message.show("Error", "Select a user from grid in order to delete", MessageType.ERROR);
		}
	}
	
	private void deleteShow(MenuSelection selectedItem) {
		Show showGrid = gridFormPanel.getShowGrid().getSelectedShow();
		if (showGrid != null) {
			MyUI.get().getShowBean().deleteShow(showGrid);
			gridFormPanel.getShowGrid().getShowProvider().refreshAll();
			removeForm(selectedItem);
		} else {
			Message.show("Error", "Select a user from grid in order to delete", MessageType.ERROR);
		}
	}

	private void deleteUser(MenuSelection selectedItem) {
		User userGrid = gridFormPanel.getUserGrid().getSelectedUser();
		if (userGrid != null) {
			MyUI.get().getUserBean().deleteUser(userGrid);
			gridFormPanel.getUserGrid().getUserProvider().refreshAll();
			removeForm(selectedItem);
		} else {
			Message.show("Error", "Select a user from grid in order to delete", MessageType.ERROR);
		}
	}

	private void removeForm(MenuSelection selectedItem) {
		if (selectedItem.equals(MenuSelection.MOVIE)) {
			gridFormPanel.removeSplitComponents();
			gridFormPanel.addMovieGrid();
			gridFormPanel.getMovieGrid().deselectAll();
			gridFormPanel.getMovieFormLayout().getMovieForm().setFieldChanged(false);
		} else if (selectedItem.equals(MenuSelection.USER)) {
			gridFormPanel.removeSplitComponents();
			gridFormPanel.addUserGrid();
			gridFormPanel.getUserGrid().deselectAll();
			gridFormPanel.getUserFormLayout().getUserForm().setFieldChanged(false);
		} else if (selectedItem.equals(MenuSelection.SHOW)) {
			gridFormPanel.removeSplitComponents();
			gridFormPanel.addShowGrid();
			gridFormPanel.getShowGrid().deselectAll();
			gridFormPanel.getShowFormLayout().getShowForm().setFieldChanged(false);
		}
	}

	private void setButtonsStyle() {
		updateButton.addStyleName(Utilities.SCSS_UPDATE_BUTTON);
		deleteButton.setStyleName(MaterialTheme.BUTTON_DANGER);
	}

	public UserFormLayout getUserFormLayout() {
		return userFormLayout;
	}

	public void setUserFormLayout(UserFormLayout userFormLayout) {
		this.userFormLayout = userFormLayout;
	}

	public Button getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(Button updateButton) {
		this.updateButton = updateButton;
	}

	public Button getDiscardButton() {
		return discardButton;
	}

	public void setDiscardButton(Button discardButton) {
		this.discardButton = discardButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public GridFormPanel getGridFormPanel() {
		return gridFormPanel;
	}

	public void setGridFormPanel(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}

	public MovieFormLayout getMovieFormLayout() {
		return movieFormLayout;
	}

	public void setMovieFormLayout(MovieFormLayout movieFormLayout) {
		this.movieFormLayout = movieFormLayout;
	}

	public ShowFormLayout getShowFormLayout() {
		return showFormLayout;
	}

	public void setShowFormLayout(ShowFormLayout showFormLayout) {
		this.showFormLayout = showFormLayout;
	}

}
