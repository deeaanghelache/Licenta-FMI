package com.example.backend.database.preference;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/preference")
public class PreferenceController {
    // injectam PreferenceService pentru a putea accesa functiile din el
    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @GetMapping("/findAllPreferences")
    public ResponseEntity<List<Preference>> getAllPreferences(){
        List<Preference> preferences = preferenceService.getAllPreferences();
        return new ResponseEntity<>(preferences, HttpStatus.OK);
    }

    @GetMapping("/findByUserId/{id}")
    public ResponseEntity<List<Preference>> getPreferenceByUserId(@PathVariable("id") Integer userId){
        List<Preference> preferencesByUser = preferenceService.findPreferencesByUserId(userId);
        return new ResponseEntity<>(preferencesByUser, HttpStatus.OK);
    }

    @GetMapping("/findByPreferenceId/{id}")
    public ResponseEntity<Preference> getPreferenceByPreferenceId(@PathVariable("id") Integer preferenceId){
        Preference preference = preferenceService.findPreferenceByPreferenceId(preferenceId);
        return new ResponseEntity<>(preference, HttpStatus.OK);
    }
}
