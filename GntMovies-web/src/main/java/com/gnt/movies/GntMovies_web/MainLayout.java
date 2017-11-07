package com.gnt.movies.GntMovies_web;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.gnt.movies.beans.UserBean;
import com.gnt.movies.entities.MovieFavorite;
import com.gnt.movies.entities.MovieReview;
import com.gnt.movies.entities.ShowFavorite;
import com.gnt.movies.entities.ShowReview;
import com.gnt.movies.entities.User;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public MainLayout() {
		final TextField name = new TextField();
		name.setCaption("Type your name here:");

		Button button = new Button("Click Me");
		button.addClickListener(e -> {
			UserBean userBean = MyUI.get().getUserBean();
			// User(int age, String email, String firstname, String lastname, String
			// password, String username) {
			// userBean.registerUser(new
			// User(20,"mail@mail.com","John","Maz","1234","gmaz"));
			// User(int age, LocalDate birthdate, String email, String firstname, String
			// lastname, String password,
			// String photo, Timestamp registrationDate, String username,
			// List<MovieFavorite> movieFavorites,
			// List<MovieReview> movieReviews, List<ShowFavorite> showFavorites,
			// List<ShowReview> showReviews) {
//			userBean.registerUser(new User(20, LocalDate.now(), "mail@mail.com", "John", "Maz", "12334", null,
//					LocalDate.now(), "gmaz", null, null, null, null));

			User user = userBean.findUserByUsername("gmaz");

			this.addComponent(new Label("Thanks " + name.getValue() + " " + MyUI.get().getUserBean() + ", it works!"));
			this.addComponent(new Label(
					"User: " + user.getFirstname() + " " + user.getLastname() + " " + user.getRegistrationDate()));
		});

		this.addComponents(name, button);
		this.setMargin(true);
		this.setSpacing(true);
	}
}
