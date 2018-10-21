package com.developer.willshuffy.willstore.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.developer.willshuffy.willstore.MapsActivity;
import com.developer.willshuffy.willstore.ProductActivity;
import com.developer.willshuffy.willstore.R;
import com.developer.willshuffy.willstore.adapters.StoreItemAdapter;
import com.developer.willshuffy.willstore.api.ApiClient;
import com.developer.willshuffy.willstore.api.ApiEndPoint;
import com.developer.willshuffy.willstore.responses.Store;
import com.developer.willshuffy.willstore.responses.StoreResponse;
import com.developer.willshuffy.willstore.utils.ConnectivityUtil;
import com.developer.willshuffy.willstore.utils.PopupUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreListFragment extends Fragment implements StoreItemAdapter.OnItemClickListener {

    public static  final  String KEY_LAT = "lat";
    public static  final  String KEY_LNG = "lng";
    private StoreItemAdapter mAdapter;
    private double mLat, mLng;
    private List<Store> mStoreList;


    public StoreListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        //untuk menampilakn option menu
        setHasOptionsMenu(true);
        //ambil parameter yg dikirim
        Bundle argumennt = getArguments();

        if (argumennt != null){
            mLat = argumennt.getDouble(KEY_LAT);
            mLng = argumennt.getDouble(KEY_LNG);
        }

        RecyclerView recyclerView = view.findViewById(R.id.rv_store);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mStoreList = new ArrayList<>();
        mAdapter = new StoreItemAdapter(getActivity(), mStoreList);
        mAdapter.setListener(this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.show_map) {
            toMapsActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void toMapsActivity(){

        //masukan data list ke format Gson
        Gson gson=new Gson();
        Type objectList=new  TypeToken<List<Store>>(){}.getType();

        //Ubah ke format String
        String list=gson.toJson(mStoreList,objectList);


        Intent intent = new Intent(getActivity(), MapsActivity.class);
        intent.putExtra(KEY_LAT,mLat);
        intent.putExtra(KEY_LNG,mLng);
        intent.putExtra(MapsActivity.store_key,list);

        startActivity(intent);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.store_list_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }




    // copy paste form pastebin https://pastebin.com/xEFfGLmi
    private void loadStores(){
        PopupUtil.showLoading(getActivity(), "", "Loading stores....");
        System.out.println(" Lat : "+mLat+" Lng : "+mLng);
        ApiEndPoint apiEndPoint = ApiClient.getClient().create(ApiEndPoint.class);
        Call<StoreResponse> call = apiEndPoint.getStoreData(mLat,mLng);

        call.enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                PopupUtil.dismissDialog();
                StoreResponse storeResponse = response.body();

                if (storeResponse != null){
                    if (storeResponse.getSuccess()){
                        Log.d("StoreListFragment", "Jumlah store:" + storeResponse.getStore().toString());

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
                Log.d("Pesan "," : "+t.getMessage());

            }
        });

    }


    @Override
    public void onItemClick(String storeId) {
        Intent intent=new Intent(getActivity(), ProductActivity.class);
        intent.putExtra(ProductActivity.KEY_STORE_ID,storeId);
        startActivity(intent);

    }
}
