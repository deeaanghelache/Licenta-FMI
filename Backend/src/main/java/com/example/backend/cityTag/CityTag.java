package com.example.backend.cityTag;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class CityTag implements Serializable {
    @EmbeddedId
    private CityTagId cityTagId;

    // TODO: pune FK

    public CityTag() {
    }

    @Override
    public String toString() {
        return "CityTag{" +
                "cityTagId=" + cityTagId +
                '}';
    }
}
