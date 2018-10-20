package com.developer.willshuffy.willstore.api;

import com.developer.willshuffy.willstore.responses.StoreResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {

    @GET("get_store.php")
    Call<StoreResponse> getStoreData();



}
