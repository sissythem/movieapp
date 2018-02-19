package com.gnt.movies.GntMovies_web.UI.forms;

import com.gnt.movies.GntMovies_web.UI.layouts.UserFormLayout;
import com.gnt.movies.GntMovies_web.UI.utils.EmailDuplicatesValidator;
import com.gnt.movies.GntMovies_web.UI.utils.UsernameDuplicatesValidator;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.entities.User;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UserForm extends VerticalLayout {
	private static String FIELD_WIDTH = "95%";
	private final Binder<User> binder = new Binder<>();
	private UserFormLayout userFormLayout;
	private boolean isFieldChanged = false;
	private TextField nametf;
	private TextField lastnametf;
	private TextField usernametf;
	private TextField emailtf;
	private PasswordField passwdtf;
	private TextField agetf;

	public UserForm(UserFormLayout userFormLayout) {
		this.userFormLayout = userFormLayout;
		setSizeFull();
		initFields();
		addPlaceHolders();
		addValidation();
		addComponents(nametf, lastnametf, usernametf, emailtf, passwdtf, agetf);
		updateFields();
	}

	private void initFields() {
		nametf = new TextField(Utilities.NAME_FIELD);
		lastnametf = new TextField(Utilities.LAST_NAME_FIELD);
		usernametf = new TextField(Utilities.USERNAME_FIELD);
		emailtf = new TextField(Utilities.EMAIL_FIELD);
		passwdtf = new PasswordField(Utilities.PASSWORD_FIELD);
		agetf = new TextField(Utilities.AGE_FIELD);
	}

	private void addPlaceHolders() {
		nametf.setPlaceholder(Utilities.NAME_FIELD);
		lastnametf.setPlaceholder(Utilities.LAST_NAME_FIELD);
		usernametf.setPlaceholder(Utilities.USERNAME_FIELD);
		emailtf.setPlaceholder(Utilities.EMAIL_FIELD);
		passwdtf.setPlaceholder(Utilities.PASSWORD_FIELD);
		agetf.setPlaceholder(Utilities.AGE_FIELD);
	}

	private void addValidation() {
		binder.forField(nametf).asRequired("Name is required").bind(User::getFirstname, User::setFirstname);
		binder.forField(lastnametf).asRequired("Last Name is required").bind(User::getLastname, User::setLastname);
		binder.forField(usernametf).asRequired("Username is required")
				.withValidator(new UsernameDuplicatesValidator("This username already exists", this))
				.bind(User::getUsername, User::setUsername);
		binder.forField(emailtf).asRequired("Email is required")
				.withValidator(new EmailValidator("This is not a valid email address"))
				.withValidator(new EmailDuplicatesValidator("This email address already exists", this))
				.bind(User::getEmail, User::setEmail);
		binder.forField(passwdtf).asRequired("Password is required").bind(User::getPassword, User::setPassword);
	}

	private void updateFields() {
		setTextFieldWidth();
		setRequiredFields();
		addValueChangeListener();
	}

	private void setTextFieldWidth() {
		nametf.setWidth(FIELD_WIDTH);
		lastnametf.setWidth(FIELD_WIDTH);
		usernametf.setWidth(FIELD_WIDTH);
		emailtf.setWidth(FIELD_WIDTH);
		passwdtf.setWidth(FIELD_WIDTH);
		agetf.setWidth(FIELD_WIDTH);
	}

	private void addValueChangeListener() {
		nametf.addValueChangeListener(event -> setFieldChanged(true));
		lastnametf.addValueChangeListener(event -> setFieldChanged(true));
		usernametf.addValueChangeListener(event -> setFieldChanged(true));
		emailtf.addValueChangeListener(event -> setFieldChanged(true));
		passwdtf.addValueChangeListener(event -> setFieldChanged(true));
		agetf.addValueChangeListener(event -> setFieldChanged(true));
	}

	private void setRequiredFields() {
		nametf.setRequiredIndicatorVisible(true);
		lastnametf.setRequiredIndicatorVisible(true);
		usernametf.setRequiredIndicatorVisible(true);
		emailtf.setRequiredIndicatorVisible(true);
		passwdtf.setRequiredIndicatorVisible(true);
		agetf.setRequiredIndicatorVisible(true);
	}
	
	public void clearFormFields() {
		nametf.clear();
		lastnametf.clear();
		usernametf.clear();
		emailtf.clear();
		passwdtf.clear();
		agetf.clear();
	}

	public TextField getNametf() {
		return nametf;
	}

	public void setNametf(TextField nametf) {
		this.nametf = nametf;
	}

	public TextField getLastnametf() {
		return lastnametf;
	}

	public void setLastnametf(TextField lastname) {
		this.lastnametf = lastname;
	}

	public TextField getUsernametf() {
		return usernametf;
	}

	public void setUsernametf(TextField username) {
		this.usernametf = username;
	}

	public TextField getEmailtf() {
		return emailtf;
	}

	public void setEmailtf(TextField email) {
		this.emailtf = email;
	}

	public PasswordField getPasswdtf() {
		return passwdtf;
	}

	public void setPasswdtf(PasswordField passwd) {
		this.passwdtf = passwd;
	}

	public UserFormLayout getUserFormLayout() {
		return userFormLayout;
	}

	public void setUserFormLayout(UserFormLayout userFormLayout) {
		this.userFormLayout = userFormLayout;
	}

	public TextField getAgetf() {
		return agetf;
	}

	public void setAgetf(TextField agetf) {
		this.agetf = agetf;
	}

	public boolean isFieldChanged() {
		return isFieldChanged;
	}

	public void setFieldChanged(boolean isFieldChanged) {
		this.isFieldChanged = isFieldChanged;
	}

	public Binder<User> getBinder() {
		return binder;
	}
}
