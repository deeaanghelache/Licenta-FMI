package com.example.backend.tag;

import jakarta.persistence.*;

import java.io.Serializable;

// TODO: FK
@Entity
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer tagId;

    @Column(columnDefinition = "varchar(200)")
    private String tagNameEng;

    @Column(columnDefinition = "varchar(500)")
    private String tagNameRom;

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
