package skillforge;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static skillforge.User.hashPassword;

public class UserAuth {

    private static final UserDatabaseManager db = new UserDatabaseManager();
    private static List<String> validationErrors = new ArrayList<>();
    public static String signup(String username, String email, String password, String role) {
        validationErrors.clear(); 
        try {
            if (username == null || username.trim().isEmpty()) {
                validationErrors.add("Validation Error: Username cannot be empty");
               
            }
            if (!isValidUsername(username)) {
                validationErrors.add("Validation Error: Username can only contain letters and spaces");
         
            }
            if (email == null || email.trim().isEmpty()) {
                 validationErrors.add("Validation Error: Email cannot be empty");
                
            }
            if (password == null || password.isEmpty()) {
                 validationErrors.add("Validation Error: Password cannot be empty");
              
            }
            if (!User.isValidEmail(email)) {
                 validationErrors.add("Validation Error: Invalid email format");
              
            }
            if (db.getUserByEmail(email) != null) {
                 validationErrors.add("Validation Error: Email already exists");
               
            }
            String passwordHash = hashPassword(password);
            String userId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            User newUser;
            if (role.equalsIgnoreCase("Student")) {
                newUser = new Student(userId, username, email, passwordHash);
            } else if (role.equalsIgnoreCase("Instructor")) {
                newUser = new Instructor(userId, username, email, passwordHash);
            } else {
                 validationErrors.add("Validation Error: Invalid role. Must be 'Student' or 'Instructor'");
                return null;
            }
            if (!validationErrors.isEmpty()) {
                for (String error : validationErrors) {
                    System.err.println("Errors:\n " + error);
                }
                return null;
            }

            boolean added = db.add(newUser);
             if (added) {
            return userId;  
        } else {
            return null;   
        }
        }  catch (IllegalArgumentException e) {
            validationErrors.add("• " + e.getMessage());
            System.err.println("Validation Error: " + e.getMessage());
            return null;
        }
    } 
 public static String getAllErrors() {
        if (validationErrors.isEmpty()) {
            return "Unknown error occurred";
        }
        StringBuilder sb = new StringBuilder();
        for (String error : validationErrors) {
            sb.append(error).append("\n");
        }
        return sb.toString().trim();
    }
    public static User login(String email, String password) {
        try {
            if (email == null || email.trim().isEmpty()) {
                System.err.println("Validation Error: Email cannot be empty");
                return null;
            }
            if (password == null || password.isEmpty()) {
                System.err.println("Validation Error: Password cannot be empty");
                return null;
            }
            User user = db.getUserByEmail(email);
            if (user == null) {
                System.err.println("Login Error: User not found");
                return null;
            }

            String hashedPassword = hashPassword(password);
            if (!user.getPasswordHash().equals(hashedPassword)) {
                System.err.println("Login Error: Incorrect password");
                return null;
            }

            return user;

        } catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
            return null;
        }
    }
     private static boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z ]+$");
    }
}
