package com.example.backend.database.city;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class CityService {
    public static final double earthRadiusKm = 6371;
    private CityInterface cityInterface;

    @Autowired
    public CityService(CityInterface cityInterface) {
        this.cityInterface = cityInterface;
    }

    public static double haversine(double firstCityLatitude, double firstCityLongitude, double secondCityLatitude, double secondCityLongitude) {
        double distanceLatitude = Math.toRadians(secondCityLatitude - firstCityLatitude);
        double distanceLongitude = Math.toRadians(secondCityLongitude - firstCityLongitude);

        firstCityLatitude = Math.toRadians(firstCityLatitude);
        secondCityLatitude = Math.toRadians(secondCityLatitude);

        double formula = Math.sin(distanceLatitude / 2) * Math.sin(distanceLatitude / 2) +
                Math.sin(distanceLongitude / 2) * Math.sin(distanceLongitude / 2) * Math.cos(firstCityLatitude) * Math.cos(secondCityLatitude);
        double finalFormula = 2 * Math.asin(Math.sqrt(formula));
        return earthRadiusKm * finalFormula;
    }

    public String[][] getDistanceMatrix(){
        List<City> cities = getAllCities();
        List<String> citiesNames = cities.stream().map(City::getNameEng).toList();

        int numberOfRows = citiesNames.size() + 1;
        int numberOfColumns = citiesNames.size() + 1;

        String[][] distanceMatrix = new String[numberOfRows][numberOfColumns];

        // the first row and column should have the names of the cities
        for (int i = 1; i < numberOfRows; i++) {
            var index = i - 1;
            distanceMatrix[0][i] = citiesNames.get(index);
            distanceMatrix[i][0] = citiesNames.get(index);
        }

        for (int i = 1; i < numberOfRows; i++){
            for (int j = 1; j < numberOfColumns; j++){
                var index = j - 1;
                if (!Objects.equals(cities.get(index).getNameEng(), distanceMatrix[i][0])) {
                    var firstCity = cities.get(index);
                    var secondCity = getCityByNameEng(distanceMatrix[i][0]);
                    var distanceBetweenTheCities = haversine(firstCity.getLatitude(), firstCity.getLongitude(), secondCity.getLatitude(), secondCity.getLongitude());
                    distanceMatrix[i][j] = String.valueOf(distanceBetweenTheCities);
                } else {
                    distanceMatrix[i][j] = String.valueOf(0);
                }
            }
        }
        return distanceMatrix;
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

    public List<City> getCitiesByNameContainsWord(String word){
        var cities = getAllCities();
        List<City> citiesResponse = new ArrayList<>();

        for (var city : cities){
            if (city.getNameEng().toLowerCase().contains(word.toLowerCase())){
                citiesResponse.add(city);
            }
        }
        return citiesResponse;
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
