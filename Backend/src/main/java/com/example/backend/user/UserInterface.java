package com.example.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserInterface extends JpaRepository<User, Integer> {
    Optional<User> findUserByUserId(Long userId);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    void deleteUserByUserId(Integer userId);
    void deleteUserByUsername(String username);
    void deleteUserByEmail(String email);
}
