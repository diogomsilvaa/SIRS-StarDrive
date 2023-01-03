package pt.sirs.app.StarDrive.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.sirs.app.StarDrive.user.UserService;
import pt.sirs.app.StarDrive.user.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/createEngineer")
    User createEngineer(@RequestParam String name){
        User newUser = userService.createUser(name, User.Role.ENGINEER);
        return newUser;
    }

    @PostMapping("/createEmployee")
    User createEmployee(@RequestParam String name){
        User newUser = userService.createUser(name, User.Role.EMPLOYEE);
        return newUser;
    }

    @GetMapping("/getUser")
    User getUser(@RequestParam String id){
        // Pegar no user e na token que vem com ele e ver se está autenticado
        return userService.getUser(id);
    }

}
