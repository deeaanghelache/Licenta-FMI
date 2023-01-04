package com.example.backend.user;

import com.example.backend.cityWishlist.CityWishlist;
import com.example.backend.landmarkList.LandmarkList;
import com.example.backend.preference.Preference;
import com.example.backend.userRole.UserRole;
import com.example.backend.role.Role;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer userId;

    @Column(columnDefinition = "varchar(100)")
    private String firstName;

    @Column(columnDefinition = "varchar(100)")
    private String lastName;

    @Column(columnDefinition = "varchar(100)")
    private String email;

    @Column(columnDefinition = "varchar(100)")
    private String username;

    @Column(columnDefinition = "varchar(200)")
    private String password;

    @Column(columnDefinition = "varchar(100)")
    private String photo;

    // Foreign Keys

    // With UserRole
    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles;

    // With Preference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Preference> preferences;

    // With LandmarkWishlist
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<LandmarkList> landmarkLists;

    // With CityWishList
    @OneToMany(mappedBy = "user")
    private Set<CityWishlist> cityWishlists;

    public User() {
    }

    public User(String firstName, String lastName, String email, String username, String password, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.photo = photo;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + userId +
                ", FirstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", Email='" + email + '\'' +
                ", Username='" + username + '\'' +
                ", Password='" + password + '\'' +
                ", Photo='" + photo + '\'' +
                '}';
    }
}
