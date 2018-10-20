package com.developer.willshuffy.willstore.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.willshuffy.willstore.R;
import com.developer.willshuffy.willstore.adapters.StoreItemAdapter;
import com.developer.willshuffy.willstore.api.ApiClient;
import com.developer.willshuffy.willstore.api.ApiEndPoint;
import com.developer.willshuffy.willstore.responses.Store;
import com.developer.willshuffy.willstore.responses.StoreResponse;
import com.developer.willshuffy.willstore.utils.ConnectivityUtil;
import com.developer.willshuffy.willstore.utils.PopupUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreListFragment extends Fragment {

    public static  final  String KEY_LAT = "lat";
    public static  final  String KEY_LNG = "lng";
    private StoreItemAdapter mAdapter;
    private List<Store> mStoreList;


    public StoreListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_store);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mStoreList = new ArrayList<>();
        mAdapter = new StoreItemAdapter(getActivity(), mStoreList);
        //mAdapter.setListener(this);
        recyclerView.setAdapter(mAdapter);

        if(ConnectivityUtil.isConnected(getActivity())){
            loadStores();
        }
        else{
            PopupUtil.showMsg(getActivity(), "No Internet connection", PopupUtil.SHORT);
        }

        return view;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_store_list, container, false);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.store_list_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // copy paste form pastebin https://pastebin.com/xEFfGLmi
    private void loadStores(){
        PopupUtil.showLoading(getActivity(), "", "Loading stores....");

        ApiEndPoint apiEndPoint = ApiClient.getClient().create(ApiEndPoint.class);
        Call<StoreResponse> call = apiEndPoint.getStoreData();

        call.enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                PopupUtil.dismissDialog();
                StoreResponse storeResponse = response.body();

                if (storeResponse != null){
                    if (storeResponse.getSuccess()){
                        Log.d("StoreListFragment", "Jumlah store:" + storeResponse.getStore().size());

                        for (int i=0; i<storeResponse.getStore().size();i++){

                            Store store=storeResponse.getStore().get(i);
                            mStoreList.add(store);
                        }
                        mAdapter.notifyDataSetChanged();

                    }
                }
                else {
                    Log.d("StoreListFragment", "response is null");
                }
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                PopupUtil.dismissDialog();
                Log.d("PEsan "," : "+t.getMessage());

            }
        });

    }

}
