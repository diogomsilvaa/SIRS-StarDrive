package pt.sirs.app.StarDrive.shiftManager.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.bson.Document;

import pt.sirs.app.StarDrive.users.domain.Employee;

public class Shift {
    
    private String id;
    private ArrayList<Employee> employees;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Shift(String id, LocalDateTime _startTime, LocalDateTime _endTime){
        setId(id);
        setStartTime(_startTime);
        setEndTime(_endTime);
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getEndTime() {
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

    public Document toDocument(){
        return new Document("id", getId()).append("startTime", getStartTime()).append("endTime", getEndTime()).append("employees", getEmployeesIDs());
    }
}
