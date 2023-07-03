package com.example.backend.database.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserInterface extends JpaRepository<User, Integer> {
    Optional<User> findUserByUserId(Long userId);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.userId = :userId")
    void queryBy(@Param("userId") Long userId, @Param("newPassword") String newPassword);

    @Modifying
    @Query("UPDATE User u SET u.username = :newUsername WHERE u.userId = :userId")
    void queryBy(@Param("userId") Integer userId,
                 @Param("newUsername") String newUsername);

    void deleteUserByUserId(Integer userId);
    void deleteUserByUsername(String username);
    void deleteUserByEmail(String email);
}
