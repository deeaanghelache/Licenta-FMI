package com.example.backend.city;

import com.example.backend.airport.Airport;
import com.example.backend.cityTag.CityTag;
import com.example.backend.cityWishlist.CityWishlist;
import com.example.backend.landmark.Landmark;
import com.example.backend.cityList.CityList;
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

    @Column(columnDefinition = "varchar(1000)")
    private String briefHistoryEng;

    @Column(columnDefinition = "varchar(1000)")
    private String briefHistoryRom;

    @Column(columnDefinition = "varchar(50)")
    private String currencyName;

    @Column(columnDefinition = "varchar(100)")
    private String photo;

    // Foreign Keys

    // With Airport
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<Airport> airports;

    // With Landmark
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<Landmark> landmarks;

    // With LandmarkList
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<CityList> cityLists;

    // With CityWishList
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<CityWishlist> cityWishlists;

    // With CityTag
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<CityTag> cityTags;

    public City() {
    }

    public City(String nameEng, String nameRom, String countryEng, String countryRom, String briefHistoryEng, String briefHistoryRom, String currencyName, String photo) {
        this.nameEng = nameEng;
        this.nameRom = nameRom;
        this.countryEng = countryEng;
        this.countryRom = countryRom;
        this.briefHistoryEng = briefHistoryEng;
        this.briefHistoryRom = briefHistoryRom;
        this.currencyName = currencyName;
        this.photo = photo;
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
                ", airports=" + airports +
                ", landmarks=" + landmarks +
                ", landmarkLists=" + cityLists +
                ", cityWishlists=" + cityWishlists +
                ", cityTags=" + cityTags +
                '}';
    }
}
