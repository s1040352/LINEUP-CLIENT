package com.example.sitting.myapplicationmixx.Entity;

import android.util.Log;

import com.example.sitting.myapplicationmixx.API.APICallBack;
import com.example.sitting.myapplicationmixx.API.APIConnector;
import com.example.sitting.myapplicationmixx.API.APIRouter;
import com.example.sitting.myapplicationmixx.API.AsyncComplete;
import com.example.sitting.myapplicationmixx.API.AsyncResult;
import com.example.sitting.myapplicationmixx.Exception.Entity.EntityFieldEmptyException;
import com.example.sitting.myapplicationmixx.Exception.Entity.StoreNotExistsException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Store extends Entity implements AsyncResult<Store> {
    private static APIRouter getAllRouter = new APIRouter().add("store").add("getAll");
    private static APIRouter getRouter = new APIRouter().add("store").add("get");

    private JSONObject storeData;
    public static void getAllEntity(final AsyncComplete complete){

        APIConnector.connect(getAllRouter, APIConnector.empty, new APICallBack() {
            @Override
            protected void callBack(JSONObject jsonObject) {
                try{
                    JSONArray data = jsonObject.getJSONObject("body").getJSONArray("stores");
                    Store stores[] = new Store[data.length()];
                    for(int i=0;i<data.length();i++){
                        stores[i] = jsonToStore(data.getJSONObject(i));
                    }
                    complete.success(new ArrayEntity<Store>(stores));
                }catch (Exception e){
                    complete.failed(null);
                }
            }

            private Store jsonToStore(JSONObject jsonObject) throws StoreNotExistsException {
                return new Store(jsonObject);
            }
        }).execute();
    }

    public static void getEntity(int id, final AsyncComplete complete){
        APIConnector.connect(getRouter.clone().add(id + ""), APIConnector.empty, new APICallBack() {
            @Override
            protected void callBack(JSONObject jsonObject) {
                try{
                    Log.d("JSON API OK?",jsonObject.toString());
                    Store store = new Store(jsonObject.getJSONObject("body").getJSONObject("store"));
                    complete.success(store);
                }catch (Exception e){
                    complete.failed(new ExceptionEntity<>(e));
                }

            }
        }).execute();
    }

    @Override
    public Store getResult() {
        return this;
    }

    public Store(JSONObject jsonObject) throws StoreNotExistsException {
        if(jsonObject.has("id"))
            this.storeData = jsonObject;
        else
            throw new StoreNotExistsException();
    }

    public String getId() throws EntityFieldEmptyException {
        try {
            int id =storeData.getInt("id");
            String id2=Integer.toString(id);
            return id2;
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("id","Store");
        }
    }
    public String getName() throws EntityFieldEmptyException {
        try {
            return storeData.getString("st_name");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("name","Store");
        }
    }
    public String getUsername() throws EntityFieldEmptyException {
        try {
            return storeData.getString("st_username");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("username","Store");
        }
    }
    public String getAddress() throws EntityFieldEmptyException {
        try {
            return storeData.getString("st_address");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("address","Store");
        }
    }
    public String getPhone() throws EntityFieldEmptyException {
        try {
            return storeData.getString("st_phone");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("phone","Store");
        }
    }
}
