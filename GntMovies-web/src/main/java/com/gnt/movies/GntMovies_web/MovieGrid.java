package com.gnt.movies.GntMovies_web;

import com.gnt.movies.entities.Movie;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.NumberRenderer;

/**
 * Grid of Movies, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class MovieGrid extends Grid<Movie> {
	private static final long serialVersionUID = 1L;

	
	private MovieDataProvider dataProvider = new MovieDataProvider();
	public MovieGrid() {
		setSizeFull();

		addColumn(Movie::getId, new NumberRenderer()).setCaption("Id");
		addColumn(Movie::getOriginalTitle).setCaption("Movie Title");
		setDataProvider(dataProvider);
		// // Format and add " €" to price
		// final DecimalFormat decimalFormat = new DecimalFormat();
		// decimalFormat.setMaximumFractionDigits(2);
		// decimalFormat.setMinimumFractionDigits(2);
		// addColumn(Movie -> decimalFormat.format(Movie.getPrice()) + " €")
		// .setCaption("Price").setComparator((p1, p2) -> {
		// return p1.getPrice().compareTo(p2.getPrice());
		// }).setStyleGenerator(Movie -> "align-right");
		//
		// // Add an traffic light icon in front of availability
		// addColumn(this::htmlFormatAvailability, new HtmlRenderer())
		// .setCaption("Availability").setComparator((p1, p2) -> {
		// return p1.getAvailability().toString()
		// .compareTo(p2.getAvailability().toString());
		// });
		//
		// // Show empty stock as "-"
		// addColumn(Movie -> {
		// if (Movie.getStockCount() == 0) {
		// return "-";
		// }
		// return Integer.toString(Movie.getStockCount());
		// }).setCaption("Stock Count").setComparator((p1, p2) -> {
		// return Integer.compare(p1.getStockCount(), p2.getStockCount());
		// }).setStyleGenerator(Movie -> "align-right");
		//
		// // Show all categories the Movie is in, separated by commas
		// addColumn(this::formatCategories).setCaption("Category").setSortable(false);
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
