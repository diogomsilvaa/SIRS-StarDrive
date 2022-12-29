package pt.sirs.StarDrive.shiftManager.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

import pt.sirs.StarDrive.users.domain.Employee;

public class Shift {
    
    private ArrayList<Employee> employees;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Shift(LocalDateTime _startTime, LocalDateTime _endTime){
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

    public void addEmployee(Employee employee){
        employees.add(employee);
        employee.addShift(this);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
        employee.removeShift(this);
    }
}
