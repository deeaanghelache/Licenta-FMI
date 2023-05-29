package com.example.backend.database.city;

import org.springframework.data.util.Pair;
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

    @GetMapping("/findAllCitiesNames")
    public ResponseEntity<List<String>> getAllCityNames(){
        List<String> cities = cityService.getAllCityNames();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/getAllCityCoordinates")
    public ResponseEntity<List<Pair<Integer, Pair<Double, Double>>>> getAllCityCoordinates(){
        List<Pair<Integer, Pair<Double, Double>>> cityCoordinates = cityService.getAllCityCoordinates();
        return new ResponseEntity<>(cityCoordinates, HttpStatus.OK);
    }

    @GetMapping("/getDistanceMatrix")
    public ResponseEntity<String[][]> getDistanceMatrix(){
        var distanceMatrix = cityService.getDistanceMatrix();
        return new ResponseEntity<>(distanceMatrix, HttpStatus.OK);
    }

    @GetMapping("getCityByNameContainsWord/{word}")
    public ResponseEntity<List<City>> getCitiesByNameContainsWord(@PathVariable("word") String word){
        List<City> cities = cityService.getCitiesByNameContainsWord(word);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping("/addCity")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City newCity = cityService.addCity(city);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }
}

