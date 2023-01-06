package com.example.backend.userRole;

import com.example.backend.role.Role;
import com.example.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleInterface extends JpaRepository<UserRole, Integer> {
    // get all users with given role
    @Query(value = "SELECT user_role.user_id, user_role.role_id " +
            "FROM user_role JOIN user ON (user_role.user_id = user.user_id) " +
            "WHERE user_role.role_id = :roleId", nativeQuery = true)
    List<User> queryBy(@Param("roleId") Integer roleId);

    // get all roles for a given user
    @Query(value = "SELECT user_role.user_id, user_role.role_id " +
            "FROM user_role JOIN role ON (user_role.role_id = user_role.role_id) " +
            "WHERE user_role.user_id = :userId", nativeQuery = true)
    List<Role> queryBy(@Param("userId") Long userId);

    // TODO delete, update, add
}
