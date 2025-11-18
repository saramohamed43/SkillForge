package skillforge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

public class UserDatabaseManager extends JsonDatabaseManager<User> {

    public UserDatabaseManager() {
        super("users.json", User.class);
    }

    @Override
    protected Gson createGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(User.class, new UserTypeAdapter())
                .create();
    }

    @Override
    public String getId(User item) {
        return item.getUserId();
    }

    @Override
    public String getEmail(User item) {
        return item.getEmail();
    }

    @Override
    protected boolean isDuplicate(User item, List<User> existingItems) {
        return existingItems.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(item.getEmail()));
    }

    public Student getStudentById(String studentId) {
        User user = getItemById(studentId);
        if (user instanceof Student) {
            return (Student) user;
        }
        return null;
    }

    public Instructor getInstructorById(String instructorId) {
        User user = getItemById(instructorId);
        if (user instanceof Instructor) {
            return (Instructor) user;
        }
        return null;
    }
    
}
