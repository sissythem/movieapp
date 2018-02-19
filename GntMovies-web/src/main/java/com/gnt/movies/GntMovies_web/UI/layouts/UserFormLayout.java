package com.gnt.movies.GntMovies_web.UI.layouts;

import com.gnt.movies.GntMovies_web.UI.forms.UserForm;
import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UserFormLayout extends VerticalLayout {
	private GridFormPanel gridFormPanel;
	private UserForm userForm;
	private EditButtons buttons;
	
	public UserFormLayout() {
		this.userForm = new UserForm(this);
		this.buttons = new EditButtons(this);
		initLayout();
	}
	
	public UserFormLayout(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
		this.userForm = new UserForm(this);
		this.buttons = new EditButtons(this, gridFormPanel);
		initLayout();
	}

 	private void initLayout() {
 		setSizeFull();
		setMargin(false);
		addComponents(userForm, buttons);
		setComponentAlignment(userForm, Alignment.TOP_CENTER);
		setComponentAlignment(buttons, Alignment.BOTTOM_CENTER);
		setExpandRatio(userForm, 10);
		setExpandRatio(buttons, 1);
		setMargin(new MarginInfo(false, false, true, false));
 	}
	public GridFormPanel getGridFormLayout() {
		return gridFormPanel;
	}

	public void setGridFormLayout(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}

	public UserForm getUserForm() {
		return userForm;
	}

	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}

	public EditButtons getButtons() {
		return buttons;
	}

	public void setButtons(EditButtons buttons) {
		this.buttons = buttons;
	}

}
