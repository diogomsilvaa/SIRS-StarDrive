package pt.sirs.StarDrive.users.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.bson.Document;

import com.mongodb.DBObject;

public class User {
    
    public enum Role {EMPLOYEE, ENGINEER}

    public static class UserTypes {
        public static final String EMPLOYEE = "employee";
        public static final String ENGINEER = "engineer";
    }

    private String id;
    private String name;
    private LocalDateTime creationDate;
    private Role role;


    public User(String _id, String _name, User.Role _role){
        setId(_id);
        setName(_name);
        setCreationDate(LocalDateTime.now(ZoneOffset.UTC));
        setRole(_role);
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

    public Document toDocument(){
        return new Document("id", getId()).append("name", getName()).append("creationDate", getCreationDate()).append("role", getRole());
    }
}
