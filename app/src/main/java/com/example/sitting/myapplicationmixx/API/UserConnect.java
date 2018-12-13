package com.example.sitting.myapplicationmixx.API;

import com.example.sitting.myapplicationmixx.Entity.Auth;
import com.example.sitting.myapplicationmixx.Entity.User;

public class UserConnect implements AsyncResult<User> {
    private static AsyncComplete complete;

    private static User user = null;
    private static Auth auth = null;

    private static String username = null;
    private static String password = null;

    private static class getUserCallback implements AsyncComplete{
        @Override
        public void success(AsyncResult completeObject) {
            User user = (User)completeObject;
            UserConnect.user = user;
            UserConnect.auth = user.getAuth();

            complete.success(user);
        }

        @Override
        public void failed(AsyncResult completeObject) {
            complete.failed(completeObject);
        }
    }

    public static class EmptyUserException extends Exception{}

    public static void getUser(AsyncComplete complete){
        UserConnect.complete = complete;
        if(user != null)
            complete.success(user);
        else if(auth != null){
            User.getEntityByToken(auth.getToken(),new getUserCallback());
        }else if(username != null && password != null){
            User.getEntityAuth(username,password,new getUserCallback());
        }else
            complete.failed(null);
    }

    public static void setAuth(Auth auth){
        UserConnect.auth = auth;
    }

    public static void setAuthData(String username,String password){
        UserConnect.username = username;
        UserConnect.password = password;
    }

    @Override
    public User getResult() {
        return user;
    }

}
