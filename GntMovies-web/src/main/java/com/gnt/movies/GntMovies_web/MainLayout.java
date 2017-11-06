package com.gnt.movies.GntMovies_web;

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
			
			this.addComponent(new Label("Thanks " + name.getValue() + " " + ", it works!"));
			this.addComponent(new Label("Thanks " + name.getValue() + " " + MyUI.get().getUserBean() + ", it works!"));
		});

		this.addComponents(name, button);
		this.setMargin(true);
		this.setSpacing(true);
	}
}
