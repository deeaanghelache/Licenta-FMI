package com.example.backend.cityTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityTagInterface extends JpaRepository<CityTag, Integer> {
    Optional<CityTag> findCityTagByCityTagId(CityTagId cityTagId);

    // get all cities by a given tag
    @Query(value = "SELECT city_tag.city_id, city_tag.tag_id " +
            "FROM city_tag JOIN tag ON (city_tag.tag_id = tag.tag_id) " +
            "WHERE city_tag.tag_id = :tagId", nativeQuery = true)
    List<CityTag> queryBy(@Param("tagId") Integer tagId);

    // get all tags for a given tag
    @Query(value = "SELECT city_tag.city_id, city_tag.tag_id " +
            "FROM city_tag JOIN city ON (city_tag.city_id = city.city_id) " +
            "WHERE city_tag.city_id = :cityId", nativeQuery = true)
    List<CityTag> queryBy(@Param("cityId") Long cityId);

    void deleteCityTagByCityTagId(CityTagId cityTagId);
}
