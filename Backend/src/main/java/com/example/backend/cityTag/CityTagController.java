package com.example.backend.cityTag;
import org.springframework.http.HttpStatus;
import com.example.backend.city.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cityTag")
public class CityTagController {
    private final CityTagService cityTagService;

    public CityTagController(CityTagService cityTagService) {
        this.cityTagService = cityTagService;
    }

    @GetMapping("/getAllCitiesForGivenTag/{tagId}")
    public ResponseEntity<List<City>> getAllCitiesForGivenTag(@PathVariable("tagId") Integer tagId){
        List<CityTag> cityTags = cityTagService.getAllCitiesForAGivenTag(tagId);
        List<City> cities = cityTags.stream().map(CityTag::getCity).toList();

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    // POST
    @PostMapping("/addCityTag")
    public ResponseEntity<CityTag> addCityTag(@RequestBody CityTag cityTag){
        CityTag newCityTag = cityTagService.addCityTag(cityTag);
        return new ResponseEntity<>(newCityTag, HttpStatus.CREATED);
    }
}
