package com.gnt.movies.GntMovies_web;

import com.gnt.movies.beans.UserBean;
import com.gnt.movies.dto.UserSessionDto;
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
		
//			userBean.registerUser(new User(20, LocalDate.now(), name.getValue()+"@mail.com", "John", "Maz", "12334", null,
//					LocalDate.now(), name.getValue(), null, null, null, null));
//			System.out.println("User added");
			UserSessionDto user = userBean.findUserDtoByUsername("gmaz");

//			this.addComponent(new Label("Thanks " + name.getValue() + " " + MyUI.get().getUserBean() + ", it works!"));
			this.addComponent(new Label("User:"+user.toString()));
			
			MyUI.get().getSchedulerBean().getUpcomingMovies();
		});

		this.addComponents(name, button);
		this.setMargin(true);
		this.setSpacing(true);
	}
}
