package com.example.backend.database.landmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LandmarkInterface extends JpaRepository<Landmark, Integer> {
    Optional<Landmark> findLandmarkByName(String name);
    Optional<Landmark> findLandmarkByLandmarkId(Integer landmarkId);

    // get all landmarks for a given city
    @Query(value = "SELECT landmark.landmark_id, landmark.name, landmark.description_eng, landmark.description_rom, landmark.type_eng, landmark.type_rom, landmark.city_id, landmark.latitude, landmark.longitude, landmark.photo " +
            "FROM landmark JOIN city on (landmark.city_id = city.city_id) " +
            "WHERE landmark.city_id = :cityId", nativeQuery = true)
    List<Landmark> queryBy(@Param("cityId") Integer cityId);

    void deleteLandmarkByLandmarkId(Integer landmarkId);
}
