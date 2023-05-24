package com.example.backend.database.journalPost;

import com.example.backend.database.user.User;
import com.example.backend.database.user.UserRegisterModel;
import com.example.backend.database.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/journalPost")
public class JournalPostController {
    private final JournalPostService journalPostService;
    private final UserService userService;

    @Value("${upload.directory}")
    private String userUploadsToDirectory;

    public JournalPostController(JournalPostService journalPostService, UserService userService) {
        this.journalPostService = journalPostService;
        this.userService = userService;
    }

    @PostMapping(value= "/addJournalPost/{userId}")
    public ResponseEntity<JournalPost> addJournalPost(@RequestParam("photo") MultipartFile photo, @RequestParam("post") String postJson, @PathVariable("userId") Integer userId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JournalPostModel journalPostModel = objectMapper.readValue(postJson, JournalPostModel.class);
        try {
            String fullPathForProfilePictures = userUploadsToDirectory + "/UserMemoriesPics/" + userId;
            File directoryToUpload = new File(fullPathForProfilePictures);

            if (!directoryToUpload.exists()) {
                boolean created = directoryToUpload.mkdirs();
                if (created) {
                    System.out.println("Directory created successfully.");
                    User currentUserToWritePost = userService.getUserByUserId(Long.valueOf(userId));

                    String photoName = photo.getOriginalFilename();
                    String filePath = fullPathForProfilePictures + "/" + photoName;
                    photo.transferTo(new File(filePath));

                    LocalDate journalPostDate = LocalDate.now();

                    JournalPost newJournalPost = new JournalPost();
                    newJournalPost.setName(journalPostModel.getName());
                    newJournalPost.setPost(journalPostModel.getPost());
                    newJournalPost.setDateWritten(journalPostDate);
                    newJournalPost.setPhoto(photoName);
                    newJournalPost.setUser(currentUserToWritePost);

                    JournalPost journalPost = journalPostService.addJournalPost(newJournalPost);
                    return new ResponseEntity<>(journalPost, HttpStatus.CREATED);
                } else {
                    System.out.println("Failed to create directory.");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                System.out.println("Directory already exists.");
                User currentUserToWritePost = userService.getUserByUserId(Long.valueOf(userId));

                String photoName = photo.getOriginalFilename();
                String filePath = fullPathForProfilePictures + "/" + photoName;
                photo.transferTo(new File(filePath));

                LocalDate journalPostDate = LocalDate.now();

                JournalPost newJournalPost = new JournalPost();
                newJournalPost.setName(journalPostModel.getName());
                newJournalPost.setPost(journalPostModel.getPost());
                newJournalPost.setDateWritten(journalPostDate);
                newJournalPost.setPhoto(photoName);
                newJournalPost.setUser(currentUserToWritePost);

                JournalPost journalPost = journalPostService.addJournalPost(newJournalPost);
                return new ResponseEntity<>(journalPost, HttpStatus.CREATED);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllJournalPostsForAGivenUser/{userId}")
    private ResponseEntity<List<JournalPost>> getAllJournalPostsForAGivenUser(@PathVariable("userId") Integer userId){
        List<JournalPost> journalPosts = journalPostService.getAllJournalPostsForGivenUser(userId);
        return new ResponseEntity<>(journalPosts, HttpStatus.OK);
    }

    @GetMapping("/getAllPhotosForAGivenUser/{userId}")
    private ResponseEntity<List<String>> getAllPhotosForAGivenUser(@PathVariable("userId") Integer userId){
        List<JournalPost> journalPosts = journalPostService.getAllJournalPostsForGivenUser(userId);
        List<String> photos = journalPosts.stream().map(JournalPost::getPhoto).toList();
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }
}
