package skillforge;

import java.util.UUID;
import static skillforge.User.hashPassword;

public class UserAuth {

    private static final UserDatabaseManager db = new UserDatabaseManager();

    public static boolean signup(String username, String email, String password, String role) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return false;
        }
        if (!email.contains("@") || !email.contains(".")) {
            return false;
        }
        if (db.getUserByEmail(email) != null) {
            return false;
        }

        String userId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String passwordHash = hashPassword(password);
        User newUser;
        if (role.equalsIgnoreCase("Student")) {
            newUser = new Student(userId, username, email, passwordHash);

        } else if (role.equalsIgnoreCase("Instructor")) {
            newUser = new Instructor(userId, username, email, passwordHash);
        } else {
            return false;
        }
        return db.add(newUser);
    }

    public static User login(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return null;
        }
        User user = db.getUserByEmail(email);
        if (user == null) {
            return null;
        }
        String hashedPassword = hashPassword(password);
        if (!user.getPasswordHash().equals(hashedPassword)) {
            return null;
        }
        return user;
    }
}
