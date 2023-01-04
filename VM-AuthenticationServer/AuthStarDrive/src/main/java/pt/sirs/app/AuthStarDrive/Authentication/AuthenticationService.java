package pt.sirs.app.AuthStarDrive.Authentication;

import pt.sirs.app.AuthStarDrive.Authentication.domain.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private Authentication auth;

    public AuthenticationService() {
        try {
            File f = new File("Serializable.txt");
            if (!f.exists()) {
                auth = new Authentication();
            }
            else {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("Serializable.txt"));  
                auth = (Authentication)in.readObject();  
                in.close();
            }
            System.out.println("Authentication service started");
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void addUser(String id, String pass) throws Exception{
        
        auth.addUser(id, pass);
        File file = new File("Serializable.txt");
        file.createNewFile();
        FileOutputStream fout = new FileOutputStream(file, false);    
        ObjectOutputStream out = new ObjectOutputStream(fout);    
        out.writeObject(auth);    
        out.flush();    
        out.close();    
    }

    public byte[] doLogin(String id, String pass) throws Exception {
        if (!auth.checkPass(id, pass)) {
            return "Invalid credentials".getBytes();
        }
        return auth.tokenGenerator(id);
    }

    public boolean changePass(String id, String oldPass, String newPass) throws Exception {
        return auth.changePass(id, oldPass, newPass);
    }
}