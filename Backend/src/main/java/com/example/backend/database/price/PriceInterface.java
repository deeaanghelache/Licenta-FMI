package com.example.backend.database.price;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceInterface extends JpaRepository<Price, Integer> {
    Optional<Price> findPriceByLandmarkId(Integer landmarkId);

    void deletePriceByLandmarkId(Integer landmarkId);
}
