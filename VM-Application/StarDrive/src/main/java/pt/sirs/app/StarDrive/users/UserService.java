package pt.sirs.app.StarDrive.users;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import pt.sirs.app.StarDrive.Database;
import pt.sirs.app.StarDrive.users.domain.Employee;
import pt.sirs.app.StarDrive.users.domain.Engineer;
import pt.sirs.app.StarDrive.users.domain.User;

public class UserService {
    
    private MongoCollection<Document> repository;

    public UserService(){
        repository = Database.usersCollection;
    }

    public User createUser(String id, String name, User.Role role){
        User user;
        switch (role) {
            case ENGINEER:
                user = new Engineer(id, name, role);
                break;
            case EMPLOYEE:
                user = new Employee(id, name, role);
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }
        // save user to database
        repository.insertOne(user.toDocument());
        return user;
    }



}
