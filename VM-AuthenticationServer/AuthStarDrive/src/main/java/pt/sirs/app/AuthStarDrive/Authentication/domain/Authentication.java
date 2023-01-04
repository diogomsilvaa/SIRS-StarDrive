package pt.sirs.app.AuthStarDrive.Authentication.domain;

import java.util.Base64;
import java.util.HashMap;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

public class Authentication implements Serializable{
    private static final long serialVersionUID = 1L;
    private SecretKey myKey, serverKey;
    private HashMap<String, String> users = new HashMap<String, String>();

    public Authentication() throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        myKey = generator.generateKey();
        serverKey = getKey("BackendKey.txt");
        myKey = getKey("AuthKey.txt");
    }

    public SecretKey getKey(String filename) throws Exception{
        File file = new File(filename);
 
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = br.readLine();
        br.close();

        byte[] decodedKey = Base64.getDecoder().decode(st);
        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
        return key;
    }

    public void addUsers(String id, String pass) throws Exception {
        if (users.containsKey(id)) {
            throw new Exception("User already has password");
        }
        String encPass = encrypt(true, pass);
        users.put(id, encPass);
    }

    public String getPassword(String id) throws Exception {
        String userPass = users.get(id);
        if (userPass == null) {
            throw new Exception("User doesn´t exist");
        }
        return userPass;
    }

    public boolean checkPass(String ip, String id, String pass) {
        String encPass;
        String realPass;
        try {
            encPass = encrypt(true, pass);
            realPass = getPassword(id).toString();
        }
        catch (Exception e) {
            return false;
        }
        if (realPass == null) {
            return false;
        }
        if (realPass == encPass) {
            return true;
        }
        return false;
    }

    public byte[] encrypt(Boolean isAuth, String data) throws Exception{
        SecretKey key;
        if (isAuth) {
            key = myKey;
        } else {
            key = serverKey;
        }
        Cipher encrypt = Cipher.getInstance("AES");
        encrypt.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedText = encrypt.doFinal(data.getBytes());
        return encryptedText;
    }

    public JSONObject tokenGenerator(String id) throws Exception{
        JSONObject jo = new JSONObject();
        String token = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();

        token = dtf.format(now) + ":";
        now.plusMinutes(10);
        token = token + dtf.format(now) + ":" + id;

        byte[] encryptToken = encrypt(false, token);
        jo.put("token", encryptToken);

        return jo;
    }
}