package com.example.backend.database.cityTag;

import com.example.backend.database.city.City;
import com.example.backend.database.tag.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class CityTagService {
    private CityTagInterface cityTagInterface;

    @Autowired
    public CityTagService(CityTagInterface cityTagInterface) {
        this.cityTagInterface = cityTagInterface;
    }

    // FIND(GET)
    public List<City> getAllCitiesForAGivenTag(Integer tagId){
        var cityTags =  cityTagInterface.queryBy(tagId);
        var cities = cityTags.stream().map(CityTag::getCity).toList();

        return cities;
    }

    public  List<Tag> getAllTagsForGivenCity(Long cityId) {
        var cityTags = cityTagInterface.queryBy(cityId);
        var tags = cityTags.stream().map(CityTag::getTag).toList();

        return tags;
    }

    public List<CityTag> getAllCityTags(){
        return cityTagInterface.findAll();
    }

    // POST
    public CityTag addCityTag(CityTag cityTag) {
        return cityTagInterface.save(cityTag);
    }
}
