package com.example.backend.user;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer UserId;

    @Column(columnDefinition = "varchar(100)")
    private String FirstName;

    @Column(columnDefinition = "varchar(100)")
    private String LastName;

    @Column(columnDefinition = "varchar(100)")
    private String Email;

    @Column(columnDefinition = "varchar(100)")
    private String Username;

    @Column(columnDefinition = "varchar(200)")
    private String Password;

    @Column(columnDefinition = "varchar(100)")
    private String Photo;

    public User() {
    }

    public User(String firstName, String lastName, String email, String username, String password, String photo) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Username = username;
        Password = password;
        Photo = photo;
    }

    public Integer getUserId() {
        return UserId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Photo='" + Photo + '\'' +
                '}';
    }
}
