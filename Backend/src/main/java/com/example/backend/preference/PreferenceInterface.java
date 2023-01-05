package com.example.backend.preference;

import com.example.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface PreferenceInterface extends JpaRepository<Preference, Integer> {
    Optional<Preference> findPreferenceByPreferenceId(Integer preferenceId);
    Optional<Preference> findPreferenceByUser(User user);

    @Query(value = "SELECT p.preference_id, p.preferenceNameEng, p.preferenceNameRom" +
            "FROM preference p JOIN user u ON (u.user_id = p.user_id)" +
            "WHERE p.user_id = :userId", nativeQuery = true)
    Set<Preference> queryBy(Integer userId);
}
