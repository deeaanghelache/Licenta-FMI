package com.example.backend.user;

import com.example.backend.userRole.UserRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;

    public UserController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
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
    public ResponseEntity<User> getUserById(@PathVariable("username") String username){
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
        var userRoles = userService.getAllRolesForGivenUser(email);
        System.out.println(userRoles);

        for (var userRole : userRoles) {
            if (userRole.getRole().getRoleName().equalsIgnoreCase("Admin")) {
                System.out.println(userRole.getRole().getRoleName());
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    // TODO: post si put

    // POST
    @PostMapping("/addUser")
    public ResponseEntity<User> register(@RequestBody User newUser) {
        var users = userService.getAllUsers();

        for (var user : users){
            if ((user.getEmail().equals(newUser.getEmail())) || (user.getUsername().equals(newUser.getUsername()))){
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        }

        var password = newUser.getPassword();

        // TODO: hash password

        System.out.println("\n1111\n");

        User user = userService.addUser(newUser);
        System.out.println("\n1\n");

        System.out.println(user.getUserId());

        userRoleService.addUserRoleForUsers(user.getUserId());

        System.out.println("\n2\n");
        System.out.println(user.getUserId());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User userTryingToLogIn){
        var users = userService.getAllUsers();

        for (var user : users){
            if (user.getEmail().equals(userTryingToLogIn.getEmail())){
                String password1 = user.getPassword();
                String password2 = userTryingToLogIn.getPassword();

                if (password1.equals(password2)){
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
                // TODO: verificare pe hashed passwords
            }
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

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
