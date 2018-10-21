package com.developer.willshuffy.willstore.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.willshuffy.willstore.ProductActivity;
import com.developer.willshuffy.willstore.R;
import com.developer.willshuffy.willstore.adapters.ProductItemAdapter;
import com.developer.willshuffy.willstore.api.ApiClient;
import com.developer.willshuffy.willstore.api.ApiEndPoint;
import com.developer.willshuffy.willstore.responses.Product;
import com.developer.willshuffy.willstore.responses.ProductResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment {

    private String mStoreId;
    private List<Product> mProductList;
    private ProductItemAdapter mAdapter;


    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_product_list, container, false);

        //menangkap storeId

        mStoreId=getArguments().getString(ProductActivity.KEY_STORE_ID);
        mProductList=new ArrayList<>();
        mAdapter=new ProductItemAdapter(getActivity(), mProductList);


        RecyclerView recyclerView=view.findViewById(R.id.rv_product);
        GridLayoutManager gridLayoutManager=
                new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);


        // Inflate the layout for this fragment
        loadProducts();
        return view;
    }

    private void loadProducts(){
        ApiEndPoint apiEndPoint = ApiClient.getClient().create(ApiEndPoint.class);
        Call<ProductResponse> call = apiEndPoint.getProductData(mStoreId);

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse productResponse = response.body();

                if (productResponse != null){
                    if (productResponse.getSuccess()){
                        Log.d("StoreListFragment", "Jumlah store:" + productResponse.getProduct().size());
                        mProductList.addAll(productResponse.getProduct());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
                else {
                    Log.d("StoreListFragment", "response is null");
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });

    }

}
