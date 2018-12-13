package com.example.sitting.myapplicationmixx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sitting.myapplicationmixx.API.AsyncComplete;
import com.example.sitting.myapplicationmixx.API.AsyncResult;
import com.example.sitting.myapplicationmixx.Entity.ArrayEntity;
import com.example.sitting.myapplicationmixx.Entity.ExceptionEntity;
import com.example.sitting.myapplicationmixx.Entity.Store;
import com.google.zxing.integration.android.IntentIntegrator;

public class StoreActivity extends AppCompatActivity {
    private static Store storeInfo;
    private IntentIntegrator scanIntegrator;
    private Button btnqrcode;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        btnqrcode = (Button)findViewById(R.id.btnqrcode);



        btnqrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(StoreActivity.this, QRcode.class);
                startActivity(intent);
            }
        });




        Store.getEntity(106,new AsyncComplete(){

            @Override
            public void success(AsyncResult completeObject) {
                try{

                    Store store = (Store)completeObject;

                    Log.d("API TEST ",store.getName());
                    Log.d("API TEST",store.getUsername());
                    Log.d("API TEST",store.getAddress());

                    TextView  stname= (TextView) findViewById(R.id.stname);
                    stname.setText(store.getName());
                    TextView  sttel= (TextView) findViewById(R.id.sttel);
                    sttel.setText(store.getPhone());
                    TextView  staddress =(TextView) findViewById(R.id.staddress);
                    staddress.setText(store.getAddress());

//

//
                }catch (Exception e){

                }

            }

            @Override
            public void failed(AsyncResult completeObject) {
                Log.e("API TEST1","failed");
                ExceptionEntity exceptionEntity = (ExceptionEntity)(completeObject);
                exceptionEntity.getAsyncResult().printStackTrace();
            }
        });


    }

    }



