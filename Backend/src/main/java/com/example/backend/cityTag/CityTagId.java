package com.example.backend.cityTag;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CityTagId implements Serializable {
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "tag_id")
    private Integer tagId;
}
