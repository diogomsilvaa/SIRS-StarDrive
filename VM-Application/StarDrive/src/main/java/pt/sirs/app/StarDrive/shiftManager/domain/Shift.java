package pt.sirs.app.StarDrive.shiftManager.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import pt.sirs.app.StarDrive.user.domain.Employee;

@Document(collection = "shifts")
public class Shift {
    
    @Id
    private String id;
    private ArrayList<Employee> employees;
    private String startTime;
    private String endTime;

    public Shift(String id, LocalDateTime _startTime, LocalDateTime _endTime){
        setId(id);
        setStartTime(_startTime.toString());
        setEndTime(_endTime.toString());
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

    public void addEmployee(Employee employee){
        employees.add(employee);
        employee.addShift(this);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
        employee.removeShift(this);
    }

    public ArrayList<String> getEmployeesIDs() {
        ArrayList<String> employeesIDs = new ArrayList<>();
        employees.forEach(e -> employeesIDs.add(e.getId()));;
        return employeesIDs;
    }

}
