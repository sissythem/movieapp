package com.gnt.movies.utilities;

public class APIClientRunnable implements Runnable {
	
	private String url;
	private String result;
	
	
	@Override
	public void run() {
		APIClient apiClient= new APIClient();
		result = apiClient.getResultFromTMDB(url.toString());
	}
	
	
	
	public APIClientRunnable(String url) {
		super();
		this.url = url;
	}



	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	

}
