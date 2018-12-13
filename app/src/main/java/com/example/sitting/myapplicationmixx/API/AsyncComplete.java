package com.example.sitting.myapplicationmixx.API;

import org.json.JSONObject;

public interface AsyncComplete {
    public void success(AsyncResult completeObject);

    public void failed(AsyncResult completeObject);
}
