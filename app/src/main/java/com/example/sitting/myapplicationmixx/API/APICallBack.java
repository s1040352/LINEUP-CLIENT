package com.example.sitting.myapplicationmixx.API;

import android.os.AsyncTask;
import android.util.Log;

import com.example.sitting.myapplicationmixx.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

abstract public class APICallBack /*extends AsyncTask<ArrayList<BasicNameValuePair>, String, JSONObject>*/ {
    private String URL;
    private JSONObject json = new JSONObject();
    private static JSONParser jsonParser = new JSONParser();
    private static APIRequest request = new APIRequest();

    public APICallBack() {
        super();
        this.URL = URL;
        request.start();
    }

    /*@Override
    final protected void onPreExecute() {

        super.onPreExecute();
        Log.e("API","executing "+URL);
    }
*/
    public void setURL(String URL) {
        this.URL = URL;
    }

    /*@Override*/
    synchronized final protected JSONObject doInBackground(ArrayList<BasicNameValuePair>... arrayLists) {

        try {

            json = jsonParser.makeHttpRequest(URL, "POST", arrayLists[0]);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                json = new JSONObject("{\"status\":-1}");
            }catch (Exception ej){

            }
        }
        return json;
    }

    public void execute(final ArrayList<BasicNameValuePair> arrayList){
        Log.e("API callback",URL);
        request.addRequest(new Runnable() {
            @Override
            public void run() {
                callBack(doInBackground(arrayList));
            }
        });
    }

    abstract protected void callBack(JSONObject json);


//    @Override
//    final protected void onPostExecute(JSONObject jsonObject) {
//        super.onPostExecute(jsonObject);
//        callBack(json);
//        this.cancel(true);
//    }
}
