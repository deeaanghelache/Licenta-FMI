package com.example.backend.city;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CityInterface extends JpaRepository<City, Integer> {
    Optional<City> findCityByCityId(Integer cityId);
    Optional<City> findCityByNameEng(String englishName);
    Optional<City> findCityByNameRom(String romanianName);
    Optional<Set<City>> findCitiesByCurrencyName(String currencyName);
    Optional<Set<City>> findCitiesByCountryEng(String englishCountryName);
    Optional<Set<City>> findCitiesByCountryRom(String romanianCountryName);

    void deleteCityByCityId(Integer cityId);
    void deleteCityByNameEng(String englishName);
    void deleteCityByNameRom(String romanianName);

}
