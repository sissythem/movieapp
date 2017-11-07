package com.gnt.movies.utilities;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIClient {

	public static String getResultFromTMDB(String url) 
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
}
