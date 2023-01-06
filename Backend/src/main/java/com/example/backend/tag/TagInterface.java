package com.example.backend.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagInterface extends JpaRepository<Tag, Integer> {
    Optional<Tag> findTagByTagId(Integer tagId);
    Optional<Tag> findTagByTagNameEng(String englishTagName);
    Optional<Tag> findTagByTagNameRom(String romanianTagName);

    void deleteTagByTagId(Integer tagId);
    void deleteTagByTagNameEng(String englishTagName);
    void deleteTagByTagNameRom(String romanianTagName);
}
