package pt.sirs.app.StarDrive.user.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {
    
    public enum Role {EMPLOYEE, ENGINEER}

    public static class UserTypes {
        public static final String EMPLOYEE = "employee";
        public static final String ENGINEER = "engineer";
    }

    @Id
    private String id;
    private double salary;
    private String name;
    private LocalDateTime creationDate;
    private Role role;
    private ArrayList<String> shiftsIDs;
    private ArrayList<String> absentDays;


    public User(){}

    public User(String id, String name, User.Role role, double salary){
        setId(id);
        setName(name);
        setCreationDate(LocalDateTime.now(ZoneOffset.UTC));
        setRole(role);
        setSalary(salary);
        shiftsIDs = new ArrayList<String>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void addShift(String shiftID){
        shiftsIDs.add(shiftID);
    }

    public void removeShift(String shiftID){
        shiftsIDs.remove(shiftID);
    }
    
    public ArrayList<String> getShiftsIDs() {
        return shiftsIDs;
    }

    public void addAbsentDay(String date){
        absentDays.add(date); // format: yyyy/MM/dd
    }

    public ArrayList<String> getAbsentDays() {
        return absentDays;
    }
}
