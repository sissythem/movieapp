package com.gnt.movies.GntMovies_web.UI.grids;

import com.gnt.movies.GntMovies_web.UI.forms.UserForm;
import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.gnt.movies.GntMovies_web.UI.providers.UserProvider;
import com.gnt.movies.GntMovies_web.UI.utils.OnDiscardWindowClickListener;
import com.gnt.movies.GntMovies_web.UI.windows.DiscardChangesWindow;
import com.gnt.movies.entities.User;
import com.vaadin.ui.Grid;

@SuppressWarnings("serial")
public class UserGrid extends Grid<User> {
	private GridFormPanel gridFormPanel;
	private UserProvider userProvider = new UserProvider();
	private User selectedUser;
	
	public UserGrid(GridFormPanel gridFormPanel) {
		super(User.class);
		this.gridFormPanel = gridFormPanel;
		setSizeFull();
		initGrid();
	}

	private void initGrid() {
		setSelectionMode(SelectionMode.SINGLE);
		setColumns("name", "lastName", "email", "username", "mobile", "license");
		setDataProvider(userProvider);
		addUserGridClickListener();
	}
			
	private void addUserGridClickListener() {
		addItemClickListener(event -> {
			if (checkIfIsFieldChanged()) {
				showDiscardChangesWindow(event);
			} else {
				setSelectedUser(event.getItem());
				User selectedUser = event.getItem();
				clearForm();
				fillForm(selectedUser);
				gridFormPanel.getUserFormLayout().getUserForm().setFieldChanged(false);
			}
		});
	}
	
	private void clearForm() {
		gridFormPanel.getUserFormLayout().getUserForm().clearFormFields();
	}

	public void fillForm(User selectedUser) {
		UserForm userForm = gridFormPanel.getUserFormLayout().getUserForm();
		userForm.getNametf().setValue(selectedUser.getFirstname());
		userForm.getLastnametf().setValue(selectedUser.getLastname());
		userForm.getPasswdtf().setValue(selectedUser.getPassword());
		userForm.getUsernametf().setValue(selectedUser.getUsername());
		userForm.getEmailtf().setValue(selectedUser.getEmail());
		userForm.getAgetf().setValue(selectedUser.getAge().toString());
		userForm.getBirthdatetf().setValue(selectedUser.getBirthdate().toString());
		gridFormPanel.getHsplit().setSecondComponent(gridFormPanel.getUserFormLayout());
	}

	private boolean checkIfIsFieldChanged() {
		return gridFormPanel.getUserFormLayout().getUserForm().isFieldChanged();
	}

	private void showDiscardChangesWindow(ItemClick<User> event) {
		new DiscardChangesWindow(new OnDiscardWindowClickListener() {
			@Override
			public void onClickDiscard() {
				setSelectedUser(event.getItem());
				User selectedUser = event.getItem();
				clearForm();
				fillForm(selectedUser);
				gridFormPanel.getUserFormLayout().getUserForm().setFieldChanged(false);
			}

			@Override
			public void onClickCancel() {
				select(getSelectedUser());
			}
		}).show();
	}
	
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}

	public GridFormPanel getGridFormPanel() {
		return gridFormPanel;
	}

	public void setGridFormPanel(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}

	public UserProvider getUserProvider() {
		return userProvider;
	}

	public void setUserProvider(UserProvider userProvider) {
		this.userProvider = userProvider;
	}

}
