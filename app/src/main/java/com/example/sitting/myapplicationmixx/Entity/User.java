package com.example.sitting.myapplicationmixx.Entity;

import android.annotation.SuppressLint;

import com.example.sitting.myapplicationmixx.API.APICallBack;
import com.example.sitting.myapplicationmixx.API.APIConnector;
import com.example.sitting.myapplicationmixx.API.APIRouter;
import com.example.sitting.myapplicationmixx.API.AsyncComplete;
import com.example.sitting.myapplicationmixx.API.AsyncResult;
import com.example.sitting.myapplicationmixx.Exception.Entity.EntityFieldEmptyException;
import com.example.sitting.myapplicationmixx.Exception.Entity.UserNotExistsException;

import org.json.JSONException;
import org.json.JSONObject;

public class User extends Entity implements AsyncComplete,AsyncResult<User> {
    private APIRouter userGetRouter = new APIRouter().add("user").add("get");
    private static Auth auth;
    private JSONObject userData;
    private AsyncComplete complete;

    public static void getEntityAuth(String user,String pass,AsyncComplete complete){
        Auth.getToken(user,pass,new User(complete));
    }

    public static void getEntityByToken(String token, AsyncComplete complete){
        new User(complete).success(new Auth(token));
    }


    public User(JSONObject jsonObject) throws UserNotExistsException {
        setUserData(jsonObject);
    }

    private User(AsyncComplete complete){
        userData = new JSONObject();
        this.complete = complete;
    }

    private void setUserData(JSONObject jsonObject) throws UserNotExistsException {
        if(!jsonObject.has("id"))
            throw new UserNotExistsException();
        userData = jsonObject;
    }

    private void setAuth(Auth auth){
        User.auth = auth;
    }

    public Auth getAuth(){
        return auth;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void success(AsyncResult completeObject) {
        final Auth auth = (Auth)completeObject.getResult();
        final User _this = this;

        APIConnector.connect(userGetRouter.add(auth.getToken()), APIConnector.empty, new APICallBack() {
            @Override
            protected void callBack(JSONObject jsonObject) {

                try {
                        _this.setUserData(jsonObject.getJSONObject("body").getJSONObject("user"));
                        _this.setAuth(auth);
                        complete.success(_this);
                    }catch (Exception e){
                        e.printStackTrace();
                        complete.failed(_this);
                }
            }
        }).execute();
    }

    @Override
    public void failed(AsyncResult completeObject) {
        this.complete.failed(this);
    }

    @Override
    public User getResult() {
        return this;
    }

    public void setId(String id){
        try{
            userData.put("id",id);
        }catch (JSONException e){}
    }

    public void setUsername(String username){
        try{
            userData.put("username",username);
        }catch (JSONException e){}
    }

    public void setPassword(String password){
        try{
            userData.put("password",password);
        }catch (JSONException e){}
    }

    public void setEmail(String email){
        try{
            userData.put("email",email);
        }catch (JSONException e){}
    }

    public void setTel(String tel){
        try{
            userData.put("tel",tel);
        }catch (JSONException e){}
    }

    public void setCreateAt(String createAt){
        try{
            userData.put("create_at",createAt);
        }catch (JSONException e){}
    }

    public void setUpdateAt(String updateAt){
        try{
            userData.put("update_at",updateAt);
        }catch (JSONException e){}
    }

    public void setSelected(String selected){
        try{
            userData.put("selected",selected);
        }catch (JSONException e){}
    }

    public void setName(String name){
        try{
            userData.put("name",name);
        }catch (JSONException e){}
    }

    public int getId() throws EntityFieldEmptyException {
        try {
            return userData.getInt("id");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("id","User");
        }
    }

    public String getUsername() throws EntityFieldEmptyException {
        try {
            return userData.getString("username");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("username","User");
        }
    }

    public String getPassword() throws EntityFieldEmptyException {
        try {
            return userData.getString("id");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("password","User");
        }
    }

    public String getEmail() throws EntityFieldEmptyException {
        try {
            return userData.getString("email");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("email","User");
        }
    }

    public String getTel() throws EntityFieldEmptyException {
        try {
            return userData.getString("tel");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("tel","User");
        }
    }

    public String getCreateAt() throws EntityFieldEmptyException {
        try {
            return userData.getString("create_at");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("CreateAt","User");
        }
    }

    public String getUpdateAt() throws EntityFieldEmptyException {
        try {
            return userData.getString("update_at");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("UpdateAt","User");
        }
    }

    public String getSelected() throws EntityFieldEmptyException {
        try {
            return userData.getString("selected");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("selected","User");
        }
    }

    public String getName() throws EntityFieldEmptyException {
        try {
            return userData.getString("name");
        } catch (JSONException e) {
            throw new EntityFieldEmptyException("name","User");
        }
    }



}
