package com.example.backend.database.cityRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRatingInterface extends JpaRepository<CityRating, Integer> {
    @Query("SELECT cr.city.cityId, AVG(cr.rating) AS rating " +
            "FROM CityRating cr " +
            "GROUP BY cr.city.cityId " +
            "ORDER BY rating DESC")
    List<Object[]> getCityAverageRatings();
}
