package com.example.backend.database.cityRating;

import java.io.Serializable;

public class CityRatingModel implements Serializable {
    private Integer cityId;
    private String nameEng;
    private String nameRom;
    private String countryEng;
    private String countryRom;
    private Double averageRating;
    private String photo;

    public CityRatingModel(Integer cityId, String nameEng, String nameRom, String countryEng, String countryRom, Double averageRating, String photo) {
        this.cityId = cityId;
        this.nameEng = nameEng;
        this.nameRom = nameRom;
        this.countryEng = countryEng;
        this.countryRom = countryRom;
        this.averageRating = averageRating;
        this.photo = photo;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameRom() {
        return nameRom;
    }

    public void setNameRom(String nameRom) {
        this.nameRom = nameRom;
    }

    public String getCountryEng() {
        return countryEng;
    }

    public void setCountryEng(String countryEng) {
        this.countryEng = countryEng;
    }

    public String getCountryRom() {
        return countryRom;
    }

    public void setCountryRom(String countryRom) {
        this.countryRom = countryRom;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "CityRatingModel{" +
                "cityId=" + cityId +
                ", nameEng='" + nameEng + '\'' +
                ", nameRom='" + nameRom + '\'' +
                ", countryEng='" + countryEng + '\'' +
                ", countryRom='" + countryRom + '\'' +
                ", averageRating=" + averageRating +
                ", photo='" + photo + '\'' +
                '}';
    }
}
