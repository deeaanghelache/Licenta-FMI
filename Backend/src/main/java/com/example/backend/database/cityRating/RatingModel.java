package com.example.backend.database.cityRating;

import java.io.Serializable;

public class RatingModel implements Serializable {
    private Integer cityId;
    private Double rating;

    public RatingModel(Integer cityId, Double rating) {
        this.cityId = cityId;
        this.rating = rating;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RatingModel{" +
                "cityId=" + cityId +
                ", rating=" + rating +
                '}';
    }
}
