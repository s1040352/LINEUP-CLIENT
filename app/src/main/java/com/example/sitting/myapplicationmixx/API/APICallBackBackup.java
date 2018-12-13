package com.example.sitting.myapplicationmixx.API;

import android.os.AsyncTask;

import com.example.sitting.myapplicationmixx.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

abstract public class APICallBackBackup extends AsyncTask<ArrayList<BasicNameValuePair>, String, JSONObject> {
    private String URL;
    private JSONObject json = new JSONObject();

    public APICallBackBackup() {
        super();
        this.URL = URL;
    }

    @Override
    final protected void onPreExecute() {

        super.onPreExecute();

    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    final protected JSONObject doInBackground(ArrayList<BasicNameValuePair>... arrayLists) {

        try {
            JSONParser jsonParser = new JSONParser();
            json = jsonParser.makeHttpRequest(URL, "POST", arrayLists[0]);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return new JSONObject("{\"status\":-1}");
            }catch (Exception ej){

            }
        }
        return null;
    }

    abstract protected void callBack(JSONObject json);


    @Override
    final protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        callBack(json);
        //this.cancel(true);

    }
}
