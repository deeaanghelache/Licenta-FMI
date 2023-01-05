package com.example.backend.city;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CityService {
    private CityInterface cityInterface;

    @Autowired
    public CityService(CityInterface cityInterface) {
        this.cityInterface = cityInterface;
    }

    // FIND (GET)
    public List<City> getAllCities(){
        return cityInterface.findAll();
    }

    public City getCityById(Integer cityId){
        return cityInterface.findCityByCityId(cityId).orElseThrow(() -> new CityIdNotFoundException("Couldn't find any city with the id: " + cityId));
    }

    public City getCityByNameEng(String englishName){
        return cityInterface.findCityByNameEng(englishName).orElseThrow(() -> new CityNameEngNotFoundException("Couldn't find any city with the english name: " + englishName));
    }

    public City getCityByNameRom(String romanianName){
        return cityInterface.findCityByNameRom(romanianName).orElseThrow(() -> new CityNameRomNotFoundException("Couldn't find any city with the romanian name: " + romanianName));
    }

    public Set<City> getCityByCurrencyName(String currencyName){
        return cityInterface.findCitiesByCurrencyName(currencyName).orElseThrow(() -> new CurrencyNameNotFoundException("Couldn't find any cities for the currency: " + currencyName));
    }

    public Set<City> getCitiesByCountryEng(String englishName){
        return cityInterface.findCitiesByCountryEng(englishName).orElseThrow(() -> new CountryEngNotFoundException("Couldn't find any city with the english country name: " + englishName));
    }

    public Set<City> getCitiesByCountryRom(String romanianName){
        return cityInterface.findCitiesByCountryRom(romanianName).orElseThrow(() -> new CountryRomNotFoundException("Couldn't find any city with the romanian country name: " + romanianName));
    }

    // POST
    public City addCity(City city){
        return cityInterface.save(city);
    }

    // PUT
    // TODO: put

    // DELETE
    public void deleteAllCities(){
        cityInterface.deleteAll();
    }

    public void deleteCityByCityId(Integer cityId){
        cityInterface.deleteCityByCityId(cityId);
    }

    public void deleteCityByNameEng(String englishName){
        cityInterface.deleteCityByNameEng(englishName);
    }

    public void deleteCityByNameRom(String romanianName){
        cityInterface.deleteCityByNameRom(romanianName);
    }

}
