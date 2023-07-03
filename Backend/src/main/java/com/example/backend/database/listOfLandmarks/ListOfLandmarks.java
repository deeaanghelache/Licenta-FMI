package com.example.backend.database.listOfLandmarks;

import com.example.backend.database.landmark.Landmark;
import com.example.backend.database.cityList.CityList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class ListOfLandmarks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer listOfLandmarksId;

    @Column(columnDefinition = "integer")
    private Integer priority;

    // Foreign Keys

    // With Landmark
    @ManyToOne(cascade = CascadeType.ALL)
//    @MapsId("landmarkId")
    @JoinColumn(name = "landmarkId", referencedColumnName = "landmarkId", nullable = false)
    private Landmark landmark;

    // With City List
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
//    @MapsId("cityListId")
    @JoinColumn(name = "cityListId", referencedColumnName = "cityListId", nullable = false)
    private CityList cityList;

    public ListOfLandmarks() {
    }

    public ListOfLandmarks(Integer priority, Landmark landmark, CityList cityList) {
        this.priority = priority;
        this.landmark = landmark;
        this.cityList = cityList;
    }

    public ListOfLandmarks(Integer priority) {
        this.priority = priority;
    }

    public Integer getListOfLandmarksId() {
        return listOfLandmarksId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setListOfLandmarksId(Integer listOfLandmarksId) {
        this.listOfLandmarksId = listOfLandmarksId;
    }

    public Landmark getLandmark() {
        return landmark;
    }

    public void setLandmark(Landmark landmark) {
        this.landmark = landmark;
    }

    public CityList getCityList() {
        return cityList;
    }

    public void setCityList(CityList cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "ListOfLandmarks{" +
                "listOfLandmarksId=" + listOfLandmarksId +
                ", priority=" + priority +
//                ", landmark=" + landmark.getLandmarkId() +
//                ", cityList=" + cityList.getCityListId() +
                '}';
    }
}
