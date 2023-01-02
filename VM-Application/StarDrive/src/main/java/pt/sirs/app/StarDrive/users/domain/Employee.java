package pt.sirs.app.StarDrive.users.domain;

import java.util.ArrayList;

import pt.sirs.app.StarDrive.shiftManager.domain.Shift;


public class Employee extends User{
    
    private ArrayList<Shift> shifts;

    public Employee(String _id, String _name, User.Role _role){
        super(_id, _name, _role);
    }

    public void addShift(Shift shift){
        shifts.add(shift);
    }

    public void removeShift(Shift shift){
        shifts.remove(shift);
    }

}
