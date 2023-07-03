package com.example.backend.database.user;

import com.example.backend.database.userRole.UserRoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${upload.directory}")
    private String userUploadsToDirectory;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findUserByUserId/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User user = userService.getUserByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findUserByUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        User user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findUserByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        User user = userService.getUserByUserEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/checkAdminRole/{email}")
    public ResponseEntity<Boolean> checkAdminRoleForGivenUser(@PathVariable("email") String email){
        boolean isAdmin = userService.checkAdminRoleForGivenUser(email);
        return new ResponseEntity<>(isAdmin, HttpStatus.OK);
    }

    // POST
    @PostMapping(value="/addUser")
    public ResponseEntity<User> register(@RequestParam("photo") MultipartFile photo, @RequestParam("user") String userJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserRegisterModel userRegisterModel = objectMapper.readValue(userJson, UserRegisterModel.class);
        try {
            String fullPathForProfilePictures = userUploadsToDirectory + "/UserProfilePics";
            String photoName = photo.getOriginalFilename();
            String filePath = fullPathForProfilePictures + "/" + photoName;
            photo.transferTo(new File(filePath));

            User newUser = new User();
            newUser.setFirstName(userRegisterModel.getFirstName());
            newUser.setLastName(userRegisterModel.getLastName());
            newUser.setEmail(userRegisterModel.getEmail());
            newUser.setUsername(userRegisterModel.getUsername());
            newUser.setPassword(userRegisterModel.getPassword());
            newUser.setPhoto(photoName);

            User user = userService.addUser(newUser);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User userTryingToLogIn){
        var user = userService.login(userTryingToLogIn);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // PUT
    @PutMapping("/changePassword/{userId}/{newPassword}")
    public ResponseEntity<?> changePassword(@PathVariable("userId") Integer userId, @PathVariable("newPassword") String newPassword){
        var newPasswordHashed = bCryptPasswordEncoder.encode(newPassword);
        userService.changeUserPassword(Long.valueOf(userId), newPasswordHashed);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/changeUsername/{userId}/{newUsername}")
    public ResponseEntity<?> changeUsername(@PathVariable("userId") Integer userId, @PathVariable("newUsername") String newUsername){
        userService.changeUsername(userId, newUsername);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<?> deleteAllUsers(){
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteByUserId/{id}")
    public ResponseEntity<?> deleteUserByUserId(@PathVariable("id") Integer userId){
        userService.deleteUserByUserId(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteByUsername/{username}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable("username") String username){
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable("email") String email){
        userService.deleteUserByUserEmail(email);

        // TODO: cand stergi, sa se stearga toate dependintele
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
