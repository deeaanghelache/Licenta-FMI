package com.example.backend.preference;

import com.example.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface PreferenceInterface extends JpaRepository<Preference, Integer> {
    Optional<Preference> findPreferenceByPreferenceId(Integer preferenceId);
    Optional<Preference> findPreferenceByUser(User user);

    @Query(value = "select preference.preference_id, preference.preference_name_eng, preference.preference_name_rom, preference.user_id" +
            " from preference JOIN user ON (user.user_id = preference.user_id)" +
            " where preference.user_id = :userId", nativeQuery = true)
    List<Preference> queryBy(@Param("userId") Integer userId);

    void deletePreferenceByPreferenceId(Integer preferenceId);
}
