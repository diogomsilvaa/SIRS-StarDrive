package pt.sirs.StarDrive;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database {
    
    public static MongoClient mongoClient;
    public static MongoDatabase database;
    public static MongoCollection<Document> usersCollection;
    public static MongoCollection<Document> shiftsCollection;
    public static MongoCollection<Document> productionCollection;

    static {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("StarDriveDB");  
        usersCollection = database.getCollection("users");
        usersCollection = database.getCollection("shifts");
        usersCollection = database.getCollection("production");
    }

}
