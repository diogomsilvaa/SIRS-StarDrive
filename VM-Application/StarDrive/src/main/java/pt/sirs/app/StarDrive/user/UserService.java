package pt.sirs.app.StarDrive.user;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import pt.sirs.app.StarDrive.user.domain.User;
import pt.sirs.app.StarDrive.user.repo.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private static int userCount = 0;

    public UserService(){
    }

    public User getUser(@RequestParam String id){
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public User createUser(String name, User.Role role, Double salary){
        User user;
        switch (role) {
            case ENGINEER:
                user = new User("U" + userCount, name, role, salary);
                break;
            case EMPLOYEE:
                user = new User("U" + userCount, name, role, salary);
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }
        // save user to database
        userCount++;
        userRepository.save(user);
        return user;
    }


}
