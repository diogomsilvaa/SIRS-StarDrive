package pt.sirs.app.StarDrive.shiftManager.domain;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shifts")
public class Shift {
    
    @Id
    private String id;
    private ArrayList<String> employeesIDs;
    private String startTime;
    private String endTime;

    public Shift(String id, String startTime, String endTime){
        setId(id);
        setStartTime(startTime);
        setEndTime(endTime);
        employeesIDs = new ArrayList<String>();
    }
    

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addEmployee(String employeeId){
        employeesIDs.add(employeeId);
    }

    public void removeEmployee(String employeeId){
        employeesIDs.remove(employeeId);
    }

    public ArrayList<String> getEmployeesIDs() {
        return employeesIDs;
    }

}
