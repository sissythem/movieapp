package com.gnt.movies.utilities;

public class APIClientRunnable implements Runnable {
	
	private String urlApi;
	private String result;
	
	@Override
	public void run() {
		result = APIClient.getResultFromTMDB(urlApi);
	}

	public APIClientRunnable(String urlApi) {
		super();
		this.urlApi = urlApi;
	}

	public String getResult() {
		return result;
	}
	
	
	
	
	
	

}
