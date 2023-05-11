package com.example.backend.database.cityList;

import com.example.backend.database.city.City;
import com.example.backend.database.user.User;
import com.example.backend.database.city.CityService;
import com.example.backend.database.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cityList")
public class CityListController {
    private final CityListService cityListService;
    private final CityService cityService;
    private final UserService userService;

    public CityListController(CityListService cityListService, CityService cityService, UserService userService) {
        this.cityListService = cityListService;
        this.cityService = cityService;
        this.userService = userService;
    }

    @GetMapping("/getAllCitiesForGivenUser/{userId}")
    public ResponseEntity<List<City>> getAllCitiesForGivenUser(@PathVariable("userId") Integer userId) {
        List<CityList> cityLists = cityListService.getAllCityListsForAGivenUser(userId);
        List<City> cities = cityLists.stream().map(CityList::getCity).toList();

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/getCityListForGivenUserAndCity/{userId}/{cityId}")
    public ResponseEntity<List<CityList>> getCityListForGivenUserAndCity(@PathVariable("userId") Integer userId, @PathVariable("cityId") Integer cityId){
        List<CityList> cityLists = cityListService.getCityListByUserAndCity(userId, cityId);
        return new ResponseEntity<>(cityLists, HttpStatus.OK);
    }

    @PostMapping("/addCityList/{cityId}/{userId}")
    public ResponseEntity<CityList> addCityList(@PathVariable("cityId") Integer cityId, @PathVariable("userId") Long userId){
        City city = cityService.getCityById(cityId);
        User user = userService.getUserByUserId(userId);
        List<CityList> cityListsForGivenUser = cityListService.getAllCityListsForAGivenUser(Math.toIntExact(userId));

        if (cityListsForGivenUser.size() != 0){
            for (var current: cityListsForGivenUser){
                if (current.getCity() != city) {
                    CityList cityList = new CityList(0.0d, user, city);
                    CityList newCityList = cityListService.addCityList(cityList);

                    return new ResponseEntity<>(newCityList, HttpStatus.CREATED);
                }
            }
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            CityList cityList = new CityList(0.0d, user, city);
            CityList newCityList = cityListService.addCityList(cityList);

            return new ResponseEntity<>(newCityList, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/deleteCityList/{cityId}/{userId}")
    public ResponseEntity<?> deleteCityList(@PathVariable("cityId") Integer cityId, @PathVariable("userId") Long userId){
        cityListService.deleteCityList(cityId, Math.toIntExact(userId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
