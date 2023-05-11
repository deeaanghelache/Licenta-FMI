package com.example.backend.database.listOfLandmarks;

import com.example.backend.database.landmark.Landmark;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/listOfLandmarks")
public class ListOfLandmarksController {
    private final ListOfLandmarksService listOfLandmarksService;

    public ListOfLandmarksController(ListOfLandmarksService listOfLandmarksService) {
        this.listOfLandmarksService = listOfLandmarksService;
    }

    @GetMapping("/getAllLandmarksForGivenCityList/{cityListId}")
    public ResponseEntity<List<Landmark>> getAllLandmarksForGivenCityList(@PathVariable("cityListId") Integer cityListId){
        List<ListOfLandmarks> listOfLandmarks = listOfLandmarksService.getAllLandmarksForGivenCityList(cityListId);
        List<Landmark> landmarks = listOfLandmarks.stream().map(ListOfLandmarks::getLandmark).toList();

        return new ResponseEntity<>(landmarks, HttpStatus.OK);
    }
}
