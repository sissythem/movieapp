package com.gnt.movies.GntMovies_web.UI.forms;

import com.gnt.movies.GntMovies_web.UI.layouts.ShowFormLayout;
import com.gnt.movies.GntMovies_web.UI.utils.Utilities;
import com.gnt.movies.entities.Show;
import com.vaadin.data.Binder;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ShowForm extends VerticalLayout {
	private static String FIELD_WIDTH = "95%";
	private final Binder<Show> binder = new Binder<>();
	private ShowFormLayout showFormLayout;
	private boolean isFieldChanged = false;
	private TextField nametf;
	private TextField overviewtf;
	private TextField homepagetf;
	private TextField statustf;
	private TextField originalLanguagetf;
	private TextField typetf;
	
	public ShowForm(ShowFormLayout showFormLayout) {
		this.showFormLayout=showFormLayout;
		setSizeFull();
		initFields();
		addPlaceHolders();
		addValidation();
		addComponents(nametf, overviewtf, homepagetf, statustf, originalLanguagetf, typetf);
		updateFields();
	}
	
	private void initFields() {
		nametf = new TextField(Utilities.NAME_FIELD);
		overviewtf = new TextField(Utilities.LAST_NAME_FIELD);
		homepagetf = new TextField(Utilities.USERNAME_FIELD);
		statustf = new TextField(Utilities.EMAIL_FIELD);
		originalLanguagetf = new TextField(Utilities.PASSWORD_FIELD);
		typetf = new TextField(Utilities.AGE_FIELD);
	}

	private void addPlaceHolders() {
		nametf.setPlaceholder(Utilities.NAME_FIELD);
		overviewtf.setPlaceholder(Utilities.LAST_NAME_FIELD);
		homepagetf.setPlaceholder(Utilities.USERNAME_FIELD);
		statustf.setPlaceholder(Utilities.EMAIL_FIELD);
		originalLanguagetf.setPlaceholder(Utilities.PASSWORD_FIELD);
		typetf.setPlaceholder(Utilities.AGE_FIELD);
	}

	private void addValidation() {
		binder.forField(nametf).asRequired("Name is required").bind(Show::getName, Show::setName);
		binder.forField(overviewtf).asRequired("Overview is required").bind(Show::getOverview, Show::setOverview);
		binder.forField(homepagetf).asRequired("Homepage is required").bind(Show::getHomepage, Show::setHomepage);
		binder.forField(statustf).asRequired("Status is required").bind(Show::getStatus, Show::setStatus);
	}

	private void updateFields() {
		setTextFieldWidth();
		setRequiredFields();
		addValueChangeListener();
	}

	private void setTextFieldWidth() {
		nametf.setWidth(FIELD_WIDTH);
		overviewtf.setWidth(FIELD_WIDTH);
		homepagetf.setWidth(FIELD_WIDTH);
		statustf.setWidth(FIELD_WIDTH);
		originalLanguagetf.setWidth(FIELD_WIDTH);
		typetf.setWidth(FIELD_WIDTH);
	}

	private void addValueChangeListener() {
		nametf.addValueChangeListener(event -> setFieldChanged(true));
		overviewtf.addValueChangeListener(event -> setFieldChanged(true));
		homepagetf.addValueChangeListener(event -> setFieldChanged(true));
		statustf.addValueChangeListener(event -> setFieldChanged(true));
		originalLanguagetf.addValueChangeListener(event -> setFieldChanged(true));
		typetf.addValueChangeListener(event -> setFieldChanged(true));
	}

	private void setRequiredFields() {
		nametf.setRequiredIndicatorVisible(true);
		overviewtf.setRequiredIndicatorVisible(true);
		homepagetf.setRequiredIndicatorVisible(true);
		statustf.setRequiredIndicatorVisible(true);
	}
	
	public void clearFormFields() {
		nametf.clear();
		overviewtf.clear();
		homepagetf.clear();
		statustf.clear();
		originalLanguagetf.clear();
		typetf.clear();
	}

	public ShowFormLayout getShowFormLayout() {
		return showFormLayout;
	}

	public void setSHowFormLayout(ShowFormLayout showFormLayout) {
		this.showFormLayout = showFormLayout;
	}

	public boolean isFieldChanged() {
		return isFieldChanged;
	}

	public void setFieldChanged(boolean isFieldChanged) {
		this.isFieldChanged = isFieldChanged;
	}

	public Binder<Show> getBinder() {
		return binder;
	}

	public TextField getNametf() {
		return nametf;
	}

	public void setNametf(TextField nametf) {
		this.nametf = nametf;
	}

	public TextField getOverviewtf() {
		return overviewtf;
	}

	public void setOverviewtf(TextField overviewtf) {
		this.overviewtf = overviewtf;
	}

	public TextField getHomepagetf() {
		return homepagetf;
	}

	public void setHomepagetf(TextField homepagetf) {
		this.homepagetf = homepagetf;
	}

	public TextField getStatustf() {
		return statustf;
	}

	public void setStatustf(TextField statustf) {
		this.statustf = statustf;
	}

	public TextField getOriginalLanguagetf() {
		return originalLanguagetf;
	}

	public void setOriginalLanguagetf(TextField originalLanguagetf) {
		this.originalLanguagetf = originalLanguagetf;
	}

	public TextField getTypetf() {
		return typetf;
	}

	public void setTypetf(TextField typetf) {
		this.typetf = typetf;
	}

	public void setShowFormLayout(ShowFormLayout showFormLayout) {
		this.showFormLayout = showFormLayout;
	}
	
}
