package com.example.backend.airport;

import jakarta.persistence.*;

import java.io.Serializable;

// TODO: pune FK
@Entity
public class Airport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer AirportId;

    @Column(columnDefinition = "varchar(500)")
    private String nameEng;

    @Column(columnDefinition = "varchar(500)")
    private String nameRom;

    public Airport() {
    }

    public Airport(String nameEng, String nameRom) {
        this.nameEng = nameEng;
        this.nameRom = nameRom;
    }

    public Integer getAirportId() {
        return AirportId;
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

    @Override
    public String toString() {
        return "Airport{" +
                "AirportId=" + AirportId +
                ", nameEng='" + nameEng + '\'' +
                ", nameRom='" + nameRom + '\'' +
                '}';
    }
}
