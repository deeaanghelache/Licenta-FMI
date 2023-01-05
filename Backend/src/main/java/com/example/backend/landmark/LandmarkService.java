package com.example.backend.landmark;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LandmarkService {
    private LandmarkInterface landmarkInterface;

    @Autowired
    public LandmarkService(LandmarkInterface landmarkInterface) {
        this.landmarkInterface = landmarkInterface;
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

    // POST
    public Landmark addLandmark(Landmark landmark){
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
