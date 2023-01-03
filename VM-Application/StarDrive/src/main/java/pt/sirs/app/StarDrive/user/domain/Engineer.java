package pt.sirs.app.StarDrive.user.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Engineer extends Employee{

    public Engineer(){}

    public Engineer(String _id, String _name, User.Role _role){
        super(_id, _name, _role);
    }
}
