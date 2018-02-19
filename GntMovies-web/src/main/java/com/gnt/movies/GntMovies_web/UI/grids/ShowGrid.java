package com.gnt.movies.GntMovies_web.UI.grids;

import com.gnt.movies.GntMovies_web.UI.forms.ShowForm;
import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.gnt.movies.GntMovies_web.UI.providers.ShowProvider;
import com.gnt.movies.GntMovies_web.UI.utils.OnDiscardWindowClickListener;
import com.gnt.movies.GntMovies_web.UI.windows.DiscardChangesWindow;
import com.gnt.movies.entities.Show;
import com.vaadin.ui.Grid;

@SuppressWarnings("serial")
public class ShowGrid extends Grid<Show> {
	private GridFormPanel gridFormPanel;
	private ShowProvider showProvider = new ShowProvider();
	private Show selectedShow;
	
	public ShowGrid(GridFormPanel gridFormPanel) {
		super(Show.class);
		this.gridFormPanel=gridFormPanel;
		setSizeFull();
		initGrid();
	}
	
	private void initGrid() {
		setSelectionMode(SelectionMode.SINGLE);
		setColumns("name", "overview", "homepage", "status", "originalLanguage", "type");
		setDataProvider(showProvider);
		addShowGridClickListener();
	}
	
	private void addShowGridClickListener() {
		addItemClickListener(event -> {
			if (checkIfIsFieldChanged()) {
				showDiscardChangesWindow(event);
			} else {
				setSelectedShow(event.getItem());
				Show selectedShow = event.getItem();
				clearForm();
				fillForm(selectedShow);
				gridFormPanel.getShowFormLayout().getShowForm().setFieldChanged(false);
			}
		});
	}
	
	private void clearForm() {
		gridFormPanel.getShowFormLayout().getShowForm().clearFormFields();
	}

	public void fillForm(Show selectedSelectedShow) {
		ShowForm showForm = gridFormPanel.getShowFormLayout().getShowForm();
		showForm.getNametf().setValue(selectedSelectedShow.getName());
		showForm.getOverviewtf().setValue(selectedSelectedShow.getOverview());
		showForm.getHomepagetf().setValue(selectedSelectedShow.getHomepage());
		showForm.getStatustf().setValue(selectedSelectedShow.getStatus());
		showForm.getOriginalLanguagetf().setValue(selectedSelectedShow.getOriginalLanguage());
		showForm.getTypetf().setValue(selectedSelectedShow.getType().toString());
		gridFormPanel.getHsplit().setSecondComponent(gridFormPanel.getUserFormLayout());
	}

	private boolean checkIfIsFieldChanged() {
		return gridFormPanel.getShowFormLayout().getShowForm().isFieldChanged();
	}

	private void showDiscardChangesWindow(ItemClick<Show> event) {
		new DiscardChangesWindow(new OnDiscardWindowClickListener() {
			@Override
			public void onClickDiscard() {
				setSelectedShow(event.getItem());
				Show selectedShow = event.getItem();
				clearForm();
				fillForm(selectedShow);
				gridFormPanel.getShowFormLayout().getShowForm().setFieldChanged(false);
			}

			@Override
			public void onClickCancel() {
				select(getSelectedShow());
			}
		}).show();
	}
	
	public GridFormPanel getGridFormPanel() {
		return gridFormPanel;
	}
	
	public void setGridFormPanel(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}
	
	public ShowProvider getShowProvider() {
		return showProvider;
	}
	
	public void setShowProvider(ShowProvider showProvider) {
		this.showProvider = showProvider;
	}
	
	public Show getSelectedShow() {
		return selectedShow;
	}
	
	public void setSelectedShow(Show selectedShow) {
		this.selectedShow = selectedShow;
	}
	
}
