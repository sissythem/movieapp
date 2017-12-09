package com.gnt.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.gnt.appobjects.User;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sissy on 12/5/17.
 */

public class RetrofitCalls {
    private boolean flag;
    private String token;

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

    private class postUserHttpRequestTask extends AsyncTask<User, User, String>
    {
        @Override
        protected String doInBackground(User... params){
            RestApi restApi = RetrofitClient.getStringClient().create(RestApi.class);
            Call<String> call = restApi.postUser(params[0]);
            try{
                Response<String> resp = call.execute();
                token = resp.body();
            } catch (IOException e){
                Log.i("",e.getMessage());
            }
            return token;
        }
    }

    public String postUser(User user){
        postUserHttpRequestTask postUserTask = new postUserHttpRequestTask();
        postUserTask.execute(user);
        try{
            postUserTask.get();
        } catch (InterruptedException e) {
            Log.i("",e.getMessage());
        } catch (ExecutionException e) {
            Log.i("",e.getMessage());
        }
        return token;
    }
}
