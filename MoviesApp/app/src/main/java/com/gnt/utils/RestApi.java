package com.gnt.utils;

/**
 * Created by sissy on 12/5/17.
 */

import com.gnt.appobjects.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/** Interface with all the calls used with Retrofit 2.0 **/
public interface RestApi {

    @GET("user/checktoken")
    Call<Boolean> checkTokenExpired();

    @POST("user/register")
    Call<String> postUser(@Body User user);
}
