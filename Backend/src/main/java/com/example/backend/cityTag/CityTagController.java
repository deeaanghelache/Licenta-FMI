package com.example.backend.cityTag;
import org.springframework.http.HttpStatus;
import com.example.backend.city.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
