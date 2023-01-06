package com.example.backend.userRole;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.role.Role;
import com.example.backend.user.User;

import java.util.List;

@Service
@Transactional
public class UserRoleService {
    private UserRoleInterface userRoleInterface;

    @Autowired
    public UserRoleService(UserRoleInterface userRoleInterface) {
        this.userRoleInterface = userRoleInterface;
    }

    // FIND(GET)
    public List<User> getAllUsersForAGivenRole(Integer roleId){
        return userRoleInterface.queryBy(roleId);
    }

    public List<Role> getAllRolesForGivenUser(Long userId){
        return userRoleInterface.queryBy(userId);
    }

    // POST

    // PUT

    // DELETE

}
