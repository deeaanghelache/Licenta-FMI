package com.example.backend.journalPost;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/journalPost")
public class JournalPostController {
    private final JournalPostService journalPostService;

    public JournalPostController(JournalPostService journalPostService) {
        this.journalPostService = journalPostService;
    }

    @GetMapping("/getAllJournalPostsForAGivenUser/{userId}")
    private ResponseEntity<List<JournalPost>> getAllJournalPostsForAGivenUser(@PathVariable("userId") Integer userId){
        List<JournalPost> journalPosts = journalPostService.getAllJournalPostsForGivenUser(userId);
        return new ResponseEntity<>(journalPosts, HttpStatus.OK);
    }
}
