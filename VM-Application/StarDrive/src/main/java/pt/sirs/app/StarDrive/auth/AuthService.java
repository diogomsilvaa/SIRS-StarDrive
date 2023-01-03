package pt.sirs.app.StarDrive.auth;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AuthService {
    
    private String token;
    private Key aesKey;
    private String message;

    public AuthService(String _token){
        setToken(_token);
        String keyString = System.getenv("STARDRIVE_AES_KEY");
        aesKey = new SecretKeySpec(keyString.getBytes(), "AES");
    }

    
    
    public void decrypt() throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(getToken().getBytes());
        message = new String(cipher.doFinal(encrypted));
    }
    
    public void setToken(String _token) {
        this.token = _token;
    }
    
    public String getToken() {
        return token;
    }

    public boolean VerifyTimeStamp(){
        
        
        // Verificar a timestamp

        // Obter user
        
        return true;
    }
}
