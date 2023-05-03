package com.example.backend.cityList;

import com.example.backend.city.City;
import com.example.backend.user.User;
import com.example.backend.city.CityService;
import com.example.backend.user.UserService;
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
        System.out.println(userId);
        List<CityList> cityLists = cityListService.getAllCityListsForAGivenUser(userId);
        List<City> cities = cityLists.stream().map(CityList::getCity).toList();

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping("/addCityList/{cityId}/{userId}")
    public ResponseEntity<CityList> addCityList(@PathVariable("cityId") Integer cityId, @PathVariable("userId") Long userId){
        City city = cityService.getCityById(cityId);
        User user = userService.getUserByUserId(userId);
        CityList cityList = new CityList(0.0d, user, city);
        CityList newCityList = cityListService.addCityList(cityList);

        return new ResponseEntity<>(newCityList, HttpStatus.CREATED);
    }
}
