package com.example.backend.database.cityList;

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
        return cityListInterface.getCityListByUserAndCity(userId, cityId);
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
