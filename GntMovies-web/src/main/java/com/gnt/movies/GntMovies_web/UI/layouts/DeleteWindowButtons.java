package com.gnt.movies.GntMovies_web.UI.layouts;

import com.github.appreciated.material.MaterialTheme;
import com.gnt.movies.GntMovies_web.UI.utils.OnDeleteWindowClickListener;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.GntMovies_web.UI.windows.DeleteWindow;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class DeleteWindowButtons extends HorizontalLayout {
	private DeleteWindow deleteWindow;
	private Button deleteButton;
	private Button cancelButton;
	OnDeleteWindowClickListener listener;

	public DeleteWindowButtons(DeleteWindow deleteWindow, OnDeleteWindowClickListener listener) {
		this.deleteWindow = deleteWindow;
		this.listener = listener;
		setSizeFull();
		setMargin(true);
		initButtons();
		HorizontalLayout buttons = new HorizontalLayout(deleteButton, cancelButton);
		addComponents(buttons);
		setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
	}

	private void initButtons() {
		deleteButton = new Button("Delete");
		deleteButton.setStyleName(MaterialTheme.BUTTON_DANGER);
		cancelButton = new Button("Cancel");
		cancelButton.setStyleName(Utilities.DELETE_BUTTON);
		deleteButton.addClickListener(event -> {
			listener.onClickDelete();
			deleteWindow.close();
		});
		cancelButton.addClickListener(event -> deleteWindow.close());
	}

	public DeleteWindow getDeleteWindow() {
		return deleteWindow;
	}

	public void setDeleteWindow(DeleteWindow deleteWindow) {
		this.deleteWindow = deleteWindow;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}

	public OnDeleteWindowClickListener getListener() {
		return listener;
	}

	public void setListener(OnDeleteWindowClickListener listener) {
		this.listener = listener;
	}
}
