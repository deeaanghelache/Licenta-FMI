package com.example.backend.role;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleService {
    private final RoleInterface roleInterface;

    @Autowired
    public RoleService(RoleInterface roleInterface) {
        this.roleInterface = roleInterface;
    }

    // FIND(GET)
    public List<Role> getAllRoles(){
        return roleInterface.findAll();
    }

    public Role getRoleByRoleId(Integer roleId){
        return roleInterface.findRoleByRoleId(roleId).orElseThrow(() -> new RoleIdNotFoundException("Couldn't find any role with the id: " + roleId));
    }

    public Role getRoleByRoleName(String roleName){
        return roleInterface.findRoleByRoleName(roleName).orElseThrow(() -> new RoleNameNotFoundException("Couldn't find any role with the name: " + roleName));
    }

    // POST
    public Role addRole(Role role){
        return roleInterface.save(role);
    }

    // PUT
    // TODO

    // DELETE
    public void deleteAllRoles(){
        roleInterface.deleteAll();
    }

    public void deleteRoleByRoleId(Integer roleId){
        roleInterface.deleteRoleByRoleId(roleId);
    }

    public void deleteRoleByRoleName(String roleName){
        roleInterface.deleteRoleByRoleName(roleName);
    }
}
