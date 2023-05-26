package com.example.backend.database.listOfLandmarks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListOfLandmarksInterface extends JpaRepository<ListOfLandmarks, Integer> {
    // get all landmarks for given city list
    @Query(value = "SELECT list_of_landmarks.list_of_landmarks_id, list_of_landmarks.landmark_id, list_of_landmarks.priority, list_of_landmarks.city_list_id " +
            "FROM list_of_landmarks JOIN landmark ON (list_of_landmarks.landmark_id = landmark.landmark_id) " +
            "WHERE list_of_landmarks.city_list_id = :cityListId", nativeQuery = true)
    List<ListOfLandmarks> getAllLandmarksForGivenCityList(@Param("cityListId") Integer cityListId);


}
