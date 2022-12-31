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
    public static MongoCollection<Document> assemblyLinesCollection;
    public static MongoCollection<Document> assemblersCollection;

    static {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("StarDriveDB");  
        usersCollection = database.getCollection("users");
        shiftsCollection = database.getCollection("shifts");
        assemblyLinesCollection = database.getCollection("assemblyLines");
        assemblersCollection = database.getCollection("assemblers");
    }

}
