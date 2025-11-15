package skillforge;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class User {

    protected String userId;
    protected String role;
    protected String username;
    protected String email;
    protected String passwordHash;

    public User(String userId, String role, String username, String email, String passwordHash) {
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    protected User() {
        // Initialize with defaults
    }

    public static String hashPassword(String Password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(Password.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                byte b = hash[i];
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
