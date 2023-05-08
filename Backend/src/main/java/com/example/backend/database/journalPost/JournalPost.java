package com.example.backend.database.journalPost;

import com.example.backend.database.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class JournalPost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long journalPostId;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @Column(columnDefinition = "varchar(5000)")
    private String post;

    @Column(columnDefinition = "varchar(100)")
    private String photo;

    @Temporal(TemporalType.DATE)
    private LocalDate dateWritten;

    // Foreign Keys

    // With User
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId")
    private User user;

    public JournalPost() {
    }

    public JournalPost(Long journalPostId, String name, String post, String photo, LocalDate dateWritten) {
        this.journalPostId = journalPostId;
        this.name = name;
        this.post = post;
        this.photo = photo;
        this.dateWritten = dateWritten;
    }

    public Long getJournalPostId() {
        return journalPostId;
    }

    public void setJournalPostId(Long journalPostId) {
        this.journalPostId = journalPostId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getDateWritten() {
        return dateWritten;
    }

    public void setDateWritten(LocalDate dateWritten) {
        this.dateWritten = dateWritten;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JournalPost{" +
                "journalPostId=" + journalPostId +
                ", name='" + name + '\'' +
                ", post='" + post + '\'' +
                ", photo='" + photo + '\'' +
                ", dateWritten=" + dateWritten +
                ", user=" + user +
                '}';
    }
}
