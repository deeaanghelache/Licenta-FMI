package com.example.backend.database.preference;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class PreferenceService {
    private final PreferenceInterface preferenceInterface;

    @Autowired
    public PreferenceService(PreferenceInterface preferenceInterface) {
        this.preferenceInterface = preferenceInterface;
    }

    // FIND(GET)
    public List<Preference> getAllPreferences(){
        return preferenceInterface.findAll();
    }

    public Preference findPreferenceByPreferenceId(Integer preferenceId){
        return preferenceInterface.findPreferenceByPreferenceId(preferenceId).orElseThrow(() -> new PreferenceIdNotFoundException("Couldn't find any preference with the id: " + preferenceId));
    }

    public List<Preference> findPreferencesByUserId(Integer userId){
        return preferenceInterface.queryBy(userId);
    }

    // POST
    // TODO: add preference by userid

    // PUT
    // TODO: put

    // DELETE
    public void deletePreferenceByPreferenceId(Integer preferenceId){
        preferenceInterface.deletePreferenceByPreferenceId(preferenceId);
    }

    public void deleteAllPreferences(){
        preferenceInterface.deleteAll();
    }

    // TODO: delete preference by userid
}
