package pt.sirs.app.StarDrive.shiftManager;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.sirs.app.StarDrive.shiftManager.domain.*;
import pt.sirs.app.StarDrive.shiftManager.repo.ShiftRepository;

@Service
public class ShiftManagerService {
    
    @Autowired
    private ShiftRepository shiftRepository;

    public ShiftManagerService(){
    }

    public Shift createShift(String id, LocalDateTime startTime, LocalDateTime endTime){
        Shift shift = new Shift(id, startTime, endTime);
        shiftRepository.save(shift);
        return shift;
    }
}
