package com.example.sitting.myapplicationmixx.Entity;

import android.annotation.SuppressLint;

import com.example.sitting.myapplicationmixx.API.*;


import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Auth extends Entity implements AsyncResult<Auth> {
    private String token;
    private static APIRouter getTokenRouter = new APIRouter().add("auth").add("getToken");

    public String getToken(){
        return token;
    }

    public Auth(String token){
        this.token = token;
    }

    @SuppressLint("StaticFieldLeak")
    public static void getToken(String user, String pass, final AsyncComplete complete){
        ArrayList<BasicNameValuePair> data = new ArrayList<>();
        data.add(APIRouter.value("user",user));
        data.add(APIRouter.value("pass",pass));
        APIConnector.connect(getTokenRouter, data, new APICallBack() {
            @Override
            protected void callBack(JSONObject jsonObject) {
                try {
                    getTokenInJson(jsonObject);
                    complete.success(new Auth(jsonObject.getJSONObject("body").getString("token")));
                }catch (Exception e){
                    complete.failed(null);
                }


            }

            private String getTokenInJson(JSONObject jsonObject) throws JSONException {
                return jsonObject.getJSONObject("body").getString("token");
            }

        }).execute();
    }

    @Override
    public Auth getResult() {
        return this;
    }
}
