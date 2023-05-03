package com.example.backend.cityList;

import com.example.backend.city.City;
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

    // POST
    public CityList addCityList(CityList cityList) {
        return cityListInterface.save(cityList);
    }
}
