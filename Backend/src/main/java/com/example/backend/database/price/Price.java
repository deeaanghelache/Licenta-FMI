package com.example.backend.database.price;

import com.example.backend.database.landmark.Landmark;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Price implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private Integer landmarkId;

    @Column(columnDefinition="Decimal(5,2)")
    private Double full;

    @Column(columnDefinition="Decimal(5,2)")
    private Double discount;

    @Column(columnDefinition = "varchar(500)")
    private String freeTicketsRequirementsEng;

    @Column(columnDefinition = "varchar(500)")
    private String getFreeTicketsRequirementsRom;

    // Foreign keys
    // With Landmark
    @OneToOne
    @MapsId
    @JoinColumn(name = "landmark_id")
    private Landmark landmark;

    public Price() {
    }

    public Price(Double full, Double discount, String freeTicketsRequirementsEng, String getFreeTicketsRequirementsRom, Landmark landmark) {
        this.full = full;
        this.discount = discount;
        this.freeTicketsRequirementsEng = freeTicketsRequirementsEng;
        this.getFreeTicketsRequirementsRom = getFreeTicketsRequirementsRom;
        this.landmark = landmark;
    }

    public Integer getLandmarkId() {
        return landmarkId;
    }

    public Double getFull() {
        return full;
    }

    public void setFull(Double full) {
        this.full = full;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
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
