package com.example.backend.userRole;

import com.example.backend.role.Role;
import com.example.backend.user.User;
import jakarta.persistence.*;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
public class UserRole implements Serializable {
    @EmbeddedId
    private UserRoleId userRoleId;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @MapsId("roleId")
    @ManyToOne
    @JoinColumn(name = "role_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    public UserRole() {
    }

    public UserRoleId getUserRoleId() {
        return userRoleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleId=" + userRoleId +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}
