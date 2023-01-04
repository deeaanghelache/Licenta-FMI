package com.example.backend.landmarkList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandmarkListInterface extends JpaRepository<LandmarkList, Integer> {
    Optional<LandmarkList> findLandmarkListByLandmarkListId(Integer landmarkListId);

    void deleteLandmarkListByLandmarkListId(Integer landmarkListId);
}
