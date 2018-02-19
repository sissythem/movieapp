package com.gnt.movies.GntMovies_web.UI.windows;

import com.gnt.movies.GntMovies_web.UI.layouts.DeleteWindowButtons;
import com.gnt.movies.GntMovies_web.UI.utils.OnDeleteWindowClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class DeleteWindow extends Window {
	private DeleteWindowButtons deleteWindowButtons;
	OnDeleteWindowClickListener listener;
	
	public DeleteWindow(OnDeleteWindowClickListener listener) {
		super("Please confirm");
		initWindow();
		this.listener = listener;
		VerticalLayout windowContent = createWindowContent();
		setContent(windowContent);
	}
	
	private void initWindow() {
		setWidth(30, Unit.PERCENTAGE);
		setHeight(21, Unit.PERCENTAGE);
		setScrollTop(0);
		setModal(true);
		setResizable(false);
	}
	
	private VerticalLayout createWindowContent() {
		VerticalLayout windowContent = new VerticalLayout();
		windowContent.setMargin(true);
		windowContent.setSpacing(true);
		
		Label message = new Label("Are you sure you want to delete this item?");
		message.setStyleName("windowtext");
		deleteWindowButtons = new DeleteWindowButtons(this, listener);
		windowContent.addComponents(message, deleteWindowButtons);
		return windowContent;
	}
	
	public void show() {
		UI.getCurrent().addWindow(this);
	}

	public DeleteWindowButtons getDeleteWindowButtons() {
		return deleteWindowButtons;
	}

	public void setDeleteWindowButtons(DeleteWindowButtons deleteWindowButtons) {
		this.deleteWindowButtons = deleteWindowButtons;
	}

	public OnDeleteWindowClickListener getListener() {
		return listener;
	}

	public void setListener(OnDeleteWindowClickListener listener) {
		this.listener = listener;
	}
}
