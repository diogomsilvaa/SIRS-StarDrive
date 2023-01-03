package pt.sirs.app.StarDrive.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.sirs.app.StarDrive.user.domain.Employee;
import pt.sirs.app.StarDrive.user.domain.Engineer;
import pt.sirs.app.StarDrive.user.domain.User;
import pt.sirs.app.StarDrive.user.repo.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private static int userCount = 0;

    public UserService(){
    }

    public User getUser(String id){
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(String name, User.Role role){
        User user;
        switch (role) {
            case ENGINEER:
                user = new Engineer("U" + userCount, name, role);
                break;
            case EMPLOYEE:
                user = new Employee("U" + userCount, name, role);
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
