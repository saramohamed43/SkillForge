package skillforge;

import java.util.ArrayList;

public class Instructor extends User {
    private ArrayList<Integer> createdCourses = new  ArrayList<>();

    public Instructor(int userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
    }

    public ArrayList<Integer> getCreatedCourses() {
        return createdCourses;
    }
    
}
