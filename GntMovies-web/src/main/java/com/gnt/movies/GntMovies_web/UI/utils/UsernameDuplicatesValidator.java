package com.gnt.movies.GntMovies_web.UI.utils;

import com.gnt.movies.GntMovies_web.UI.MyUI;
import com.gnt.movies.GntMovies_web.UI.forms.UserForm;
import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.gnt.movies.beans.UserBean;
import com.gnt.movies.entities.User;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;

public class UsernameDuplicatesValidator implements Validator<String>{
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	private UserForm userForm;
	
	public UsernameDuplicatesValidator(String errorMessage, UserForm userForm) {
		this.errorMessage = errorMessage;
		this.userForm = userForm;
	}
	
	@Override
	public ValidationResult apply(String value, ValueContext context) {
		User selectedUser = findSelectedUser();
		UserBean userBean = MyUI.get().getUserBean();

		if (selectedUser == null) {
			if (userBean.usernameExists(value)) {
				return ValidationResult.error(errorMessage);
			} else {
				return ValidationResult.ok();
			}
		} else {
			if (userBean.usernameExists(value) &&
					selectedUser.getId() != userBean.getUserByUsername(value).getId()) {
				return ValidationResult.error(errorMessage);
			} else {
				return ValidationResult.ok();
			}
		}
	}
	
	private User findSelectedUser() {
		User selectedUser = null;
		GridFormPanel gridFormPanel = userForm.getUserFormLayout().getGridFormLayout();
		if (gridFormPanel != null) {
			selectedUser = gridFormPanel.getUserGrid().getSelectedUser();
		}
		return selectedUser;
	}

}
