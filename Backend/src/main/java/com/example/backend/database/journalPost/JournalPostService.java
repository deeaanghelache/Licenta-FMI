package com.example.backend.database.journalPost;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class JournalPostService {
    private final JournalPostInterface journalPostInterface;

    @Autowired
    public JournalPostService(JournalPostInterface journalPostInterface) {
        this.journalPostInterface = journalPostInterface;
    }

    // FIND(GET)
    public List<JournalPost> getAllJournalPostsForGivenUser(Integer userId){
        return journalPostInterface.queryBy(userId);
    }
}
