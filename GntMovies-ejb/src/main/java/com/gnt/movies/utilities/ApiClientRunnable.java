package com.gnt.movies.utilities;

public class ApiClientRunnable implements Runnable {
	
	private String urlApi;
	private String result;
	
	@Override
	public void run() {
		result = ApiClient.getResultFromTMDB(urlApi);
	}

	public ApiClientRunnable(String urlApi) {
		super();
		this.urlApi = urlApi;
	}

	public String getResult() {
		return result;
	}
	
	
	
	
	
	

}
