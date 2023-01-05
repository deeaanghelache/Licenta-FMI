package com.example.backend.airport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportInterface extends JpaRepository<Airport, Integer> {
    Optional<Airport> findAirportByAirportId(Integer airportId);
    Optional<Airport> findAirportByNameEng(String englishAirportName);
    Optional<Airport> findAirportByNameRom(String romanianAirportName);

    void deleteAirportByAirportId(Integer airportId);
    void deleteAirportByNameEng(String englishAirportName);
    void deleteAirportByNameRom(String romanianAirportName);
}
