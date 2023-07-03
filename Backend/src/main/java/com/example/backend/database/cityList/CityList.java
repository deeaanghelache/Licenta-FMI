package com.example.backend.database.cityList;

import com.example.backend.database.city.City;
import com.example.backend.database.listOfLandmarks.ListOfLandmarks;
import com.example.backend.database.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class CityList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer cityListId;

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
    @OneToMany(mappedBy = "cityList", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ListOfLandmarks> listOfLandmarks;

    public CityList() {
    }

    public CityList(Double totalPrice, User user, City city) {
        this.totalPrice = totalPrice;
        this.user = user;
        this.city = city;
    }

    public CityList(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCityListId() {
        return cityListId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public City getCity() {
        return city;
    }

    public Set<ListOfLandmarks> getListOfLandmarks() {
        return listOfLandmarks;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityList{" +
                "cityListId=" + cityListId +
                ", totalPrice=" + totalPrice +
//                ", user=" + user +
//                ", city=" + city +
                '}';
    }
}
