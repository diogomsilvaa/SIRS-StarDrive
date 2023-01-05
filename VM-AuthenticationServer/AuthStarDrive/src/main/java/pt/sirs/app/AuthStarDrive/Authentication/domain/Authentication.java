package pt.sirs.app.AuthStarDrive.Authentication.domain;

import java.util.Base64;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.NoPermissionException;


import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

public class Authentication implements Serializable{
    private static final long serialVersionUID = 1L;
    private byte[] salt;
    private SecretKey serverKey;
    private HashMap<String, byte[]> users = new HashMap<String, byte[]>();

    public Authentication() throws Exception{
        serverKey = getKey(System.getenv("BACK_SERVER_KEY"));
        initializePBKDF2();
    }

    public SecretKey getKey(String key) throws Exception{
 
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey secKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
        return secKey;
    }

    public void addUser(String id, String pass) throws Exception {
        if (users.containsKey(id)) {
            throw new Exception("User already exists");
        }
        byte[] encPass = hashPass(pass);
        users.put(id, encPass);
    }

    public byte[] getPassword(String id) throws NoSuchElementException {
        byte[] userPass = users.get(id);
        if (userPass == null) {
            throw new NoSuchElementException();
        }
        return userPass;
    }

    public boolean checkPass(String id, String pass) throws Exception, NoPermissionException{
        byte[] encPass;
        byte[] realPass;
        encPass = hashPass(pass);
        realPass = getPassword(id);
        if (Arrays.equals(encPass, realPass)) {
            return true;
        }
        throw new NoPermissionException();
    }

    private String encrypt(String data) throws Exception{
        Cipher encrypt = Cipher.getInstance("AES");
        encrypt.init(Cipher.ENCRYPT_MODE, serverKey);
        return Base64.getEncoder().encodeToString(encrypt.doFinal(data.getBytes()));
    }

    public String tokenGenerator(String id) throws Exception{
        String token = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();

        token = "StarDrive;";
        token += dtf.format(now) + ";";
        token += dtf.format(now.plusMinutes(10)) + ";" + id;

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

    private byte[] hashPass(String pass) throws Exception {
        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return factory.generateSecret(spec).getEncoded();
    }
}