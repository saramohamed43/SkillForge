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
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty");
        }
   
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
        if (!role.equalsIgnoreCase("Student") && !role.equalsIgnoreCase("Instructor")) {
            throw new IllegalArgumentException("Role must be either 'Student' or 'Instructor'");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format. Must contain @ and .");
        }
    
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        
        this.userId = userId;
        this.role = role;
        this.username = username.trim();
        this.email = email.trim().toLowerCase();
        this.passwordHash = passwordHash;
    }
    
    protected User() {
        
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
