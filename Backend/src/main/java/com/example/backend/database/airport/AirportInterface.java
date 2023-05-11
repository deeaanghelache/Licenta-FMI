package com.example.backend.database.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface AirportInterface extends JpaRepository<Airport, Integer> {
    Optional<Airport> findAirportByAirportId(Integer airportId);
    Optional<Airport> findAirportByNameEng(String englishAirportName);
    Optional<Airport> findAirportByNameRom(String romanianAirportName);

    @Query(value = "SELECT airport.airport_id, airport.name_eng, airport.name_rom, airport.distance_to_city, airport.city_id " +
            "FROM airport JOIN city ON (airport.city_id = city.city_id) " +
            "WHERE airport.city_id = :cityId", nativeQuery = true)
    List<Airport> queryBy(@Param("cityId") Integer cityId);

    void deleteAirportByAirportId(Integer airportId);
    void deleteAirportByNameEng(String englishAirportName);
    void deleteAirportByNameRom(String romanianAirportName);
}
