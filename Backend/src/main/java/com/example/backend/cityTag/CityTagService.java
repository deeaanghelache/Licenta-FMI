package com.example.backend.cityTag;

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
    public List<CityTag> getAllCitiesForAGivenTag(Integer tagId){
        return cityTagInterface.queryBy(tagId);
    }

    // POST
    public CityTag addCityTag(CityTag cityTag) {
        return cityTagInterface.save(cityTag);
    }
}
