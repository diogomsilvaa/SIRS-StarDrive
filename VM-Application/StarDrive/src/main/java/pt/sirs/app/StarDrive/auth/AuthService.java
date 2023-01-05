package pt.sirs.app.StarDrive.auth;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.concurrent.TimeoutException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class AuthService {
    
    private String token;
    private Key aesKey;
    private String message;
    private String id;
    private LocalDateTime expirationTime;
    private LocalDateTime loginTime;

    public AuthService(String _token){
        setToken(_token);
        String keyString = System.getenv("BACK_SERVER_KEY");
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        aesKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
    }

    
    
    private String decrypt() throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        message = new String(cipher.doFinal(Base64.getDecoder().decode(token))); 
        return message;
    }
    
    
    private boolean parse(){
        String company = message.split(";")[0];
        if(!company.equals("StarDrive")){
            throw new IllegalArgumentException("Invalid company");
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        String loginTimeStr = message.split(";")[1];
        try {
            loginTime = LocalDateTime.parse(loginTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid login time");
        }
        
        String expirationTimeStr = message.split(";")[2];
        try {
            expirationTime = LocalDateTime.parse(expirationTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid expiration time");
        }

        id = message.split(";")[3];

        
        return false;
    }

    private boolean isExpired(){
        return loginTime.isBefore(LocalDateTime.now()) && expirationTime.isAfter(LocalDateTime.now());
    }

    public void verifyToken() throws Exception{
        this.decrypt();
        this.parse();
        if(!this.isExpired()){
            throw new TimeoutException("Token expired");
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        if(this.id == null)
            this.id = message.split(";")[3];
        return this.id;
    }

    public void setToken(String _token) {
        this.token = _token;
    }
    
    public String getToken() {
        return token;
    }
}
