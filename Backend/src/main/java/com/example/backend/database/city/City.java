package com.example.backend.database.city;

import com.example.backend.database.airport.Airport;
import com.example.backend.database.cityRating.CityRating;
import com.example.backend.database.cityTag.CityTag;
//import com.example.backend.cityWishlist.CityWishlist;
import com.example.backend.database.landmark.Landmark;
import com.example.backend.database.cityList.CityList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer cityId;

    @Column(columnDefinition = "varchar(100)")
    private String nameEng;

    @Column(columnDefinition = "varchar(100)")
    private String nameRom;

    @Column(columnDefinition = "varchar(300)")
    private String countryEng;

    @Column(columnDefinition = "varchar(300)")
    private String countryRom;

    @Column(columnDefinition = "varchar(5000)")
    private String briefHistoryEng;

    @Column(columnDefinition = "varchar(5000)")
    private String briefHistoryRom;

    @Column(columnDefinition = "varchar(50)")
    private String currencyName;

    @Column(columnDefinition = "varchar(100)")
    private String photo;

    @Column(columnDefinition="Decimal(10,6)")
    private Double latitude;

    @Column(columnDefinition="Decimal(10,6)")
    private Double longitude;

    // Foreign Keys

    // With Airport
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Airport> airports;

    // With Landmark
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Landmark> landmarks;

    // With CityList
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CityList> cityLists;

    // With CityTag
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CityTag> cityTags;

    // With CityRating
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CityRating> cityRatings;

    public City() {
    }

    public City(Integer cityId, String nameEng, String nameRom, String countryEng, String countryRom, String briefHistoryEng, String briefHistoryRom, String currencyName, String photo, Double latitude, Double longitude) {
        this.cityId = cityId;
        this.nameEng = nameEng;
        this.nameRom = nameRom;
        this.countryEng = countryEng;
        this.countryRom = countryRom;
        this.briefHistoryEng = briefHistoryEng;
        this.briefHistoryRom = briefHistoryRom;
        this.currencyName = currencyName;
        this.photo = photo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getCityId() {
        return cityId;
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

    public String getBriefHistoryEng() {
        return briefHistoryEng;
    }

    public void setBriefHistoryEng(String briefHistoryEng) {
        this.briefHistoryEng = briefHistoryEng;
    }

    public String getBriefHistoryRom() {
        return briefHistoryRom;
    }

    public void setBriefHistoryRom(String briefHistoryRom) {
        this.briefHistoryRom = briefHistoryRom;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", nameEng='" + nameEng + '\'' +
                ", nameRom='" + nameRom + '\'' +
                ", countryEng='" + countryEng + '\'' +
                ", countryRom='" + countryRom + '\'' +
                ", briefHistoryEng='" + briefHistoryEng + '\'' +
                ", briefHistoryRom='" + briefHistoryRom + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", photo='" + photo + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
//                ", airports=" + airports +
//                ", landmarks=" + landmarks +
//                ", cityLists=" + cityLists +
//                ", cityWishlists=" + cityWishlists +
//                ", cityTags=" + cityTags +
                '}';
    }
}
