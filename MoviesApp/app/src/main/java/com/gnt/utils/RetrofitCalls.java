package com.gnt.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.gnt.appobjects.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sissy on 12/5/17.
 */

public class RetrofitCalls {
    private boolean flag;
    private String token;
    private ArrayList<User> usersList;
    private User user;

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

    private class postUserHttpRequestTask extends AsyncTask<User, User, Boolean>
    {
        @Override
        protected Boolean doInBackground(User... params){
            RestApi restApi = RetrofitClient.getStringClient().create(RestApi.class);
            Call<Boolean> call = restApi.postUser(params[0]);
            try{
                Response<Boolean> resp = call.execute();
                flag = resp.body();
            } catch (IOException e){
                Log.i("",e.getMessage());
            }
            return flag;
        }
    }

    public Boolean postUser(User user){
        postUserHttpRequestTask postUserTask = new postUserHttpRequestTask();
        postUserTask.execute(user);
        try{
            postUserTask.get();
        } catch (InterruptedException e) {
            Log.i("",e.getMessage());
        } catch (ExecutionException e) {
            Log.i("",e.getMessage());
        }
        return flag;
    }

    private class getUserByUsernameHttpRequestTask extends AsyncTask<String, String, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... params) {
            usersList = new ArrayList<>();
            RestApi restApi = RetrofitClient.getClient(params[0]).create(RestApi.class);
            Call<List<User>> call = restApi.getUserByUsername(params[1]);
            try {
                Response<List<User>> resp = call.execute();
                usersList.addAll(resp.body());
            } catch (IOException e) {
                Log.i("",e.getMessage());
            }
            return usersList;
        }
        @Override
        protected void onPostExecute(ArrayList<User> users) {}
    }

    public ArrayList<User> getUserbyUsername(String token, String username) {
        getUserByUsernameHttpRequestTask getUserByUsername = new getUserByUsernameHttpRequestTask();
        getUserByUsername.execute(token, username);
        try {
            getUserByUsername.get();
        } catch (InterruptedException e) {
            Log.i("",e.getMessage());
        } catch (ExecutionException e) {
            Log.i("",e.getMessage());
        }
        return usersList;
    }

    private class editUserHttpRequestTask extends AsyncTask<Object, Object, String>
    {
        @Override
        protected String doInBackground(Object... params){
            try{
                RestApi restApi = RetrofitClient.getClient((String)params[0]).create(RestApi.class);
                Call<String> call = restApi.editUserById((Integer)params[1],(User)params[2]);
                Response<String> resp = call.execute();
                token = resp.body();
            } catch (IOException e){
                Log.i("",e.getMessage());
            }
            catch (Exception e){
                Log.i("",e.getMessage());
            }
            return token;
        }
    }

    public String editUser(String token, int userId, User user){
        editUserHttpRequestTask editUserTask = new editUserHttpRequestTask();
        editUserTask.execute(token, userId, user);
        try{
            editUserTask.get();
        } catch (InterruptedException e) {
            Log.i("",e.getMessage());
        } catch (ExecutionException e) {
            Log.i("",e.getMessage());
        }
        return this.token;
    }

    private class getUserByEmailHttpRequestTask extends AsyncTask<String, String, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... params) {
            usersList = new ArrayList<>();
            RestApi restApi = RetrofitClient.getClient(params[0]).create(RestApi.class);
            Call<List<User>> call = restApi.getUserByEmail(params[1]);
            try {
                Response<List<User>> resp = call.execute();
                usersList.addAll(resp.body());
            } catch (IOException e) {
                Log.i("",e.getMessage());
            }
            return usersList;
        }
        @Override
        protected void onPostExecute(ArrayList<User> users) {}
    }

    public ArrayList<User> getUserbyEmail(String token, String email){
        getUserByEmailHttpRequestTask getUserByEmail = new getUserByEmailHttpRequestTask();
        getUserByEmail.execute(token, email);
        try {
            getUserByEmail.get();
        } catch (InterruptedException e) {
            Log.i("",e.getMessage());
        } catch (ExecutionException e) {
            Log.i("",e.getMessage());
        }
        return usersList;
    }

    private class deleteUserByIdHttpRequestTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String token="";
            RestApi restApi = RetrofitClient.getClient(params[0]).create(RestApi.class);
            Call<String> call = restApi.deleteUserById(params[1]);
            try {
                Response<String> resp = call.execute();
                token = resp.body();
            }
            catch(IOException e){
                Log.i("",e.getMessage());
            }
            return token;
        }
    }

    public String deleteUserById(String token, String id) {
        deleteUserByIdHttpRequestTask deleteUser = new deleteUserByIdHttpRequestTask();
        deleteUser.execute(token, id);
        try {
            token = deleteUser.get();
        } catch (InterruptedException e) {
            Log.i("",e.getMessage());
        } catch (ExecutionException e) {
            Log.i("",e.getMessage());
        }
        return token;
    }

    private class getUserByIdHttpRequestTask extends AsyncTask<String, String, User> {
        @Override
        protected User doInBackground(String... params) {
            user = new User();
            RestApi restApi = RetrofitClient.getClient(params[0]).create(RestApi.class);
            Call<User> call = restApi.getUserById(params[1]);
            try {
                Response<User> resp = call.execute();
                user = resp.body();
            } catch (IOException e) {
                Log.i("",e.getMessage());
            }
            return user;
        }
        @Override
        protected void onPostExecute(User users) {
        }
    }

    public User getUserbyId(String token, String id) {
        getUserByIdHttpRequestTask userById = new getUserByIdHttpRequestTask();
        userById.execute(token, id);
        try {
            userById.get();
        } catch (InterruptedException e) {
            Log.i("",e.getMessage());
        } catch (ExecutionException e) {
            Log.i("",e.getMessage());
        }
        return user;
    }
}
