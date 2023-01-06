package pt.sirs.app.StarDrive.shiftManager.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pt.sirs.app.StarDrive.auth.AuthService;
import pt.sirs.app.StarDrive.shiftManager.ShiftManagerService;
import pt.sirs.app.StarDrive.shiftManager.domain.Shift;
import pt.sirs.app.StarDrive.user.UserService;
import pt.sirs.app.StarDrive.user.domain.User;

@RestController
@RequestMapping("/shift")
public class ShiftManagerController {

    @Autowired
    ShiftManagerService shiftManagerService;

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/create")
    Shift createShift(@RequestBody Map<String, String> body) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(body.get("start"), formatter);
        LocalDateTime endDateTime = startDateTime.plusMinutes(Long.parseLong(body.get("duration")));
        Shift newShift = shiftManagerService.createShift(startDateTime, endDateTime);
        return newShift;
    }

    @CrossOrigin
    @PostMapping("/getShifts")
    Shift[] getShifts(@RequestBody Map<String, String> body) {
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
        
        return shiftManagerService.getShifts();
    }

    @CrossOrigin
    @PutMapping("/addEmployee")
    Shift addEmployee(@RequestBody Map<String, String> body) {
        //só um engineer é que consegue adicionar um employee a um shift
        Shift newShift = null;
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

        try {
            newShift = shiftManagerService.addEmployee(body.get("shiftId"), body.get("employeeId"));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }


        return newShift;
    }

    @CrossOrigin
    @GetMapping("/get")
    Shift[] getShifts() {
        return shiftManagerService.getShifts();
    }
}
