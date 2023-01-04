package pt.sirs.app.AuthStarDrive.Authentication.api;

import java.util.NoSuchElementException;
import javax.naming.NoPermissionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pt.sirs.app.AuthStarDrive.Authentication.AuthenticationService;

@RestController
public class AuthController {
    
    
    @Autowired
    private AuthenticationService service;

    @GetMapping("/auth")
    String requestToken(@RequestParam String id, @RequestParam String pass){
        byte[] response;
        try {
            response = service.doLogin(id, pass);
        } catch (NoPermissionException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(403));
        } catch (NoSuchElementException e) {
            System.out.println("No such element");
            throw new ResponseStatusException(HttpStatus.valueOf(404));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.valueOf(500));
        }
        return response.toString();
    }

    @PutMapping("/pass")
    String changePass(@RequestParam String id, @RequestParam String pass, @RequestParam String newPass){
        try {
            if (service.changePass(id, pass, newPass)) {
                return "Password changed";
            }
            return "Invalid credentials";
        } catch (NoPermissionException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(403));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(404));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.valueOf(500));
        }
    }
}
