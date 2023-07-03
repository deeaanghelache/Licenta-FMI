package com.example.backend.database.cityRating;

import com.example.backend.database.city.City;
import com.example.backend.database.city.CityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CityRatingService {
    private CityRatingInterface cityRatingInterface;
    private final CityService cityService;


    @Autowired
    public CityRatingService(CityRatingInterface cityRatingInterface, CityService cityService) {
        this.cityRatingInterface = cityRatingInterface;
        this.cityService = cityService;
    }

    public List<RatingModel> mapResultsToRatingModels(List<Object[]> resultList) {
        List<RatingModel> ratingModels = new ArrayList<>();

        for (Object[] result : resultList) {
            Integer cityId = (Integer) result[0];
            Double rating = (Double) result[1];

            RatingModel ratingModel = new RatingModel(cityId, rating);
            ratingModels.add(ratingModel);
        }

        return ratingModels;
    }

    public List<CityRatingModel> getCityRatings(){
        var ratingsAsObjects = cityRatingInterface.getCityAverageRatings();
        var ratings = mapResultsToRatingModels(ratingsAsObjects);

        List<CityRatingModel> cityRatingModels = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            var currentRating = ratings.get(i);
            var currentCity = cityService.getCityById(currentRating.getCityId());
            CityRatingModel currentCityRatingModel = new CityRatingModel(currentCity.getCityId(), currentCity.getNameEng(), currentCity.getNameRom(), currentCity.getCountryEng(), currentCity.getCountryRom(), currentRating.getRating(), currentCity.getPhoto());
            cityRatingModels.add(currentCityRatingModel);
        }
        return cityRatingModels;
    }

    public CityRating addRating(Integer cityId, Integer rating){
        CityRating newCityRating = new CityRating((double) rating);
        City currentCity = cityService.getCityById(cityId);
        newCityRating.setCity(currentCity);
        return cityRatingInterface.save(newCityRating);
    }
}
