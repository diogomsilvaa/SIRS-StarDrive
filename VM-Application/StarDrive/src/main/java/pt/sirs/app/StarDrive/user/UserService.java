package pt.sirs.app.StarDrive.user;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import pt.sirs.app.StarDrive.auth.AuthService;
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

    public User getUser(@RequestParam String token){
        AuthService auth = new AuthService(token);
        String id = null;
        try {
            id = auth.VerifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }

        User user = userRepository.findById(id).orElse(null);
        
        if(user == null) throw new ResponseStatusException(HttpStatusCode.valueOf(404));

        return user;
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
