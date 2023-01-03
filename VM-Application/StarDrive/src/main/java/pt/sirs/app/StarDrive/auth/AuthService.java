package pt.sirs.app.StarDrive.auth;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

public class AuthService {
    
    private String token;
    private Key aesKey;
    private String message;
    private String id;

    public AuthService(String _token){
        setToken(_token);
        String keyString = System.getenv("STARDRIVE_AES_KEY");
        aesKey = new SecretKeySpec(keyString.getBytes(), "AES");
    }

    
    
    private void decrypt() throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(getToken().getBytes());
        message = new String(encrypted);
    }
    
    
    private boolean verifyTimeStamp(){
        String loginTimeStr = message.split(":")[1];
        String expirationTimeStr = message.split(":")[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime loginTime = LocalDateTime.parse(loginTimeStr, formatter);
        LocalDateTime expirationTime = LocalDateTime.parse(expirationTimeStr, formatter);

        if (loginTime.isBefore(LocalDateTime.now()) && expirationTime.isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    public String VerifyToken() throws Exception{
        this.decrypt();
        // parse the token and check if its well built
        if(!this.verifyTimeStamp()) throw new TimeoutException();
        return this.getId();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        if(this.id == null)
            this.id = message.split(":")[0];
        return this.id;
    }

    public void setToken(String _token) {
        this.token = _token;
    }
    
    public String getToken() {
        return token;
    }
}
