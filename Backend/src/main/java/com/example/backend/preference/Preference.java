package com.example.backend.preference;

import com.example.backend.user.User;
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
    private String getPreferenceNameRom;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    public Preference() {
    }

    public Preference(String preferenceNameEng, String getPreferenceNameRom) {
        this.preferenceNameEng = preferenceNameEng;
        this.getPreferenceNameRom = getPreferenceNameRom;
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

    public String getGetPreferenceNameRom() {
        return getPreferenceNameRom;
    }

    public void setGetPreferenceNameRom(String getPreferenceNameRom) {
        this.getPreferenceNameRom = getPreferenceNameRom;
    }
}
