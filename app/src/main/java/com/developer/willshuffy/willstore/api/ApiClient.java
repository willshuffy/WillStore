package com.developer.willshuffy.willstore.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static final  String Base_URL = "http://willstore19.000webhostapp.com/";
    //public static final  String Base_URL = "http://192.168.1.11/near_deal_api/";
            private static Retrofit retrofit = null;


            public static Retrofit getClient(){

                if (retrofit==null){
                    retrofit=new Retrofit.Builder()
                            .baseUrl(Base_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
                return retrofit;

            }

}
