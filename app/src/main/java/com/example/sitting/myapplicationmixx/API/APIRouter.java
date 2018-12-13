package com.example.sitting.myapplicationmixx.API;

import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;

public class APIRouter {
    private LinkedList<String> fields = new LinkedList<>();

    public static BasicNameValuePair value(String key,String value){
        return new BasicNameValuePair(key,value);
    }


    public APIRouter(){

    }

    private APIRouter(LinkedList<String> fields){
        this.fields = fields;
    }

    public APIRouter add(String field){
        fields.add(field);
        return this;
    }

    public String getRoutingString(){
        StringBuilder routingString = new StringBuilder("?");
        for(String field:fields){
            routingString.append(field).append("&");
        }

        return routingString.substring(0,routingString.length()-1);
    }

    public APIRouter clone(){
        return new APIRouter(this.fields);
    }
}
