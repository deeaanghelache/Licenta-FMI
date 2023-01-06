package com.example.backend.landmark;

import com.example.backend.city.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface LandmarkInterface extends JpaRepository<Landmark, Integer> {
    Optional<Landmark> findLandmarkByName(String name);
    Optional<Landmark> findLandmarkByLandmarkId(Integer landmarkId);
    //Optional<Set<Landmark>> findLandmarksByCity(City city);

    void deleteLandmarkByLandmarkId(Integer landmarkId);
}
