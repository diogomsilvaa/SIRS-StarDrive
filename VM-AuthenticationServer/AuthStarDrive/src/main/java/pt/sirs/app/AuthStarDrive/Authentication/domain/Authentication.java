package pt.sirs.app.AuthStarDrive.Authentication.domain;

import java.util.Base64;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.NoPermissionException;

import org.json.JSONObject;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

public class Authentication implements Serializable{
    private static final long serialVersionUID = 1L;
    private byte[] salt;
    private SecretKey serverKey;
    private HashMap<String, byte[]> users = new HashMap<String, byte[]>();

    public Authentication() throws Exception{
        serverKey = getKey("BackendKey.txt");
        initializePBKDF2();
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

    public void addUser(String id, String pass) throws Exception {
        if (users.containsKey(id)) {
            throw new Exception("User already exists");
        }
        byte[] encPass = hashPass(pass);
        users.put(id, encPass);
    }

    public String getPassword(String id) throws NoSuchElementException {
        String userPass = users.get(id);
        if (userPass == null) {
            throw new NoSuchElementException();
        }
        return userPass;
    }

    public boolean checkPass(String id, String pass) throws NoPermissionException{
        String encPass;
        String realPass;
        encPass = hashPass(pass);
        realPass = getPassword(id);
        if (Arrays.equals(encPass, realPass)) {
            return true;
        }
        throw new NoPermissionException();
    }

    public byte[] encrypt(String data) throws Exception{
        Cipher encrypt = Cipher.getInstance("AES");
        encrypt.init(Cipher.ENCRYPT_MODE, serverKey);
        byte[] encryptedText = encrypt.doFinal(data.getBytes());
        return encryptedText;
    }

    public byte[] tokenGenerator(String id) throws Exception{
        String token = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();

        token = dtf.format(now) + ":";
        now.plusMinutes(10);
        token = token + dtf.format(now) + ":" + id;

        return encrypt(token);
    }

    public boolean changePass(String id, String oldPass, String newPass) throws Exception {
        boolean check = checkPass(id, oldPass);
        if (check) {
            users.put(id, hashPass(newPass));
        }
        return check;
    }

    private void initializePBKDF2() {
        SecureRandom random = new SecureRandom();
        salt = new byte[16];
        random.nextBytes(salt);
    }

    private byte[] hashPass(String pass) {
        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }
}