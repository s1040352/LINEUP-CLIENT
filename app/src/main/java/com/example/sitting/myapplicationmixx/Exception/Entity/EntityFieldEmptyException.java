package com.example.sitting.myapplicationmixx.Exception.Entity;

public class EntityFieldEmptyException extends Exception{

    public EntityFieldEmptyException(String field,String entityName) {
        super("Missing "+entityName+"Field " + field);
    }
}
