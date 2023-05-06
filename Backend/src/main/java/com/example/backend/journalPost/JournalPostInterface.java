package com.example.backend.journalPost;

import com.example.backend.preference.Preference;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JournalPostInterface extends JpaRepository<JournalPost, Integer> {
    @Query(value = "select journal_post.journal_post_id, journal_post.post, journal_post.photo, journal_post.photo, journal_post.date_written, journal_post.user_id" +
            " from journal_post JOIN user ON (user.user_id = journal_post.user_id)" +
            " where journal_post.user_id = :userId", nativeQuery = true)
    List<JournalPost> queryBy(@Param("userId") Integer userId);
}
