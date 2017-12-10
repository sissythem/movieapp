package com.gnt.utils;

/**
 * Created by sissy on 12/5/17.
 */

import com.gnt.appobjects.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/** Interface with all the calls used with Retrofit 2.0 **/
public interface RestApi {

    @GET("user/checktoken")
    Call<Boolean> checkTokenExpired();

    @POST("user/register")
    Call<Boolean> postUser(@Body User user);

    @GET("user/{userId}")
    Call<User> getUserById(@Path("userId") String userId);

    @GET("user/username")
    Call<List<User>> getUserByUsername(@Query("username") String username);

    @GET("user/email")
    Call<List<User>> getUserByEmail(@Query("email") String email);

    @GET("users/login")
    Call<String> getLoginUser(@Query("username") String username, @Query("password") String password);

    @PUT("user/put/{id}")
    Call<String> editUserById(@Path("id") int id, @Body User user);

    @DELETE("user/delete/{id}")
    Call<String> deleteUserById(@Path("id") String id);
}
