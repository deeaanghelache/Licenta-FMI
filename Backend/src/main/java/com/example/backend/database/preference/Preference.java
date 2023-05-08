package com.example.backend.database.preference;

import com.example.backend.database.user.User;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Preference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer preferenceId;

    @Column(columnDefinition = "varchar(100)")
    private String preferenceNameEng;

    @Column(columnDefinition = "varchar(100)")
    private String preferenceNameRom;

    // Foreign Keys

    // With User
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId")
    private User user;

    public Preference() {
    }

    public Preference(String preferenceNameEng, String preferenceNameRom) {
        this.preferenceNameEng = preferenceNameEng;
        this.preferenceNameRom = preferenceNameRom;
    }

    public Integer getPreferenceId() {
        return preferenceId;
    }

    public String getPreferenceNameEng() {
        return preferenceNameEng;
    }

    public void setPreferenceNameEng(String preferenceNameEng) {
        this.preferenceNameEng = preferenceNameEng;
    }

    public String getPreferenceNameRom() {
        return preferenceNameRom;
    }

    public void setPreferenceNameRom(String preferenceNameRom) {
        this.preferenceNameRom = preferenceNameRom;
    }
}
