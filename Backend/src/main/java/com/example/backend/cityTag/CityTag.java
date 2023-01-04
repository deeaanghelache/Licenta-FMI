package com.example.backend.cityTag;

import com.example.backend.city.City;
import com.example.backend.tag.Tag;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CityTag implements Serializable {
    @EmbeddedId
    private CityTagId cityTagId;

    // Foreign Keys

    // With City
    @ManyToOne
    @MapsId("cityId")
    @JoinColumn(name = "city_id", referencedColumnName = "cityId")
    private City city;

    // With Tag
    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id", referencedColumnName = "tagId")
    private Tag tag;

    public CityTag() {
    }

    @Override
    public String toString() {
        return "CityTag{" +
                "cityTagId=" + cityTagId +
                '}';
    }
}
