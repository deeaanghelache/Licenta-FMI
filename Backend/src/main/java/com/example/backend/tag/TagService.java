package com.example.backend.tag;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TagService {
    private final TagInterface tagInterface;

    @Autowired
    public TagService(TagInterface tagInterface) {
        this.tagInterface = tagInterface;
    }

    // FIND(GET)
    public List<Tag> getAllTags(){
        return tagInterface.findAll();
    }

    public Tag findTagById(Integer tagId){
        return tagInterface.findTagByTagId(tagId).orElseThrow(() -> new TagIdNotFoundException("Couldn't find any tag with the id: " + tagId));
    }

    public Tag findTagByTagName(String tagName){
        return tagInterface.findTagByTagNameEng(tagName).orElseThrow(() -> new TagNameNotFound("Couldn't find any tag with the name: " + tagName));
    }

    // POST
    public Tag addTag(Tag tag){
        return tagInterface.save(tag);
    }

    // PUT
    // TODO

    // DELETE
    public void deleteAllTags(){
        tagInterface.deleteAll();
    }

    public void deleteTagByTagId(Integer tagId){
        tagInterface.deleteTagByTagId(tagId);
    }
}
