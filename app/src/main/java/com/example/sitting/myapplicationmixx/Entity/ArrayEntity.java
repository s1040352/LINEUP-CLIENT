package com.example.sitting.myapplicationmixx.Entity;

import com.example.sitting.myapplicationmixx.API.AsyncResult;

public class ArrayEntity<T> implements AsyncResult<ArrayEntity> {

    private T asyncResult[];
    public ArrayEntity(T asyncResult[]){
        this.asyncResult = asyncResult;
    }

    public T[] getAsyncResult() {
        return asyncResult;
    }

    @Override
    public ArrayEntity<T> getResult() {
        return this;
    }
}
