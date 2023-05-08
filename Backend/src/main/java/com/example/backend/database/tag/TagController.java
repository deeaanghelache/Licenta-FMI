package com.example.backend.database.tag;

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

    @GetMapping("/findTagByName/{name}")
    public ResponseEntity<Integer> getTagIdByName(@PathVariable("name") String name){
        Tag tag = tagService.findTagByTagName(name);
        return new ResponseEntity<>(tag.getTagId(), HttpStatus.OK);
    }

    // TODO: post si put

    // POST
    @PostMapping("/addTag")
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag) {
        Tag newTag = tagService.addTag(tag);
        return new ResponseEntity<>(newTag, HttpStatus.CREATED);
    }

    // DELETE
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
