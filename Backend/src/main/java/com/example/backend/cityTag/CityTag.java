package com.example.backend.cityTag;

import com.example.backend.city.City;
import com.example.backend.tag.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    public CityTagId getCityTagId() {
        return cityTagId;
    }

    public City getCity() {
        return city;
    }

    public Tag getTag() {
        return tag;
    }

    public void setCityTagId(CityTagId cityTagId) {
        this.cityTagId = cityTagId;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "CityTag{" +
                "cityTagId=" + cityTagId +
                '}';
    }
}
