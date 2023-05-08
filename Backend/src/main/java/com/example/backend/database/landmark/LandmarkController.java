package com.example.backend.database.landmark;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/landmark")
public class LandmarkController {
    private final LandmarkService landmarkService;

    public LandmarkController(LandmarkService landmarkService) {
        this.landmarkService = landmarkService;
    }

    // GET
    @GetMapping("/getAllLandmarksForGivenCity/{cityId}")
    public ResponseEntity<List<Landmark>> getAllLandmarksForGivenCity(@PathVariable("cityId") Integer cityId){
        List<Landmark> landmarks = landmarkService.getAllLandmarksForGivenCity(cityId);
        return new ResponseEntity<>(landmarks, HttpStatus.OK);
    }
}
