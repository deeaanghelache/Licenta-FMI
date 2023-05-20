package com.example.backend.database.userRole;

import com.example.backend.database.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.database.role.Role;
import com.example.backend.database.user.User;

import java.util.List;

@Service
@Transactional
public class UserRoleService {
    private UserRoleInterface userRoleInterface;
//    private UserService userService;

    @Autowired
    public UserRoleService(UserRoleInterface userRoleInterface) {
        this.userRoleInterface = userRoleInterface;
    }

    // FIND(GET)
    public List<UserRole> getAllUsersForAGivenRole(Integer roleId){
        return userRoleInterface.queryBy(roleId);
    }

    public List<UserRole> getAllRolesForGivenUser(Long userId){
        return userRoleInterface.queryBy(userId);
    }

    // POST
    public void addUserRoleForUsers(User user){
        UserRoleId userRoleId = new UserRoleId();
        userRoleId.setRoleId(2);
        userRoleId.setUserId(user.getUserId());

        UserRole userRole = new UserRole();
        userRole.setUserRoleId(userRoleId);

        Role role = new Role(2, "user");
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleInterface.save(userRole);
    }

    // PUT

    // DELETE

}
