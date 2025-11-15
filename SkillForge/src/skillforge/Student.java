package skillforge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends User {

    private ArrayList<String> enrolledCourses;
    private Map<String, List<String>> progress;

    // Default constructor for Gson deserialization
    public Student() {
        super();
        this.enrolledCourses = new ArrayList<>();
        this.progress = new HashMap<>();
    }

    public Student(String userId, String username, String email, String passwordHash) {
        super(userId, "Student", username, email, passwordHash);
        this.enrolledCourses = new ArrayList<>();
        this.progress = new HashMap<>();
    }

    public void enrollInCourse(String courseId) {
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);
            if (progress == null) {
                progress = new HashMap<>();
            }
            progress.put(courseId, new ArrayList<>());
        }
    }

    public ArrayList<String> getEnrolledCourses() {
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }
        return enrolledCourses;
    }

    public Map<String, List<String>> getProgress() {
        if (progress == null) {
            progress = new HashMap<>();
        }
        return progress;
    }
}
