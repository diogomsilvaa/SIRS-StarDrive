package pt.sirs.app.StarDrive.shiftManager;

import java.time.LocalDateTime;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import pt.sirs.app.StarDrive.Database;
import pt.sirs.app.StarDrive.shiftManager.domain.*;

public class ShiftManagerService {
    
    private MongoCollection<Document> repository;

    public ShiftManagerService(){
        repository = Database.shiftsCollection;
    }

    public Shift createShift(String id, LocalDateTime startTime, LocalDateTime endTime){
        Shift shift = new Shift(id, startTime, endTime);
        repository.insertOne(shift.toDocument());
        return shift;
    }
}
