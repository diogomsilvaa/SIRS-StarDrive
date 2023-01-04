package pt.sirs.app.StarDrive.shiftManager.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pt.sirs.app.StarDrive.shiftManager.ShiftManagerService;
import pt.sirs.app.StarDrive.shiftManager.domain.Shift;

@RestController
@RequestMapping("/shift")
public class ShiftManagerController {

    @Autowired
    ShiftManagerService shiftManagerService;

    @PostMapping("/create")
    Shift createShift(@RequestParam String token, @RequestParam String start, @RequestParam long duration) {
        //só um engineer é que consegue criar um shift
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
        //LocalDateTime endDateTime = startDateTime.plusMinutes(duration);
        //Shift newShift = shiftManagerService.createShift(startDateTime, endDateTime);
        Shift newShift = shiftManagerService.createShift(LocalDateTime.now(), LocalDateTime.now().plusMinutes(duration));
        return newShift;
    }

    @PutMapping("/addEmployee")
    Shift addEmployee(@RequestParam String token, @RequestParam String shiftId, @RequestParam String employeeId) {
        //só um engineer é que consegue adicionar um employee a um shift
        Shift newShift = null;
        try {
            newShift = shiftManagerService.addEmployee(shiftId, employeeId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
        return newShift;
    }    
}
