package com.gnt.utils;

/**
 * Created by sissy on 12/5/17.
 */

import retrofit2.Call;
import retrofit2.http.GET;

/** Interface with all the calls used with Retrofit 2.0 **/
public interface RestApi {

    @GET("user/checktoken")
    Call<Boolean> checkTokenExpired();
}
