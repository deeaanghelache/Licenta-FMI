package com.example.backend.database.listOfLandmarks;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ListOfLandmarksId implements Serializable {
    @Column(name = "landmark_id")
    private Integer landmarkId;

    @Column(name = "landmark_List_id")
    private Integer landmarkListId;
}
