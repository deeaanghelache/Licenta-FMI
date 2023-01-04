package com.example.backend.landmarkList;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class LandmarkList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer landmarkListId;

    @Column(columnDefinition="Decimal(10,2)")
    private Double totalPrice;

    // TODO: pune fk

    public LandmarkList() {
    }

    public LandmarkList(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getLandmarkListId() {
        return landmarkListId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "LandmarkList{" +
                "landmarkListId=" + landmarkListId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
