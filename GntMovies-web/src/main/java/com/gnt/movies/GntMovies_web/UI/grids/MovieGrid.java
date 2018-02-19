package com.gnt.movies.GntMovies_web.UI.grids;

import com.gnt.movies.GntMovies_web.UI.forms.MovieForm;
import com.gnt.movies.GntMovies_web.UI.panels.GridFormPanel;
import com.gnt.movies.GntMovies_web.UI.providers.MovieProvider;
import com.gnt.movies.GntMovies_web.UI.utils.OnDiscardWindowClickListener;
import com.gnt.movies.GntMovies_web.UI.windows.DiscardChangesWindow;
import com.gnt.movies.entities.Movie;
import com.vaadin.ui.Grid;

@SuppressWarnings("serial")
public class MovieGrid extends Grid<Movie> {
	private GridFormPanel gridFormPanel;
	private MovieProvider movieProvider = new MovieProvider();
	private Movie selectedMovie;
	
	public MovieGrid(GridFormPanel gridFormPanel) {
		super(Movie.class);
		this.gridFormPanel=gridFormPanel;
		setSizeFull();
		initGrid();
	}
	
	private void initGrid() {
		setSelectionMode(SelectionMode.SINGLE);
		setColumns("title", "overview", "homepage", "status", "originalLanguage", "adult");
		setDataProvider(movieProvider);
		addMovieGridClickListener();
	}
	
	private void addMovieGridClickListener() {
		addItemClickListener(event -> {
			if (checkIfIsFieldChanged()) {
				showDiscardChangesWindow(event);
			} else {
				setSelectedMovie(event.getItem());
				Movie selectedMovie = event.getItem();
				clearForm();
				fillForm(selectedMovie);
				gridFormPanel.getMovieFormLayout().getMovieForm().setFieldChanged(false);
			}
		});
	}
	
	private void clearForm() {
		gridFormPanel.getUserFormLayout().getUserForm().clearFormFields();
	}

	public void fillForm(Movie selectedSelectedMovie) {
		MovieForm movieForm = gridFormPanel.getMovieFormLayout().getMovieForm();
		movieForm.getTitletf().setValue(selectedMovie.getTitle());
		movieForm.getOverviewtf().setValue(selectedMovie.getOverview());
		movieForm.getHomepagetf().setValue(selectedMovie.getHomepage());
		movieForm.getStatustf().setValue(selectedMovie.getStatus());
		movieForm.getOriginalLanguagetf().setValue(selectedMovie.getOriginalLanguage());
		movieForm.getAdulttf().setValue(selectedMovie.getAdult().toString());
		gridFormPanel.getHsplit().setSecondComponent(gridFormPanel.getUserFormLayout());
	}

	private boolean checkIfIsFieldChanged() {
		return gridFormPanel.getMovieFormLayout().getMovieForm().isFieldChanged();
	}

	private void showDiscardChangesWindow(ItemClick<Movie> event) {
		new DiscardChangesWindow(new OnDiscardWindowClickListener() {
			@Override
			public void onClickDiscard() {
				setSelectedMovie(event.getItem());
				Movie selectedMovie = event.getItem();
				clearForm();
				fillForm(selectedMovie);
				gridFormPanel.getMovieFormLayout().getMovieForm().setFieldChanged(false);
			}

			@Override
			public void onClickCancel() {
				select(getSelectedMovie());
			}
		}).show();
	}

	public GridFormPanel getGridFormPanel() {
		return gridFormPanel;
	}
	public void setGridFormPanel(GridFormPanel gridFormPanel) {
		this.gridFormPanel = gridFormPanel;
	}
	public MovieProvider getMovieProvider() {
		return movieProvider;
	}
	public void setMovieProvider(MovieProvider movieProvider) {
		this.movieProvider = movieProvider;
	}
	public Movie getSelectedMovie() {
		return selectedMovie;
	}
	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

}
