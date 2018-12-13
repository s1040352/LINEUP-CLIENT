package com.example.sitting.myapplicationmixx.Entity;

import android.util.Log;

import com.example.sitting.myapplicationmixx.API.APICallBack;
import com.example.sitting.myapplicationmixx.API.APIConnector;
import com.example.sitting.myapplicationmixx.API.APIRouter;
import com.example.sitting.myapplicationmixx.API.AsyncComplete;
import com.example.sitting.myapplicationmixx.API.AsyncResult;
import com.example.sitting.myapplicationmixx.API.UserConnect;
import com.example.sitting.myapplicationmixx.Exception.Entity.EntityFieldEmptyException;
import com.example.sitting.myapplicationmixx.Exception.Entity.LinegoNotExistsException;

import org.json.JSONException;
import org.json.JSONObject;

public class LineGo extends Entity implements AsyncResult<LineGo> {
    private static APIRouter routerFirst = new APIRouter().add("linego").add("getFirst");
    private static APIRouter routerUsername = new APIRouter().add("linego").add("get");

    private JSONObject linegoData;

    public static void getFirstEntity(final AsyncComplete complete){
        APIConnector.connect(routerFirst, APIConnector.empty, new APICallBack() {
            @Override
            protected void callBack(JSONObject jsonObject) {

                try {
                    JSONObject data = jsonObject.getJSONObject("body").getJSONObject("linego");
                    complete.success(new LineGo(data));
                } catch (Exception e) {
                    e.printStackTrace();
                    complete.failed(new ExceptionEntity<>(e));
                }
            }
        }).execute();
    }

    public static void getOurEntity(final AsyncComplete complete){
        Log.e("API","frag");
        UserConnect.getUser(new AsyncComplete() {
            @Override
            public void success(AsyncResult completeObject) {

                User user = ((User)completeObject);

                Log.e("API","frag2 "+routerUsername.getRoutingString());

                try {
                    APIConnector.connect(routerUsername.clone().add(user.getUsername()), APIConnector.empty, new APICallBack() {
                        @Override
                        protected void callBack(JSONObject jsonObject) {
                            Log.d("API GET OK", "OUR");
                            try {
                                JSONObject data = jsonObject.getJSONObject("body").getJSONObject("linego");
                                complete.success(new LineGo(data));
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println(e);
                                Log.d("567", String.valueOf(e));
                                complete.failed(new ExceptionEntity<>(e));
                            }
                        }
                    }).execute();
                } catch (EntityFieldEmptyException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(AsyncResult completeObject) {
                Log.e("API Lingo","fail");
            }
        });


    }

    private LineGo(JSONObject jsonObject) throws LinegoNotExistsException {
        if(jsonObject.has("id"))
            this.linegoData = jsonObject;
        else
            throw new LinegoNotExistsException();
    }



    public int getId() throws EntityFieldEmptyException{
        try {
            int id = linegoData.getInt("id");

            return id;
        } catch (JSONException e){
            throw new EntityFieldEmptyException("id","LineGo");
        }
    }
    public String getUsername() throws EntityFieldEmptyException {
        try {
            return linegoData.getString("username");
        }catch (Exception e){
            throw new EntityFieldEmptyException("username","LineGo");
        }
    }
    public String getTel() throws EntityFieldEmptyException{
        try {
            return linegoData.getString("tel");
        }catch (Exception e){
            throw new EntityFieldEmptyException("tel","LineGo");
        }
    }
    public int getWaitingtime() throws EntityFieldEmptyException{
        try {
            return linegoData.getInt("waitingtime");
        }catch (Exception e){
            throw new EntityFieldEmptyException("waitingtime","LineGo");
        }
    }
    public int getLinetime() throws EntityFieldEmptyException{
        try {
            return linegoData.getInt("linetime");
        }catch (Exception e){
            throw new EntityFieldEmptyException("linetime","LineGo");
        }
    }


    @Override
    public LineGo getResult() {
        return this;
    }

    //TODO complete the Setter and Getter
}
