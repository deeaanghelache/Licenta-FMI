package com.example.backend.listOfLandmarks;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class ListOfLandmarks implements Serializable {
    @EmbeddedId
    private ListOfLandmarksId listOfLandmarksId;

    @Column(columnDefinition = "integer")
    private Integer priority;

    // TODO: PUNE Fk

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
