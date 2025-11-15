package skillforge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends User{
    private ArrayList<String> enrolledCourses = new ArrayList<>();
    private Map<String,List<String>> progress; 

    public Student(String userId,String username, String email, String passwordHash) {
        super(userId,"Student", username, email, passwordHash);
        this.enrolledCourses=new ArrayList<>();
        this.progress = new HashMap<>();
    }
    public void enrollInCourse (String courseId){
        if(!enrolledCourses.contains(courseId)){
           enrolledCourses.add(courseId);
            progress.put(courseId,new ArrayList<>());
        }
    }
    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public Map<String, List<String>> getProgress() {
        return progress;
    }
}

