package com.example.backend.database.landmark;

import com.example.backend.database.city.City;
import com.example.backend.database.listOfLandmarks.ListOfLandmarks;
import com.example.backend.database.price.Price;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;


@Entity
public class Landmark implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer landmarkId;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @Column(columnDefinition = "varchar(1000)")
    private String descriptionEng;

    @Column(columnDefinition = "varchar(1000)")
    private String descriptionRom;

    @Column(columnDefinition = "varchar(200)")
    private String typeEng;

    @Column(columnDefinition = "varchar(200)")
    private String typeRom;

    // Foreign Keys

    // With Price
    @OneToOne(mappedBy = "landmark", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Price price;

    // With City
    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false, referencedColumnName = "cityId")
    City city;

    // With ListOfLandmarks
    @OneToMany(mappedBy = "landmark", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ListOfLandmarks> listOfLandmarks;

    public Landmark() {
    }

    public Landmark(String name, String descriptionEng, String descriptionRom, String typeEng, String typeRom) {
        this.name = name;
        this.descriptionEng = descriptionEng;
        this.descriptionRom = descriptionRom;
        this.typeEng = typeEng;
        this.typeRom = typeRom;
    }

    public Integer getLandmarkId() {
        return landmarkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public String getDescriptionRom() {
        return descriptionRom;
    }

    public void setDescriptionRom(String descriptionRom) {
        this.descriptionRom = descriptionRom;
    }

    public String getTypeEng() {
        return typeEng;
    }

    public void setTypeEng(String typeEng) {
        this.typeEng = typeEng;
    }

    public String getTypeRom() {
        return typeRom;
    }

    public void setTypeRom(String typeRom) {
        this.typeRom = typeRom;
    }

    @Override
    public String toString() {
        return "Landmark{" +
                "landmarkId=" + landmarkId +
                ", name='" + name + '\'' +
                ", descriptionEng='" + descriptionEng + '\'' +
                ", descriptionRom='" + descriptionRom + '\'' +
                ", typeEng='" + typeEng + '\'' +
                ", typeRom='" + typeRom + '\'' +
//                ", price=" + price +
//                ", city=" + city +
                '}';
    }
}
