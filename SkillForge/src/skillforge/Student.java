package skillforge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student extends User{
    private ArrayList<String> enrolledCourses = new ArrayList<>();
    private Map<String,Integer> progress; 

    public Student(int userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
        this.enrolledCourses=new ArrayList<>();
        this.progress = new HashMap<>();
    }
    public void enrollInCourse (String courseId){
        if(!enrolledCourses.contains(courseId)){
           enrolledCourses.add(courseId);
            progress.put(courseId,0);
        }
    }
    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public Map<String, Integer> getProgress() {
        return progress;
    }
}

