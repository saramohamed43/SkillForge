package skillforge;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    public static String hashPassword(String Password){
        try{
            MessageDigest digest= MessageDigest.getInstance("SHA-256");      
            byte[]hash=digest.digest(Password.getBytes("UTF-8"));
        StringBuilder hex=new StringBuilder();
        for(int i=0;i<hash.length;i++){
            byte b=hash[i];
            hex.append(String.format("%02x",b ));
        }
        return hex.toString();
        }
        catch(UnsupportedEncodingException | NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
        }
    }
    
