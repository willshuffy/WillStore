package com.developer.willshuffy.willstore.api;

import com.developer.willshuffy.willstore.responses.ProductResponse;
import com.developer.willshuffy.willstore.responses.StoreResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("get_store.php")
    Call<StoreResponse> getStoreData(@Query("lat") double lat,
                                     @Query("lng") double lng);

    @GET("get_product.php")
    Call<ProductResponse> getProductData(@Query("store_id") String storeId);

}
