package pt.sirs.app.AuthStarDrive.Authentication;

import pt.sirs.app.AuthStarDrive.Authentication.domain.*;
import org.json.JSONObject;
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
            }
            System.out.println("Authentication service started");
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void addUsers(String id, String pass) {
        auth.addUsers(id, pass);

        File file = new File("Serializable.txt");
        file.createNewFile();
        FileOutputStream fout = new FileOutputStream(file, false);    
        ObjectOutputStream out = new ObjectOutputStream(fout);    
        out.writeObject(auth);    
        out.flush();    
        out.close();    
    }

    public JSONObject doSomething(String ip, String id, String pass) {
        JSONObject jsonObj = new JSONObject();
        if (!auth.checkPass(ip, id, pass)) {
            // return empty, user or pass not valid!
            return jsonObj;
        }

        try {
            jsonObj = auth.tokenGenerator(id);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return jsonObj;
    }
}