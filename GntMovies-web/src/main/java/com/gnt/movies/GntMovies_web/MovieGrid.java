package com.gnt.movies.GntMovies_web;

import com.gnt.movies.dto.MovieListItemDto;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.NumberRenderer;

/**
 * Grid of Movies, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class MovieGrid extends Grid<MovieListItemDto> {
	private static final long serialVersionUID = 1L;

	

	public MovieGrid() {
		setSizeFull();

		addColumn(MovieListItemDto::getId, new NumberRenderer()).setCaption("Id");
		addColumn(MovieListItemDto::getOriginalTitle).setCaption("Movie Title");
	
	}
			
	//
	// public Movie getSelectedRow() {
	// return asSingleSelect().getValue();
	// }
	//
	// public void refresh(Movie Movie) {
	// getDataCommunicator().refresh(Movie);
	// }
	//
	// private String htmlFormatAvailability(Movie Movie) {
	// Availability availability = Movie.getAvailability();
	// String text = availability.toString();
	//
	// String color = "";
	// switch (availability) {
	// case AVAILABLE:
	// color = "#2dd085";
	// break;
	// case COMING:
	// color = "#ffc66e";
	// break;
	// case DISCONTINUED:
	// color = "#f54993";
	// break;
	// default:
	// break;
	// }
	//
	// String iconCode = "<span class=\"v-icon\" style=\"font-family: "
	// + VaadinIcons.CIRCLE.getFontFamily() + ";color:" + color
	// + "\">&#x"
	// + Integer.toHexString(VaadinIcons.CIRCLE.getCodepoint())
	// + ";</span>";
	//
	// return iconCode + " " + text;
	// }
	//
	// private String formatCategories(Movie Movie) {
	// if (Movie.getCategory() == null || Movie.getCategory().isEmpty()) {
	// return "";
	// }
	// return Movie.getCategory().stream()
	// .sorted(Comparator.comparing(Category::getId))
	// .map(Category::getName).collect(Collectors.joining(", "));
	// }
}
