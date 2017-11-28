package com.gnt.movies.GntMovies_web;

import java.util.Locale;
import java.util.Objects;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.inject.Inject;

import com.gnt.movies.beans.MovieBean;
import com.gnt.movies.entities.Movie;
import com.vaadin.cdi.CDIUI;
import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.Query;

public class MovieDataProvider extends AbstractDataProvider<Movie, String> {
	private static final long serialVersionUID = 1L;
	
	/** Text filter that can be changed separately. */
	private String filterText = "";

	/**
	 * Store given movie to the backing data service.
	 * 
	 * @param movie
	 *            the updated or new movie
	 */
	// public void save(Movie movie) {
	// boolean newMovie = movie.getId() == -1;
	//
	// DataService.get().updateMovie(movie);
	// if (newMovie) {
	// refreshAll();
	// } else {
	// refreshItem(movie);
	// }
	// }

	/**
	 * Delete given movie from the backing data service.
	 * 
	 * @param movie
	 *            the movie to be deleted
	 */
	// public void delete(Movie movie) {
	// DataService.get().deleteMovie(movie.getId());
	// refreshAll();
	// }

	/**
	 * Sets the filter to use for the this data provider and refreshes data.
	 * <p>
	 * Filter is compared for movie name, availability and category.
	 * 
	 * @param filterText
	 *            the text to filter by, never null
	 */
	public void setFilter(String filterText) {
		Objects.requireNonNull(filterText, "Filter text cannot be null");
		if (Objects.equals(this.filterText, filterText.trim())) {
			return;
		}
		this.filterText = filterText.trim();

		refreshAll();
	}

	@Override
	public Integer getId(Movie movie) {
		Objects.requireNonNull(movie, "Cannot provide an id for a null movie.");

		return movie.getId();
	}

	@Override
	public boolean isInMemory() {
		return true;
	}

	@Override
	public int size(Query<Movie, String> t) {
		return (int) fetch(t).count();
	}

	@Override
    public Stream<Movie> fetch(Query<Movie, String> query) {
        if (filterText.isEmpty()) {
            return MyUI.get().getMovieBean().getMovies().stream();
        }
        return MyUI.get().getMovieBean().getMovies().stream().filter(
                movie -> passesFilter(movie.getOriginalTitle(), filterText)
                        || passesFilter(movie.getTitle(), filterText)
                        || passesFilter(movie.getGenres(), filterText));
    	
//    	return movieBean.getMovies().stream();
    }

	private boolean passesFilter(Object object, String filterText) {
		return object != null && object.toString().toLowerCase(Locale.ENGLISH).contains(filterText);
	}
}
