package com.example.sitting.myapplicationmixx;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sitting.myapplicationmixx.API.AsyncComplete;
import com.example.sitting.myapplicationmixx.API.AsyncResult;
import com.example.sitting.myapplicationmixx.Entity.Store;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragmentOne extends android.app.Fragment implements GoogleMap.OnMarkerClickListener,OnMapReadyCallback {
    private Button store01;
    GoogleMap googleMap;
    MapView mMapView;
    View mView;
    private Marker myMarker;
    final String[] s_Info = {"",""};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_one,container,false);

        mMapView =  mView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        ;
        Store.getEntity(106,new AsyncComplete(){

            @Override
            public void success(AsyncResult completeObject) {
                try{

                    Store store = (Store)completeObject;
                    s_Info[0] =store.getName();
                    s_Info[1] =store.getAddress();
                }
                    catch (Exception e){

                }
            }

            @Override
            public void failed(AsyncResult completeObject) {
                Log.d("API15","failed");
            }
        });
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        return mView;
//        store01 = (Button) mView.findViewById(R.id.store01);
//        store01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.store01:
//                        Intent intent = new Intent(getActivity(), StoreActivity.class);
////                        intent.putExtra("storeId","3");
//                        startActivity(intent);
//                        break;
//                }
//            }
//
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(myMarker)){
            Log.d("MAP", "onMarkerClick: dispatched");
            Intent intent = new Intent(getActivity(), StoreActivity.class);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        googleMap = mMap;

        // For dropping a marker at a point on the Map
        LatLng sydney = new LatLng(24.231686, 120.573740);
        myMarker = googleMap.addMarker(new MarkerOptions().position(sydney).title(s_Info[0]).snippet(s_Info[1]));

        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(18).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.setOnMarkerClickListener(this);
    }
}