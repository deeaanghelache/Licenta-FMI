package com.example.backend.landmarkList;

import com.example.backend.city.City;
import com.example.backend.listOfLandmarks.ListOfLandmarks;
import com.example.backend.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class LandmarkList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer landmarkListId;

    @Column(columnDefinition="Decimal(5,2)")
    private Double totalPrice;

    // Foreign Keys

    // With User
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId")
    private User user;

    // With City
    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false, referencedColumnName = "cityId")
    private City city;

    // With ListOfLandmarks
    @OneToMany(mappedBy = "landmarkList")
    private Set<ListOfLandmarks> listOfLandmarks;

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
