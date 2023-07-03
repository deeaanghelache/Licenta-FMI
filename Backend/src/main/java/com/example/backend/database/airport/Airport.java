package com.example.backend.database.airport;

import com.example.backend.database.city.City;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Airport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer airportId;

    @Column(columnDefinition = "varchar(500)")
    private String nameEng;

    @Column(columnDefinition = "varchar(500)")
    private String nameRom;

    @Column(columnDefinition = "Decimal(5,2)")
    private Double distanceToCity;

    @Column(columnDefinition="Decimal(10,6)")
    private Double latitude;

    @Column(columnDefinition="Decimal(10,6)")
    private Double longitude;

    // Foreign keys

    // With City
    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false, referencedColumnName = "cityId")
    private City city;

    public Airport() {
    }

    public Airport(Integer airportId, String nameEng, String nameRom, Double distanceToCity, Double latitude, Double longitude) {
        this.airportId = airportId;
        this.nameEng = nameEng;
        this.nameRom = nameRom;
        this.distanceToCity = distanceToCity;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getAirportId() {
        return airportId;
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

    public Double getDistanceToCity() {
        return distanceToCity;
    }

    public void setDistanceToCity(Double distanceToCity) {
        this.distanceToCity = distanceToCity;
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

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", nameEng='" + nameEng + '\'' +
                ", nameRom='" + nameRom + '\'' +
                ", distanceToCity=" + distanceToCity +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
//                ", city=" + city.getCityId() +
                '}';
    }
}
