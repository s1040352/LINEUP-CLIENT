package com.example.sitting.myapplicationmixx;

import android.content.Intent;
//import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sitting.myapplicationmixx.API.AsyncComplete;
import com.example.sitting.myapplicationmixx.API.AsyncResult;
import com.example.sitting.myapplicationmixx.API.UserConnect;
import com.example.sitting.myapplicationmixx.Entity.User;

//import org.json.JSONObject;
//import java.util.ArrayList;

public class FragmentThree extends android.app.Fragment{

    private Button btnUpdate;
    private View view;


    @Override
    public void onResume() {
        super.onResume();

        UserConnect.getUser(new AsyncComplete() {
            @Override
            public void success(AsyncResult completeObject) {
                User user = (User)completeObject;

                try{
                    TextView name = (TextView) view.findViewById(R.id.name1);
                    name.setText(user.getName());
                    TextView account = (TextView) view.findViewById(R.id.username1);
                    account.setText(user.getUsername());
                    TextView email = (TextView) view.findViewById(R.id.email1);
                    email.setText(user.getEmail());
                    TextView gender = (TextView) view.findViewById(R.id.gender1);
                    gender.setText(user.getSelected());
                    TextView phone= (TextView) view.findViewById(R.id.phone1);
                    phone.setText(user.getTel());
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failed(AsyncResult completeObject) {

            }
        });
    }



    //  JSONParser jsonParser=new JSONParser();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_three, container, false);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnUpdate:
                        Intent intent = new Intent(getActivity(), editpage.class);
                        startActivity(intent);
                        break;
                }
            }

        });
//
        return view;
    }



}
// class apiconnect  {
//    public static void aptcon() {
//        String URL = "http://120.110.112.52/test_android2/lineup/?auth&getToken";
//        ArrayList params = new ArrayList();
//        JSONParser jsonParser = new JSONParser();
//        JSONObject json = jsonParser.makeHttpRequest(URL, "GET", params);
//    }

//}


 /*   public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdate:
                Intent intent = new Intent(getActivity(),Main3Activity.class);
                startActivity(intent);
                break;
        }
    }*/

