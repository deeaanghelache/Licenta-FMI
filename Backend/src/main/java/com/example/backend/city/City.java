package com.example.backend.city;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
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

    public City() {
    }

    public City(String nameEng, String nameRom, String countryEng, String countryRom, String briefHistoryEng, String briefHistoryRom, String currencyName) {
        this.nameEng = nameEng;
        this.nameRom = nameRom;
        this.countryEng = countryEng;
        this.countryRom = countryRom;
        this.briefHistoryEng = briefHistoryEng;
        this.briefHistoryRom = briefHistoryRom;
        this.currencyName = currencyName;
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

    @Override
    public String toString() {
        return "City{" +
                "CityId=" + cityId +
                ", nameEng='" + nameEng + '\'' +
                ", nameRom='" + nameRom + '\'' +
                ", countryEng='" + countryEng + '\'' +
                ", countryRom='" + countryRom + '\'' +
                ", briefHistoryEng='" + briefHistoryEng + '\'' +
                ", briefHistoryRom='" + briefHistoryRom + '\'' +
                ", currencyName='" + currencyName + '\'' +
                '}';
    }
}
