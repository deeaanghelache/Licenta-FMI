package com.example.backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findUserByUserId/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId){
        User user = userService.getUserByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findUserByUsername/{username}")
    public ResponseEntity<User> getUserById(@PathVariable("username") String username){
        User user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findUserByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        User user = userService.getUserByUserEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // TODO: post si put

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
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
