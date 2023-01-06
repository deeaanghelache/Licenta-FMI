package com.example.backend.role;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface RoleInterface extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByRoleId(Integer roleId);
    Optional<Role> findRoleByRoleName(String roleName);

    void deleteRoleByRoleId(Integer roleId);
    void deleteRoleByRoleName(String roleName);
}
