package com.example.backend.tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/findAllTags")
    public ResponseEntity<List<Tag>> getAllTags(){
        List<Tag> tags = tagService.getAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/findTagById/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") Integer tagId){
        Tag tag = tagService.findTagById(tagId);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    // TODO: post si put

    @DeleteMapping("/deleteAllTags")
    public ResponseEntity<?> deleteAllTags(){
        tagService.deleteAllTags();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteTagById/{id}")
    public ResponseEntity<?> deleteTagById(@PathVariable("id") Integer tagId){
        tagService.deleteTagByTagId(tagId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
