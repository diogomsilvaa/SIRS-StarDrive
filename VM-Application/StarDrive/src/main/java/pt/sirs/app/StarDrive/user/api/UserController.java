package pt.sirs.app.StarDrive.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.sirs.app.StarDrive.user.UserService;
import pt.sirs.app.StarDrive.user.domain.User;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @RequestMapping("/create")
    User createUser(){
        User newUser = userService.createUser("diogo", User.Role.ENGINEER);
        return newUser;
    }
}
