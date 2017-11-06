package com.gnt.movies.utilities;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIClient {

	public String getResultFromTMDB(String url) 
	{
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url(url)
		  .get()
		  .build();

		Response response;
		try {
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
//	public resultToGSON(String url) {
//		String result = getResultFromTMDB(url);
//	    try {
//	        Gson jsonObject = new JSONObject(result);
//	        
//	        } catch (JSONException e) {
//	        }
//	}
}
