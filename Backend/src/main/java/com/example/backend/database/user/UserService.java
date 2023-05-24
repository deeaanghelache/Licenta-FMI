package com.example.backend.database.user;

import com.example.backend.database.userRole.UserRole;
import com.example.backend.database.userRole.UserRoleInterface;
import com.example.backend.database.userRole.UserRoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    // injectam interfata user-ului
    private final UserInterface userInterface;
    private final UserRoleInterface userRoleInterface;
    private final UserRoleService userRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserInterface userInterface, UserRoleInterface userRoleInterface, UserRoleService userRoleService) {
        this.userInterface = userInterface;
        this.userRoleInterface = userRoleInterface;
        this.userRoleService = userRoleService;
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

    public Boolean checkAdminRoleForGivenUser(String email) {
        var userRoles = getAllRolesForGivenUser(email);
        System.out.println(userRoles);

        for (var userRole : userRoles) {
            if (userRole.getRole().getRoleName().equalsIgnoreCase("Admin")) {
                System.out.println(userRole.getRole().getRoleName());
                return true;
            }
        }
        return false;
    }

    // POST
    public User addUser(User newUser){
        var users = getAllUsers();

        for (var user : users){
            if ((user.getEmail().equals(newUser.getEmail())) || (user.getUsername().equals(newUser.getUsername()))){
                return null;
            }
        }

        var password = newUser.getPassword();
        System.out.println("Initial password: " + password);
        var hashedPassword = bCryptPasswordEncoder.encode(password);
        System.out.println("Hashed password: " + hashedPassword);

        newUser.setPassword(hashedPassword);
        User user = userInterface.save(newUser);

        userRoleService.addUserRoleForUsers(user);
        return user;
    }

    public User login(User userTryingToLogIn){
        var users = getAllUsers();

        for (var user : users){
            if (user.getEmail().equals(userTryingToLogIn.getEmail())){
                if (bCryptPasswordEncoder.matches(userTryingToLogIn.getPassword(), user.getPassword())){
                    return user;
                }
            }
        }
        return null;
    }

    // PUT (UPDATE)
    public void changeUserPassword(Long userId, String newPassword){
        userInterface.queryBy(userId, newPassword);
    }

    public void changeUsername(Integer userId, String newUsername){
        userInterface.queryBy(userId, newUsername);
    }

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
