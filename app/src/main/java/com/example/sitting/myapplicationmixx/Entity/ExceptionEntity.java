package com.example.sitting.myapplicationmixx.Entity;

import com.example.sitting.myapplicationmixx.API.AsyncResult;

public class ExceptionEntity<T extends Exception> implements AsyncResult<Exception> {

    private T asyncResult;
    public ExceptionEntity(T asyncResult){
        this.asyncResult = asyncResult;
    }

    public T getAsyncResult() {
        return asyncResult;
    }

    @Override
    public Exception getResult() {
        return asyncResult;
    }
}
