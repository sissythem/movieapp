package com.gnt.movies.GntMovies_web.UI.forms;


import com.gnt.movies.GntMovies_web.UI.panels.LoginPanel;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.GntMovies_web.UI.views.HomeView;
import com.gnt.movies.GntMovies_web.UI.windows.Message;
import com.gnt.movies.GntMovies_web.UI.windows.Message.MessageType;
import com.gnt.movies.entities.User;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LoginForm extends VerticalLayout {
	private LoginPanel loginPanel;
	private Label signinLabel;
	private TextField username;
	private PasswordField password;
	private Button loginBtn;
	private final Binder<User> binder = new Binder<>();

	public LoginForm(LoginPanel loginPanel) {
		setSizeFull();
		initComponents(loginPanel);
		addComponents(signinLabel, username, password, loginBtn);
		customizeLoginButton();
		customizeLayout();
	}
	
	private void initComponents(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
		signinLabel = new Label(Utilities.SIGN_IN_LABEL);
		signinLabel.setStyleName(Utilities.SCSS_LOGIN_FORM_TEXT);
		username = new TextField(Utilities.USERNAME_FIELD);
		password = new PasswordField(Utilities.PASSWORD_FIELD);
		loginBtn = new Button(Utilities.SIGN_IN_LABEL);
		addPlaceholders();
		addValidation();
	}
	
	private void addPlaceholders() {
		this.username.setPlaceholder(Utilities.USERNAME_FIELD);
		this.password.setPlaceholder(Utilities.PASSWORD_FIELD);
	}
	
	private void addValidation() {
		binder.forField(username).asRequired("Username is mandatory").bind(User::getUsername, User::setUsername);
		binder.forField(password).asRequired("Password is mandatory").bind(User::getPassword, User::setPassword);
	}
	
	private void customizeLoginButton() {
		loginBtn.setWidth(190, Unit.PIXELS);
		loginBtn.setStyleName(Utilities.SCSS_LOGIN);
		loginBtn.addClickListener(event-> onLoginClick(username, password));
	}
	
	private void customizeLayout() {
		setComponentAlignment(signinLabel, Alignment.MIDDLE_CENTER);
		setComponentAlignment(username, Alignment.MIDDLE_CENTER);
		setComponentAlignment(password, Alignment.MIDDLE_CENTER);
		setComponentAlignment(loginBtn, Alignment.MIDDLE_CENTER);
	}

	public void onLoginClick(TextField username, PasswordField password) {
		try {
			User user = new User();
			user.setUsername(username.getValue());
			user.setPassword(password.getValue());
			binder.writeBean(user);
			if (validCredentials(username.getValue(), password.getValue())) {
				getSession().setAttribute(Utilities.SESSION_USER, username.getValue());
				loginPanel.getLoginView().getMainView().checkForComponent();
				HomeView homeView = new HomeView(loginPanel.getLoginView().getMainView());
				loginPanel.getLoginView().getMainView().removeAllComponents();
				loginPanel.getLoginView().getMainView().addComponent(homeView);
			} else {
				Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
			}
		} catch (ValidationException e) {
			Message.show("Error", "Could not login! Please provide username and password", MessageType.ERROR);
		}
		

	}
	
	private boolean validCredentials(String username, String password) {
		// TODO Check credentials

		return true;
	}

	public TextField getUsername() {
		return username;
	}

	public void setUsername(TextField username) {
		this.username = username;
	}

	public PasswordField getPassword() {
		return password;
	}

	public void setPassword(PasswordField password) {
		this.password = password;
	}

	public Button getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(Button loginBtn) {
		this.loginBtn = loginBtn;
	}

	public Label getSigninLabel() {
		return signinLabel;
	}

	public void setSigninLabel(Label signinLabel) {
		this.signinLabel = signinLabel;
	}
}
