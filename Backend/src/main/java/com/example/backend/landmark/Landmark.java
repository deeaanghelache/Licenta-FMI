package com.example.backend.landmark;

import com.example.backend.city.City;
import com.example.backend.listOfLandmarks.ListOfLandmarks;
import com.example.backend.price.Price;
import com.example.backend.user.User;
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
    private Price price;

    // With City
    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false, referencedColumnName = "cityId")
    City city;

    // With ListOfLandmarks
    @OneToMany(mappedBy = "landmark")
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
}
