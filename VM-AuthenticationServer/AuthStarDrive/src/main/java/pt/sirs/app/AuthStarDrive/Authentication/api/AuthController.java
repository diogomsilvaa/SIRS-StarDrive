package pt.sirs.app.AuthStarDrive.Authentication.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.sirs.app.AuthStarDrive.Authentication.AuthenticationService;

@RestController
public class AuthController {
    
    @Autowired
    private AuthenticationService service;

    @GetMapping("/auth")
    String requestToken(@RequestParam String id, @RequestParam String pass){
        service.doSomething("ip", "id", "pass");
        return "MUDA ISTO TUDO";
    }
}
