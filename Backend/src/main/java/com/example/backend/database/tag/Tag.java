package com.example.backend.database.tag;

import com.example.backend.database.cityTag.CityTag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer tagId;

    @Column(columnDefinition = "varchar(200)", unique = true)
    private String tagNameEng;

    @Column(columnDefinition = "varchar(200)", unique = true)
    private String tagNameRom;

    // Foreign Keys

    // With CityTag
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CityTag> cityTags;

    public Tag() {
    }

    public Tag(String tagNameEng, String tagNameRom) {
        this.tagNameEng = tagNameEng;
        this.tagNameRom = tagNameRom;
    }

    public Integer getTagId() {
        return tagId;
    }

    public String getTagNameEng() {
        return tagNameEng;
    }

    public void setTagNameEng(String tagNameEng) {
        this.tagNameEng = tagNameEng;
    }

    public String getTagNameRom() {
        return tagNameRom;
    }

    public void setTagNameRom(String tagNameRom) {
        this.tagNameRom = tagNameRom;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagNameEng='" + tagNameEng + '\'' +
                ", tagNameRom='" + tagNameRom + '\'' +
                '}';
    }
}
