package com.example.backend.database.journalPost;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JournalPostModel {
    @JsonProperty
    private String name;

    @JsonProperty
    private String post;

    public JournalPostModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "JournalPostModel{" +
                "name='" + name + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}
