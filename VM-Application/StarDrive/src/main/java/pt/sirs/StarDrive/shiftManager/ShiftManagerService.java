package pt.sirs.StarDrive.shiftManager;

import java.time.LocalDateTime;
import pt.sirs.StarDrive.shiftManager.domain.*;

public class ShiftManagerService {
    
    public ShiftManagerService(){

    }

    public Shift createShift(LocalDateTime startTime, LocalDateTime endTime){
        Shift shift = new Shift(startTime, endTime);
        return shift;
    }
}
