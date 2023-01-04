package com.example.backend.listOfLandmarks;

import com.example.backend.landmark.Landmark;
import com.example.backend.landmarkList.LandmarkList;
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
    @MapsId("landmarkListId")
    @JoinColumn(name = "landmark_list_id", referencedColumnName = "landmarkListId")
    private LandmarkList landmarkList;

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

    @Override
    public String toString() {
        return "ListOfLandmarks{" +
                "listOfLandmarksId=" + listOfLandmarksId +
                ", priority=" + priority +
                '}';
    }
}
