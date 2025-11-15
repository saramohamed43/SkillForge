package skillforge;

import java.util.ArrayList;

public class Student extends User{
    private ArrayList<Integer> enrolledCourses = new ArrayList<>();

    public Student(int userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
    }

    public ArrayList<Integer> getEnrolledCourses() {
        return enrolledCourses;
    }
}

