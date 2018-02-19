package com.gnt.movies.GntMovies_web.UI.providers;

import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

import com.gnt.movies.GntMovies_web.UI.MyUI;
import com.gnt.movies.entities.Show;
import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.Query;

public class ShowProvider extends AbstractDataProvider<Show, String>{
	private static final long serialVersionUID = 1L;
	private String filterText = "";

	@Override
	public boolean isInMemory() {
		return true;
	}

	@Override
	public int size(Query<Show, String> query) {
		return (int) fetch(query).count();
	}

	@Override
	public Stream<Show> fetch(Query<Show, String> query) {
		if (filterText.isEmpty()) {
			return MyUI.get().getShowBean().getShows().stream();
		}
		return MyUI.get().getShowBean().getShows().stream().filter(show -> 
				passesFilter(show.getName(), filterText)
				|| passesFilter(show.getStatus(), filterText)
				|| passesFilter(show.getOverview(), filterText));
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
