package com.example.backend.database.user;

import com.example.backend.database.cityList.CityList;
import com.example.backend.database.journalPost.JournalPost;
import com.example.backend.database.preference.Preference;
import com.example.backend.database.userRole.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long userId;

    @Column(columnDefinition = "varchar(100)")
    private String firstName;

    @Column(columnDefinition = "varchar(100)")
    private String lastName;

    @Column(columnDefinition = "varchar(100)", unique = true)
    private String email;

    @Column(columnDefinition = "varchar(100)", unique = true)
    private String username;

    @Column(columnDefinition = "varchar(200)")
    private String password;

    @Column(columnDefinition = "varchar(100)")
    private String photo;

//    private MultipartFile photo;

    // Foreign Keys

    // With UserRole
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles;

    // With Preference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Preference> preferences;

    // With CityList
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CityList> cityLists;

    // With JournalPost
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JournalPost> journalPosts;

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

    public Long getUserId() {
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
