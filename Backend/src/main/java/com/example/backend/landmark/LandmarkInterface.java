package com.example.backend.landmark;

import com.example.backend.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LandmarkInterface extends JpaRepository<Landmark, Integer> {
    Optional<Landmark> findLandmarkByName(String name);
    Optional<Landmark> findLandmarkByLandmarkId(Integer landmarkId);

    // get all landmarks for a given city
    @Query(value = "SELECT landmark.landmark_id, landmark.name, landmark.descriptionEng, landmark.descriptionRom, landmark.typeEng, landmark.typeRom " +
            "FROM landmark JOIN city on (landmark.city_id = city.city_id) " +
            "WHERE landmark.city_id = :cityId", nativeQuery = true)
    List<Landmark> queryBy(@Param("cityId") Integer cityId);

    void deleteLandmarkByLandmarkId(Integer landmarkId);
}
