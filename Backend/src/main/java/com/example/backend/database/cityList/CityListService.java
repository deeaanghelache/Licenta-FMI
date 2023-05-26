package com.example.backend.database.cityList;

import com.example.backend.database.city.City;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class CityListService {
    private  CityListInterface cityListInterface;

    @Autowired
    public CityListService(CityListInterface cityListInterface) {
        this.cityListInterface = cityListInterface;
    }

    // FIND(GET)
    public List<CityList> getAllCityListsForAGivenUser(Integer userId) {
        return cityListInterface.queryBy(userId);
    }

    public List<CityList> getCityListByUserAndCity(Integer userId, Integer cityId) {
        System.out.println("_____");
        var cl = cityListInterface.getCityListByUserAndCity(userId, cityId);
        System.out.println(cl);
        return cl;
    }

    public CityList getCityListById(Integer cityListId){
        return cityListInterface.findCityListByCityListId(cityListId).orElseThrow(() -> new CityListIdNotFoundException("Couldn't find any cityList with the id: " + cityListId));
    }

    public List<City> getAllCitiesForGivenUser(Integer userId){
        List<CityList> cities = getAllCityListsForAGivenUser(userId);
        List<City> userCities = cities.stream().map(CityList::getCity).toList();
        return userCities;
    }

    // POST
    public CityList addCityList(CityList cityList) {
        return cityListInterface.save(cityList);
    }

    // DELETE
    public void deleteCityList(Integer cityId, Integer userId){
        cityListInterface.queryBy(cityId, userId);
    }

    public void deleteAllCityLists() {
        cityListInterface.deleteAll();
    }
}
