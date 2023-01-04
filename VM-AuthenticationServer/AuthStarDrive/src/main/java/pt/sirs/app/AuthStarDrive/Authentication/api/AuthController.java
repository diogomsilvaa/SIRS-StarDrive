package pt.sirs.app.AuthStarDrive.Authentication.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.sirs.app.AuthStarDrive.Authentication.AuthenticationService;

@RestController
public class AuthController {
    
    @Autowired
    private AuthenticationService service;

    @GetMapping("/auth")
    byte[] requestToken(@RequestParam String id, @RequestParam String pass){
        byte[] response;
        try {
            response = service.doLogin("id", "pass");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @PutMapping("/auth")
    String changePass(@RequestParam String id, @RequestParam String pass, @RequestParam String newPass){
        try {
            if (service.changePass(id, pass, newPass)) {
                return "Password changed";
            }
            return "Invalid credentials";
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
    }
}
