package com.example.backend.database.user;

import com.example.backend.database.userRole.UserRole;
import com.example.backend.database.userRole.UserRoleInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    // injectam interfata user-ului
    private final UserInterface userInterface;
    private final UserRoleInterface userRoleInterface;

    @Autowired
    public UserService(UserInterface userInterface, UserRoleInterface userRoleInterface) {
        this.userInterface = userInterface;
        this.userRoleInterface = userRoleInterface;
    }

    // FIND (GET)
    public List<User> getAllUsers(){
        return userInterface.findAll();
    }

    public User getUserByUserId(Long userId){
        return userInterface.findUserByUserId(userId).orElseThrow(() -> new UserIdNotFoundException("Couldn't find any user with the id: " + userId));
    }

    public User getUserByUserEmail(String email){
        return userInterface.findUserByEmail(email).orElseThrow(() -> new UserEmailNotFoundException("Couldn't find any user with the email: " + email));
    }

    public User getUserByUsername(String username){
        return userInterface.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Couldn't find any user with the username: " + username));
    }

    public List<UserRole> getAllRolesForGivenUser(String email){
        var currentUser = getUserByUserEmail(email);
        return userRoleInterface.queryBy(currentUser.getUserId());
    }

    public Boolean checkIfUsernameExists(String username){
        return userInterface.existsByUsername(username);
    }

    public Boolean checkIfEmailExists(String email){
        return userInterface.existsByEmail(email);
    }

    // POST
    public User addUser(User user){
        // TODO: criptare parola


        return userInterface.save(user);
    }

    // PUT (UPDATE)
    // TODO: put

    // DELETE
    public void deleteAllUsers(){
        userInterface.deleteAll();
    }

    public void deleteUserByUserId(Integer userId){
        userInterface.deleteUserByUserId(userId);
    }

    public void deleteUserByUsername(String username){
        userInterface.deleteUserByUsername(username);
    }

    public void deleteUserByUserEmail(String email){
        userInterface.deleteUserByEmail(email);
    }
}
