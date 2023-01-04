package pt.sirs.app.AuthStarDrive.Authentication.api;

import java.util.Map;
import java.util.NoSuchElementException;
import javax.naming.NoPermissionException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pt.sirs.app.AuthStarDrive.Authentication.AuthenticationService;
import pt.sirs.app.AuthStarDrive.Authentication.domain.Token;

@RestController
public class AuthController {
    
    
    @Autowired
    private AuthenticationService service;

    @CrossOrigin
    @PostMapping("/auth")
    Token requestToken(@RequestBody Map<String, String> body){
        String token;
        try {
            token = service.doLogin(body.get("id"), body.get("pass"));
        } catch (NoPermissionException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(403));
        } catch (NoSuchElementException e) {
            System.out.println("No such element");
            throw new ResponseStatusException(HttpStatus.valueOf(404));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.valueOf(500));
        }
        return new Token(token);
        // return new Token(token.toString());
    }

    @CrossOrigin
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
