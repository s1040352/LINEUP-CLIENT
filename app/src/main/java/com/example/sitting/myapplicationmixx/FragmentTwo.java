package com.example.sitting.myapplicationmixx;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.sitting.myapplicationmixx.API.APIConnector;
import com.example.sitting.myapplicationmixx.API.AsyncComplete;
import com.example.sitting.myapplicationmixx.API.AsyncResult;
import com.example.sitting.myapplicationmixx.Entity.ExceptionEntity;
import com.example.sitting.myapplicationmixx.Entity.LineGo;


public class FragmentTwo extends Fragment {


    private View view;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
    }

    public void onResume(){
        super.onResume();
//

        LineGo.getOurEntity(new AsyncComplete() {
            @Override
            public void success(AsyncResult completeObject) {
                try {
                    LineGo lineGo=(LineGo)completeObject;
                    TextView  number=(TextView) view.findViewById(R.id.number);
                    number.setText(String.valueOf(lineGo.getId()));
                    TextView waittime=(TextView) view.findViewById(R.id.waittime) ;
                    waittime.setText(String.valueOf(lineGo.getWaitingtime()));
                    Log.d("API 456",lineGo.getWaitingtime()+"");
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("API TESTOUR","123");
                }
            }

            @Override
            public void failed(AsyncResult completeObject) {
                Log.e("API TEST6","failed");
                ExceptionEntity exceptionEntity = (ExceptionEntity)(completeObject);
                exceptionEntity.getAsyncResult().printStackTrace();
            }
        });

        LineGo.getFirstEntity(new AsyncComplete(){
            @Override
            public void success(AsyncResult completeObject) {
                try {
                    LineGo lineGo=(LineGo)completeObject;
                    TextView nownumber=(TextView) view.findViewById(R.id.nownumber);
                    nownumber.setText(String.valueOf(lineGo.getId()));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void failed(AsyncResult completeObject) {
                ExceptionEntity exceptionEntity = (ExceptionEntity)(completeObject);
                exceptionEntity.getAsyncResult().printStackTrace();
            }


        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        return view;
    }


    }

//LineGo.getFirstEntity(new AsyncComplete(){
//
//@Override
//public void success(AsyncResult completeObject) {
//        try {
//        LineGo linego =(LineGo) completeObject;
//        Log.d("API TEST ",linego.getID());
//        } catch (Exception e){}
//        }
//
//@Override
//public void failed(AsyncResult completeObject) {
//        Log.e("API TEST1","failed");
//        ExceptionEntity exceptionEntity = (ExceptionEntity)(completeObject);
//        exceptionEntity.getAsyncResult().printStackTrace();
//        }
//        })