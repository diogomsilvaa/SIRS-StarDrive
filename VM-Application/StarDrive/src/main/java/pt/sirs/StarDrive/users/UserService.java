package pt.sirs.StarDrive.users;

import pt.sirs.StarDrive.users.domain.Employee;
import pt.sirs.StarDrive.users.domain.Engineer;
import pt.sirs.StarDrive.users.domain.User;

public class UserService {
    
    public UserService(){
        
    }

    public User createUser(String id, String name, User.Role role){
        User user;
        switch (role) {
            case ENGINEER:
                user = new Engineer(id, name, role);
                break;
            case EMPLOYEE:
                user = new Employee(id, name, role);
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }
        // save user to database
        return user;
    }



}
