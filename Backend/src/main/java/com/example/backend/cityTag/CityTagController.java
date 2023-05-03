package com.example.backend.cityTag;
import com.example.backend.city.CityService;
import com.example.backend.tag.Tag;
import com.example.backend.tag.TagService;
import org.springframework.http.HttpStatus;
import com.example.backend.city.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cityTag")
public class CityTagController {
    private final CityTagService cityTagService;
    private final CityService cityService;
    private final TagService tagService;

    public CityTagController(CityTagService cityTagService, CityService cityService, TagService tagService) {
        this.cityTagService = cityTagService;
        this.cityService = cityService;
        this.tagService = tagService;
    }

    // GET
    @GetMapping("/findAllCityTags")
    public ResponseEntity<List<CityTag>> getAllCityTags(){
        List<CityTag> cityTags = cityTagService.getAllCityTags();
        return new ResponseEntity<>(cityTags, HttpStatus.OK);
    }

    @GetMapping("/getAllCitiesForGivenTag/{tagId}")
    public ResponseEntity<List<City>> getAllCitiesForGivenTag(@PathVariable("tagId") Integer tagId){
        List<CityTag> cityTags = cityTagService.getAllCitiesForAGivenTag(tagId);
        List<City> cities = cityTags.stream().map(CityTag::getCity).toList();

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
