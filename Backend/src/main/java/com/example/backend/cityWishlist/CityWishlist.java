package com.example.backend.cityWishlist;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CityWishlist implements Serializable {
    @EmbeddedId
    private CityWishlistId CityWishListId;

    @Column(columnDefinition = "varchar(100)")
    private String status;

    // TODO: PUNE FK

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
