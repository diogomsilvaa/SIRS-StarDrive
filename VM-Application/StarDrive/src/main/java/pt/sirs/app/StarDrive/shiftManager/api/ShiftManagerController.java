package pt.sirs.app.StarDrive.shiftManager.api;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.sirs.app.StarDrive.shiftManager.ShiftManagerService;
import pt.sirs.app.StarDrive.shiftManager.domain.Shift;

@RestController
@RequestMapping("/shifts")
public class ShiftManagerController {

    @Autowired
    ShiftManagerService shiftManagerService;

    @GetMapping("/create")
    Shift createShift() {
        Shift newShift = shiftManagerService.createShift("1", LocalDateTime.now(), LocalDateTime.now());
        return newShift;
    }
    
}
