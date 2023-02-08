package com.example.backend.userRole;

import com.example.backend.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.backend.role.Role;
import com.example.backend.user.User;

import java.util.List;

@Service
@Transactional
public class UserRoleService {
    private UserRoleInterface userRoleInterface;
    private UserService userService;

    @Autowired
    public UserRoleService(UserRoleInterface userRoleInterface, UserService userService) {
        this.userRoleInterface = userRoleInterface;
        this.userService = userService;
    }

    // FIND(GET)
    public List<UserRole> getAllUsersForAGivenRole(Integer roleId){
        return userRoleInterface.queryBy(roleId);
    }

    public List<UserRole> getAllRolesForGivenUser(Long userId){
        return userRoleInterface.queryBy(userId);
    }

    // POST
    public void addUserRoleForUsers(Long userId){
        UserRoleId userRoleId = new UserRoleId();
        userRoleId.setRoleId(2);
        userRoleId.setUserId(userId);

        UserRole userRole = new UserRole();
        userRole.setUserRoleId(userRoleId);

        User user = userService.getUserByUserId(userId);
        Role role = new Role(2, "user");
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleInterface.save(userRole);
    }

    // PUT

    // DELETE

}
