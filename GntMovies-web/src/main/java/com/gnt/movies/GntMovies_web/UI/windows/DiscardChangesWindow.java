package com.gnt.movies.GntMovies_web.UI.windows;

import com.gnt.movies.GntMovies_web.UI.layouts.DiscardWindowButtons;
import com.gnt.movies.GntMovies_web.UI.utils.OnDiscardWindowClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class DiscardChangesWindow extends Window {
	private DiscardWindowButtons windowButtons;
	OnDiscardWindowClickListener listener;
	
	public DiscardChangesWindow(OnDiscardWindowClickListener listener) {
		super("Please confirm");
		initWindow();
		this.listener = listener;
		VerticalLayout windowContent = createWindowContent();
		this.setContent(windowContent);
	}
	
	private void initWindow() {
		setWidth(30, Unit.PERCENTAGE);
		setHeight(21, Unit.PERCENTAGE);
		setModal(true);
		setResizable(false);
	}
	
	private VerticalLayout createWindowContent() {
		VerticalLayout windowContent = new VerticalLayout();
		windowContent.setMargin(true);
		windowContent.setSpacing(true);
		
		Label message = new Label("You have unsaved changes that will be discarded if you navigate away.");
		message.setStyleName("windowtext");
		windowButtons = new DiscardWindowButtons(this, listener);
		
		windowContent.addComponents(message, windowButtons);
		
		return windowContent;
	}
	
	public void show() {
		UI.getCurrent().addWindow(this);
	}

	public DiscardWindowButtons getWindowButtons() {
		return windowButtons;
	}

	public void setWindowButtons(DiscardWindowButtons windowButtons) {
		this.windowButtons = windowButtons;
	}
}
