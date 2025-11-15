package skillforge;

import java.util.UUID;
import static skillforge.User.hashPassword;

public class UserAuth {

    private static final UserDatabaseManager db = new UserDatabaseManager();

    public static boolean signup(String username, String email, String password, String role) {
        try {
            if (username == null || username.trim().isEmpty()) {
                System.err.println("Validation Error: Username cannot be empty");
                return false;
            }
            if (email == null || email.trim().isEmpty()) {
                System.err.println("Validation Error: Email cannot be empty");
                return false;
            }
            if (password == null || password.isEmpty()) {
                System.err.println("Validation Error: Password cannot be empty");
                return false;
            }
            if (!User.isValidEmail(email)) {
                System.err.println("Validation Error: Invalid email format");
                return false;
            }
            if (db.getUserByEmail(email) != null) {
                System.err.println("Validation Error: Email already exists");
                return false;
            }
            String passwordHash = hashPassword(password);
            String userId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            User newUser;
            if (role.equalsIgnoreCase("Student")) {
                newUser = new Student(userId, username, email, passwordHash);
            } else if (role.equalsIgnoreCase("Instructor")) {
                newUser = new Instructor(userId, username, email, passwordHash);
            } else {
                System.err.println("Validation Error: Invalid role. Must be 'Student' or 'Instructor'");
                return false;
            }

            return db.add(newUser);

        } catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
            return false;
        }
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
}
