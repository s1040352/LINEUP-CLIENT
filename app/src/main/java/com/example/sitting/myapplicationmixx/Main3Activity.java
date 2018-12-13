package com.example.sitting.myapplicationmixx;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sitting.myapplicationmixx.FragmentOne;
import com.example.sitting.myapplicationmixx.FragmentThree;
import com.example.sitting.myapplicationmixx.FragmentTwo;
import com.example.sitting.myapplicationmixx.FragmentFour;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Main3Activity extends AppCompatActivity {

    private TextView mTextMessage;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private GoogleMap mGoogleMap;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_map:
                    mTextMessage.setText(R.string.title_map);
                    showNav(R.id.navigation_map);
                    return true;
                case R.id.navigation_line:
                    mTextMessage.setText(R.string.title_line);
                    showNav(R.id.navigation_line);
                    return true;
                case R.id.navigation_me:
                    mTextMessage.setText(R.string.title_me);
                    showNav(R.id.navigation_me);
                    return true;
                case R.id.navigation_setting:
                    mTextMessage.setText(R.string.title_setting);
                    showNav(R.id.navigation_setting);
                    return true;
            }
            return false;
        }

    };

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    private void init() {
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
        fragmentFour = new FragmentFour();
        FragmentTransaction beginTransaction=getFragmentManager().beginTransaction();
        beginTransaction.add(R.id.content,fragmentOne).add(R.id.content,fragmentTwo).add(R.id.content,fragmentThree).add(R.id.content,fragmentFour);//开启一个事务将fragment动态加载到组件
        beginTransaction.hide(fragmentOne).hide(fragmentTwo).hide(fragmentThree).hide(fragmentFour);//隐藏fragment
        beginTransaction.addToBackStack(null);//返回到上一个显示的fragment
        beginTransaction.commit();//每一个事务最后操作必须是commit（），否则看不见效果
        showNav(R.id.navigation_map);


        }

    private void showNav(int navid) {
        FragmentTransaction beginTransaction=getFragmentManager().beginTransaction();
        switch (navid){
            case R.id.navigation_map:
                beginTransaction.hide(fragmentTwo).hide(fragmentThree).hide(fragmentFour);
                beginTransaction.show(fragmentOne);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            case R.id.navigation_line:
                beginTransaction.hide(fragmentOne).hide(fragmentThree).hide(fragmentFour);
                beginTransaction.show(fragmentTwo);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            case R.id.navigation_me:
                beginTransaction.hide(fragmentTwo).hide(fragmentOne).hide(fragmentFour);
                beginTransaction.show(fragmentThree);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
            case R.id.navigation_setting:
                beginTransaction.hide(fragmentTwo).hide(fragmentOne).hide(fragmentThree);
                beginTransaction.show(fragmentFour);
                beginTransaction.addToBackStack(null);
                beginTransaction.commit();
                break;
        }
    }


}