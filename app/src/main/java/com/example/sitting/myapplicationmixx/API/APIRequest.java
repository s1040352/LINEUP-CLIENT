package com.example.sitting.myapplicationmixx.API;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.Queue;

public class APIRequest implements Runnable {
    private Queue<Runnable> requests = new ArrayDeque<>(20);
    private boolean shutdown = false;
    private boolean started = false;

    @Override
    public void run() {
        Log.d("API request","start Request queue");
        while (!shutdown){
            if(requests.size() > 0){
                pushRequest();
                Log.d("API request","executed a request");
            }
        }
    }

    synchronized private void pushRequest(){
        try{
            requests.poll().run();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    synchronized public void addRequest(Runnable request){
        requests.add(request);
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public void shutdown(){
        shutdown = true;
    }

    public void start(){
        if(!started){
            new Thread(this).start();
            started = true;
        }
    }
}
