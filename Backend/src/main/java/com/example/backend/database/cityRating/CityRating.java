package com.example.backend.database.cityRating;

import com.example.backend.database.city.City;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CityRating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer cityRatingId;

    @Column(columnDefinition="Decimal(3,2)")
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false, referencedColumnName = "cityId")
    City city;

    public CityRating() {
    }

    public CityRating(Double rating) {
        this.rating = rating;
    }

    public Integer getCityRatingId() {
        return cityRatingId;
    }

    public void setCityRatingId(Integer cityRatingId) {
        this.cityRatingId = cityRatingId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityRating{" +
                "cityRatingId=" + cityRatingId +
                ", rating=" + rating +
//                ", city=" + city +
                '}';
    }
}
