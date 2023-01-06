package com.example.backend.airport;

import com.example.backend.city.City;
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

    // Foreign keys

    // With City
    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false, referencedColumnName = "cityId")
    private City city;

    public Airport() {
    }

    public Airport(String nameEng, String nameRom, Double distanceToCity) {
        this.nameEng = nameEng;
        this.nameRom = nameRom;
        this.distanceToCity = distanceToCity;
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

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", nameEng='" + nameEng + '\'' +
                ", nameRom='" + nameRom + '\'' +
                ", distanceToCity=" + distanceToCity +
                ", city=" + city +
                '}';
    }
}
