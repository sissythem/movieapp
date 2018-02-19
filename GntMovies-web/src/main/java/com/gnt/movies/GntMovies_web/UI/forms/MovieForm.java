package com.gnt.movies.GntMovies_web.UI.forms;

import com.gnt.movies.GntMovies_web.UI.layouts.MovieFormLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MovieForm extends VerticalLayout {
	private MovieFormLayout movieFormLayout;
	
	public MovieForm(MovieFormLayout movieFormLayout) {
		this.movieFormLayout=movieFormLayout;
	}

	public MovieFormLayout getMovieFormLayout() {
		return movieFormLayout;
	}

	public void setMovieFormLayout(MovieFormLayout movieFormLayout) {
		this.movieFormLayout = movieFormLayout;
	}
	
}
