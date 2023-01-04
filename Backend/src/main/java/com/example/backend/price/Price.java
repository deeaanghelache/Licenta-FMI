package com.example.backend.price;

import com.example.backend.landmark.Landmark;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Price implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private Integer landmarkId;

    @Column(columnDefinition = "integer")
    private Integer full;

    @Column(columnDefinition = "integer")
    private Integer discount;

    @Column(columnDefinition = "varchar(500)")
    private String freeTicketsRequirementsEng;

    @Column(columnDefinition = "varchar(500)")
    private String getFreeTicketsRequirementsRom;

    @OneToOne
    @MapsId
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    public Price() {
    }

    public Price(Integer full, Integer discount, String freeTicketsRequirementsEng, String getFreeTicketsRequirementsRom, Landmark landmark) {
        this.full = full;
        this.discount = discount;
        this.freeTicketsRequirementsEng = freeTicketsRequirementsEng;
        this.getFreeTicketsRequirementsRom = getFreeTicketsRequirementsRom;
        this.landmark = landmark;
    }

    public Integer getLandmarkId() {
        return landmarkId;
    }

    public Integer getFull() {
        return full;
    }

    public void setFull(Integer full) {
        this.full = full;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getFreeTicketsRequirementsEng() {
        return freeTicketsRequirementsEng;
    }

    public void setFreeTicketsRequirementsEng(String freeTicketsRequirementsEng) {
        this.freeTicketsRequirementsEng = freeTicketsRequirementsEng;
    }

    public String getGetFreeTicketsRequirementsRom() {
        return getFreeTicketsRequirementsRom;
    }

    public void setGetFreeTicketsRequirementsRom(String getFreeTicketsRequirementsRom) {
        this.getFreeTicketsRequirementsRom = getFreeTicketsRequirementsRom;
    }

    public Landmark getLandmark() {
        return landmark;
    }

    public void setLandmark(Landmark landmark) {
        this.landmark = landmark;
    }

    @Override
    public String toString() {
        return "Price{" +
                "landmarkId=" + landmarkId +
                ", full=" + full +
                ", discount=" + discount +
                ", freeTicketsRequirementsEng='" + freeTicketsRequirementsEng + '\'' +
                ", getFreeTicketsRequirementsRom='" + getFreeTicketsRequirementsRom + '\'' +
                ", landmark=" + landmark +
                '}';
    }
}
