package com.example.backend.database.cityRating;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cityRating")
public class CityRatingController {
    private final CityRatingService cityRatingService;

    public CityRatingController(CityRatingService cityRatingService) {
        this.cityRatingService = cityRatingService;
    }

    @GetMapping("/getTopDestinations")
    public ResponseEntity<List<CityRatingModel>> getTopDestinations(){
        var cityRatings = cityRatingService.getCityRatings();
        return new ResponseEntity<>(cityRatings, HttpStatus.OK);
    }

    @PostMapping("/addRating/{cityId}")
    public ResponseEntity<CityRating> addRating(@PathVariable("cityId") Integer cityId, @RequestBody Integer rating){
        var newCityRating = cityRatingService.addRating(cityId, rating);
        return new ResponseEntity<>(newCityRating, HttpStatus.CREATED);
    }
}
