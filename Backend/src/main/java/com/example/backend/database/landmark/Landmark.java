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

    @Column(columnDefinition = "varchar(500)")
    private String name;

    @Column(columnDefinition = "varchar(2000)")
    private String descriptionEng;

    @Column(columnDefinition = "varchar(2000)")
    private String descriptionRom;

    @Column(columnDefinition = "varchar(200)")
    private String typeEng;

    @Column(columnDefinition = "varchar(200)")
    private String typeRom;

    @Column(columnDefinition="Decimal(10,6)")
    private Double latitude;

    @Column(columnDefinition="Decimal(10,6)")
    private Double longitude;

    @Column(columnDefinition = "varchar(100)")
    private String photo;

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

    public Landmark(Integer landmarkId, String name, String descriptionEng, String descriptionRom, String typeEng, String typeRom, Double latitude, Double longitude, String photo) {
        this.landmarkId = landmarkId;
        this.name = name;
        this.descriptionEng = descriptionEng;
        this.descriptionRom = descriptionRom;
        this.typeEng = typeEng;
        this.typeRom = typeRom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photo = photo;
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

    public void setLandmarkId(Integer landmarkId) {
        this.landmarkId = landmarkId;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCity(City city) {
        this.city = city;
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
