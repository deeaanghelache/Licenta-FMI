package com.example.backend.database.landmark;

import com.example.backend.database.city.City;
import com.example.backend.database.city.CityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LandmarkService {
    private LandmarkInterface landmarkInterface;
    private final CityService cityService;

    @Autowired
    public LandmarkService(LandmarkInterface landmarkInterface, CityService cityService) {
        this.landmarkInterface = landmarkInterface;
        this.cityService = cityService;
    }

    // FIND(GET)
    public List<Landmark> getAllLandmarks(){
        return landmarkInterface.findAll();
    }

    public Landmark getLandmarkByLandmarkId(Integer landmarkId){
        return landmarkInterface.findLandmarkByLandmarkId(landmarkId).orElseThrow(() -> new LandmarkIdNotFoundException("Couldn't find any landmark with the id: " + landmarkId));
    }

    public Landmark getLandmarkByLandmarkName(String landmarkName){
        return landmarkInterface.findLandmarkByName(landmarkName).orElseThrow(() -> new LandmarkNameNotFoundException("Couldn't find any landmark with the name: " + landmarkName));
    }

    public List<Landmark> getAllLandmarksForGivenCity(Integer cityId){
        return landmarkInterface.queryBy(cityId);
    }

    // POST
    public Landmark addLandmark(Landmark landmark, Integer cityId){
        City city = cityService.getCityById(cityId);
        landmark.setCity(city);
        return landmarkInterface.save(landmark);
    }

    // PUT
    // TODO: put

    // DELETE
    public void deleteAllLandmarks(){
        landmarkInterface.deleteAll();
    }

    public void deleteLandmarkById(Integer landmarkId){
        landmarkInterface.deleteLandmarkByLandmarkId(landmarkId);
    }
}
