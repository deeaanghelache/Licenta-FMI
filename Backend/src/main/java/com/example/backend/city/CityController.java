package com.example.backend.city;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping("/findAllCities")
    public ResponseEntity<List<City>> getAllCities(){
        List<City> cities = cityService.getAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping("/addCity")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City newCity = cityService.addCity(city);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }
}

