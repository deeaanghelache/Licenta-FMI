package com.example.backend.userRole;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserRoleId implements Serializable {
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public UserRoleId() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserRoleId{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                '}';
    }
}
