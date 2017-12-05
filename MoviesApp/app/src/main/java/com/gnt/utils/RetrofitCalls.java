package com.gnt.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sissy on 12/5/17.
 */

public class RetrofitCalls {
    private boolean flag;

    /** Call to check if token is still valid **/
    private class checkTokenHttpRequestTask extends AsyncTask<String, String, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            RestApi restApi = RetrofitClient.getClient(params[0]).create(RestApi.class);
            Call<Boolean> call = restApi.checkTokenExpired();
            try {
                Response<Boolean> resp = call.execute();
                flag = resp.body();
            } catch(IOException e){
                Log.i("",e.getMessage());
            }
            return flag;
        }
    }

    public Boolean isTokenExpired(String token) {
        checkTokenHttpRequestTask checktoken = new checkTokenHttpRequestTask();
        checktoken.execute(token);
        try{
            checktoken.get();
        } catch (InterruptedException e) {
            Log.i("",e.getMessage());
        } catch (ExecutionException e) {
            Log.i("",e.getMessage());
        }
        return flag;
    }
}
