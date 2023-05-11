package com.example.backend.database.listOfLandmarks;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ListOfLandmarksService {
    private ListOfLandmarksInterface listOfLandmarksInterface;

    @Autowired
    public ListOfLandmarksService(ListOfLandmarksInterface listOfLandmarksInterface) {
        this.listOfLandmarksInterface = listOfLandmarksInterface;
    }

    List<ListOfLandmarks> getAllLandmarksForGivenCityList(Integer cityListId){
        return listOfLandmarksInterface.getAllLandmarksForGivenCityList(cityListId);
    }
}
