package com.example.backend.cityTag;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CityTagInterface extends JpaRepository<CityTag, Integer> {
    Optional<CityTag> findCityTagByCityTagId(CityTagId cityTagId);
    void deleteCityTagByCityTagId(CityTagId cityTagId);
}
