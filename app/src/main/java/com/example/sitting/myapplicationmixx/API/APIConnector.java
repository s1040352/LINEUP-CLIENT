package com.example.sitting.myapplicationmixx.API;


import org.apache.http.message.BasicNameValuePair;


import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class APIConnector {
    private static String host = "https://lineup.susautw.nctu.me/test_android2/lineup/";
    public static ArrayList<BasicNameValuePair> empty= new ArrayList<>();
    private APIRouter router;

    private APICallBack callBack;

    private ArrayList<BasicNameValuePair> entityBody;

    public static APIConnector connect(APIRouter router, ArrayList<BasicNameValuePair> entityBody,APICallBack callBack){
        return new APIConnector(router,entityBody,callBack);
    }

    private APIConnector(APIRouter router, ArrayList<BasicNameValuePair> entityBody,APICallBack callBack){
        this.router = router;
        this.callBack = callBack;
        this.entityBody = entityBody;
    }

    public void execute(){
        String URL = host + router.getRoutingString();
        callBack.setURL(URL);
        callBack.execute(entityBody);
    }

    public APIRouter getRouter(){
        return this.router;
    }

}
