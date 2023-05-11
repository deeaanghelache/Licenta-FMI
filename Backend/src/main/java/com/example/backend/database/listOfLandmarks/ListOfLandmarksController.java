package com.example.backend.database.listOfLandmarks;

import com.example.backend.database.city.CityService;
import com.example.backend.database.cityList.CityList;
import com.example.backend.database.cityList.CityListService;
import com.example.backend.database.landmark.Landmark;
import com.example.backend.database.landmark.LandmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listOfLandmarks")
public class ListOfLandmarksController {
    private final ListOfLandmarksService listOfLandmarksService;
    private final CityListService cityListService;
    private final LandmarkService landmarkService;

    public ListOfLandmarksController(ListOfLandmarksService listOfLandmarksService, CityListService cityListService, LandmarkService landmarkService) {
        this.listOfLandmarksService = listOfLandmarksService;
        this.cityListService = cityListService;
        this.landmarkService = landmarkService;
    }

    @GetMapping("/getAllLandmarksForGivenCityList/{cityListId}")
    public ResponseEntity<List<Landmark>> getAllLandmarksForGivenCityList(@PathVariable("cityListId") Integer cityListId){
        List<ListOfLandmarks> listOfLandmarks = listOfLandmarksService.getAllLandmarksForGivenCityList(cityListId);
        List<Landmark> landmarks = listOfLandmarks.stream().map(ListOfLandmarks::getLandmark).toList();

        return new ResponseEntity<>(landmarks, HttpStatus.OK);
    }

    @PostMapping("addListOfLandmarks/{cityListId}/{landmarkId}/{priority}")
    public ResponseEntity<ListOfLandmarks> addListOfLandmark(@PathVariable("cityListId") Integer cityListId, @PathVariable("landmarkId") Integer landmarkId, @PathVariable("priority") Integer priority){
        CityList cityList = cityListService.getCityListById(cityListId);
        Landmark landmark = landmarkService.getLandmarkByLandmarkId(landmarkId);
        ListOfLandmarks listOfLandmarks = new ListOfLandmarks(priority, landmark, cityList);
        System.out.println(listOfLandmarks);
        ListOfLandmarks newListOfLandmarks = listOfLandmarksService.addListOfLandmarks(listOfLandmarks);

        return new ResponseEntity<>(newListOfLandmarks, HttpStatus.CREATED);
    }
}
