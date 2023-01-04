package pt.sirs.app.StarDrive.shiftManager;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.sirs.app.StarDrive.shiftManager.domain.*;
import pt.sirs.app.StarDrive.shiftManager.repo.ShiftRepository;
import pt.sirs.app.StarDrive.user.domain.User;
import pt.sirs.app.StarDrive.user.repo.UserRepository;

@Service
public class ShiftManagerService {
    
    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private UserRepository userRepository;

    private static int shiftCounter = 0;

    public ShiftManagerService(){
    }

    public Shift createShift(LocalDateTime startTime, LocalDateTime endTime){
        Shift shift = new Shift("S" + shiftCounter, startTime.toString(), endTime.toString());
        shiftRepository.save(shift);
        shiftCounter++;
        return shift;
    }

    public Shift addEmployee(String shiftId, String employeeId){

        Shift shift = shiftRepository.findById(shiftId).get();
        User user = userRepository.findById(employeeId).get();
        
        if(user == null) throw new IllegalArgumentException("User does not exist");
        if(shift == null) throw new IllegalArgumentException("Shift does not exist");
        if(user.getRole() != User.Role.EMPLOYEE) throw new IllegalArgumentException("User is not an employee");

        user.addShift(shiftId);
        shift.addEmployee(employeeId);
        userRepository.save(user);
        shiftRepository.save(shift);
        return shift;
    }
}
