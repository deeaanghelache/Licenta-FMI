package com.example.backend.userRole;

import com.example.backend.user.User;
import com.example.backend.userRole.UserRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService){
        this.userRoleService = userRoleService;
    }

    @GetMapping("/getAllUsersForGivenRole/{roleId}")
    public ResponseEntity<List<User>> getAllUsersForGivenRole(@PathVariable("roleId") Integer roleId){
        List<UserRole> userRoles = userRoleService.getAllUsersForAGivenRole(roleId);
        List<User> users = userRoles.stream().map(UserRole::getUser).toList();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
