package com.developer.willshuffy.willstore;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.developer.willshuffy.willstore.fragments.StoreListFragment;
import com.developer.willshuffy.willstore.responses.Store;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double mLat, mLng;
    public static final String store_key="STORE_KEY";
    private String mStoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mLat=getIntent().getDoubleExtra(StoreListFragment.KEY_LAT, 0);
        mLng=getIntent().getDoubleExtra(StoreListFragment.KEY_LNG, 0);
        mStoreList=getIntent().getStringExtra(store_key);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        drawStores();
    }

    private void drawStores() {
        if(TextUtils.isEmpty(mStoreList)){
            return;
        }

        Gson gson = new Gson();
        Type listOfObject = new TypeToken<List<Store>>(){}.getType();
        List<Store> storeList = gson.fromJson(mStoreList, listOfObject);

        if(storeList != null){
            for(int i = 0; i<storeList.size(); i++){
                Store store = storeList.get(i);
                LatLng latLng = new LatLng(Double.parseDouble(store.getLat()),
                        Double.parseDouble(store.getLng()));

                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(store.getName())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_store)));
                //untuk zoom location store
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
            }
        }
    }
}
