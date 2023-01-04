package pt.sirs.app.StarDrive.user.api;

import java.util.concurrent.TimeoutException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pt.sirs.app.StarDrive.auth.AuthService;
import pt.sirs.app.StarDrive.user.UserService;
import pt.sirs.app.StarDrive.user.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping("/loginFront")
    void loginFront(@RequestParam String token, @RequestParam String id){
        AuthService auth = new AuthService(token);
        String userId = null;
        try {
            id = auth.VerifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        if(userId != id) throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        User user = userService.getUser(id);
        if(user == null) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

    @GetMapping("/loginBack")
    void loginBack(@RequestParam String token, @RequestParam String id){
        AuthService auth = new AuthService(token);
        String userId = null;
        try {
            id = auth.VerifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        if(userId != id) throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        User user = userService.getUser(id);
        if(user == null) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        if(user.getRole() != User.Role.ENGINEER) throw new ResponseStatusException(HttpStatusCode.valueOf(403));
    }

    @GetMapping("/getUser")
    User getUser(@RequestParam String token){
        AuthService auth = new AuthService(token);
        String id = null;
        try {
            id = auth.VerifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        User user = userService.getUser(id);
        if(user == null) throw new ResponseStatusException(HttpStatusCode.valueOf(404));

        return user;
    }

}
