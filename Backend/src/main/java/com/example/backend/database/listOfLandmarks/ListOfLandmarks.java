package com.example.backend.database.listOfLandmarks;

import com.example.backend.database.landmark.Landmark;
import com.example.backend.database.cityList.CityList;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class ListOfLandmarks implements Serializable {
    @EmbeddedId
    private ListOfLandmarksId listOfLandmarksId;

    @Column(columnDefinition = "integer")
    private Integer priority;

    // Foreign Keys

    // With Landmark
    @ManyToOne
    @MapsId("landmarkId")
    @JoinColumn(name = "landmark_id", referencedColumnName = "landmarkId")
    private Landmark landmark;

    // With Landmark List
    @ManyToOne
    @MapsId("cityListId")
    @JoinColumn(name = "city_list_id", referencedColumnName = "cityListId")
    private CityList cityList;

    public ListOfLandmarks() {
    }

    public ListOfLandmarks(Integer priority) {
        this.priority = priority;
    }

    public ListOfLandmarksId getListOfLandmarksId() {
        return listOfLandmarksId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setListOfLandmarksId(ListOfLandmarksId listOfLandmarksId) {
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
                '}';
    }
}
