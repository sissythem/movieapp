package com.gnt.movies.GntMovies_web.UI.providers;

import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

import com.gnt.movies.GntMovies_web.UI.MyUI;
import com.gnt.movies.entities.Movie;
import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.Query;

public class MovieProvider extends AbstractDataProvider<Movie, String>{
	private static final long serialVersionUID = 1L;
	
	private String filterText = "";

	@Override
	public boolean isInMemory() {
		return true;
	}

	@Override
	public int size(Query<Movie, String> query) {
		return (int) fetch(query).count();
	}

	@Override
	public Stream<Movie> fetch(Query<Movie, String> query) {
		if (filterText.isEmpty()) {
			return MyUI.get().getMovieBean().getMovies().stream();
		}
		return MyUI.get().getMovieBean().getMovies().stream().filter(movie -> 
				passesFilter(movie.getTitle(), filterText)
				|| passesFilter(movie.getStatus(), filterText)
				|| passesFilter(movie.getOverview(), filterText));
	}
	
	private boolean passesFilter(Object object, String filterText) {
		return object != null && 
				object.toString().toLowerCase(Locale.ENGLISH).contains(filterText);
	}
	
	public void setFilter(String filterText) {
		Objects.requireNonNull(filterText, "Filter text cannot be null");
		if (Objects.equals(this.filterText, filterText.trim())) {
			return;
		}
		this.filterText = filterText.trim();

		refreshAll();
	}
}
