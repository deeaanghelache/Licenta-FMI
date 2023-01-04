package com.example.backend.cityWishlist;

import com.example.backend.city.City;
import com.example.backend.user.User;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CityWishlist implements Serializable {
    @EmbeddedId
    private CityWishlistId CityWishListId;

    @Column(columnDefinition = "varchar(100)")
    private String status;

    // Foreign Keys

    // With City
    @MapsId("cityId")
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    // With User
    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public CityWishlist() {
    }

    public CityWishlist(String status) {
        this.status = status;
    }

    public CityWishlistId getCityWishListId() {
        return CityWishListId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CityWishlist{" +
                "CityWishListId=" + CityWishListId +
                ", status='" + status + '\'' +
                '}';
    }
}
