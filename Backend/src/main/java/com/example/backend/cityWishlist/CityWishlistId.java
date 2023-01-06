package com.example.backend.cityWishlist;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

import java.io.Serializable;

@Embeddable
public class CityWishlistId implements Serializable {
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "user_id")
    private Integer userId;
}
