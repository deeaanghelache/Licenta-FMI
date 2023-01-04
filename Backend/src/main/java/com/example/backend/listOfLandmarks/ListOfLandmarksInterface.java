package com.example.backend.listOfLandmarks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListOfLandmarksInterface extends JpaRepository<ListOfLandmarks, Integer> {

}
