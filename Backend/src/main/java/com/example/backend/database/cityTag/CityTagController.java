package com.example.backend.database.cityTag;
import com.example.backend.database.city.CityService;
import com.example.backend.database.tag.Tag;
import com.example.backend.database.tag.TagService;
import org.springframework.http.HttpStatus;
import com.example.backend.database.city.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<City> cities = cityTagService.getAllCitiesForAGivenTag(tagId);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/getAllTagsForGivenCity/{cityId}")
    public ResponseEntity<List<Tag>> getAllTagsForGivenCity(@PathVariable("cityId") Integer cityId) {
        List<Tag> tags = cityTagService.getAllTagsForGivenCity(Long.valueOf(cityId));
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
