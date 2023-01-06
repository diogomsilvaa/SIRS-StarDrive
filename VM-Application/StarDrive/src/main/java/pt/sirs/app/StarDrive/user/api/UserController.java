package pt.sirs.app.StarDrive.user.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pt.sirs.app.StarDrive.auth.AuthService;
import pt.sirs.app.StarDrive.user.UserService;
import pt.sirs.app.StarDrive.user.domain.User;
import pt.sirs.app.StarDrive.user.domain.User.Role;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/loginFront")
    void loginFront(@RequestBody Map<String, String> body){
        AuthService auth = new AuthService(body.get("token"));
        String userId = null;
        try {
            auth.verifyToken();
            userId = auth.getId();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        if(!userId.equals(body.get("id"))) throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        User user = userService.getUser(body.get("id"));
        if(user == null) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

    @CrossOrigin
    @PostMapping("/loginBack")
    void loginBack(@RequestBody Map<String, String> body){
        AuthService auth = new AuthService(body.get("token"));
        String userId = null;
        try {
            auth.verifyToken();
            userId = auth.getId();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        if(!userId.equals(body.get("id"))) throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        User user = userService.getUser(body.get("id"));
        if(user == null) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        if(user.getRole() != User.Role.ENGINEER) throw new ResponseStatusException(HttpStatusCode.valueOf(403));
    }

    @CrossOrigin
    @PostMapping("/getUser")
    User getUser(@RequestBody Map<String, String> body){
        AuthService auth = new AuthService(body.get("token"));
        try {
            auth.verifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        User user = userService.getUser(auth.getId());
        if(user == null) throw new ResponseStatusException(HttpStatusCode.valueOf(404));

        return user;
    }

    @CrossOrigin
    @PostMapping("/getEmployees")
    List<User> getEmployees(@RequestBody Map<String, String> body){
        AuthService auth = new AuthService(body.get("token"));
        try {
            auth.verifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        User user = userService.getUser(auth.getId());
        if(user.getRole() != User.Role.ENGINEER) throw new ResponseStatusException(HttpStatusCode.valueOf(403));

        List<User> employees = new ArrayList<User>();

        for(User u : userService.getUsers()){
            if(u.getRole() == Role.EMPLOYEE){
                employees.add(u);
            }
        }

        return employees;
    }


    @CrossOrigin
    @PostMapping("/changeSalary")
    void changeSalary(@RequestBody Map<String, String> body){
        AuthService auth = new AuthService(body.get("token"));
        try {
            auth.verifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        User user = userService.getUser(auth.getId());
        if(user.getRole() != User.Role.ENGINEER) throw new ResponseStatusException(HttpStatusCode.valueOf(403));
        
        userService.changeSalary(body.get("id"), Double.parseDouble(body.get("salary")));
    }
}
