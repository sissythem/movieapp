package com.gnt.movies.GntMovies_web.UI.layouts;

import com.github.appreciated.material.MaterialTheme;
import com.gnt.movies.GntMovies_web.UI.utils.OnDiscardWindowClickListener;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.GntMovies_web.UI.windows.DiscardChangesWindow;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class DiscardWindowButtons extends HorizontalLayout{
	private DiscardChangesWindow discardChangesWindow;
	private Button discardButton;
	private Button cancelButton;
	OnDiscardWindowClickListener listener;
	
	public DiscardWindowButtons(DiscardChangesWindow discardChangesWindow, OnDiscardWindowClickListener listener) {
		this.discardChangesWindow = discardChangesWindow;
		setSizeFull();
		setMargin(true);
		initButtons();
		HorizontalLayout buttons = new HorizontalLayout(discardButton, cancelButton);
		addComponents(buttons);
		setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
		this.listener = listener;
	}
	
	private void initButtons() {
		discardButton = new Button("Discard Changes");
		discardButton.setStyleName(MaterialTheme.BUTTON_DANGER);
		cancelButton = new Button("Cancel");
		cancelButton.setStyleName(Utilities.DELETE_BUTTON);
		
		discardButton.addClickListener(event->{
			listener.onClickDiscard();
			discardChangesWindow.close();
			});
		cancelButton.addClickListener(event->{
			listener.onClickCancel();
			discardChangesWindow.close();});
	}

	public DiscardChangesWindow getDiscardChangesWindow() {
		return discardChangesWindow;
	}

	public void setDiscardChangesWindow(DiscardChangesWindow discardChangesWindow) {
		this.discardChangesWindow = discardChangesWindow;
	}

	public Button getDiscardButton() {
		return discardButton;
	}

	public void setDiscardButton(Button discardButton) {
		this.discardButton = discardButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}
}
