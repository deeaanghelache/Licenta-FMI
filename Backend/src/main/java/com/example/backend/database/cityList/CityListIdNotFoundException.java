package com.example.backend.database.cityList;

public class CityListIdNotFoundException extends RuntimeException{
    public CityListIdNotFoundException() {
    }

    public CityListIdNotFoundException(String message) {
        super(message);
    }
}
