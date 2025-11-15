package skillforge;

import java.util.ArrayList;

public class Instructor extends User {

    private ArrayList<String> createdCourses = new ArrayList<>();

    public Instructor(String userId, String username, String email, String passwordHash) {
        super(userId, "Instructor", username, email, passwordHash);
        this.createdCourses = new ArrayList<>();
    }

    public Instructor() {
        super();
        this.createdCourses = new ArrayList<>();
    }

    public ArrayList<String> getCreatedCourses() {
        return createdCourses;
    }

    public void addCreatedCourses(String courseId) {
        if (!createdCourses.contains(courseId)) {
            createdCourses.add(courseId);
        }
    }

    public void removeCreatedCourse(String courseId) {
        if (createdCourses.contains(courseId)) {
            createdCourses.remove(courseId);
        }
    }
}
