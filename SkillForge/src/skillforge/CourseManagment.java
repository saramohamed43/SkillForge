package skillforge;
import java.util.ArrayList;

//extend service class when done
public class CourseManagment {
    private String courseID;
    private String courseTitle;
    private String courseDescription;
    private String instructorID;
    private ArrayList<Lesson> lessons;
    private ArrayList<Student> students;
    
    public CourseManagment(String courseID, String courseTitle, String courseDescription, String instructorID, ArrayList<Lesson> lessons, ArrayList<Student> students) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.instructorID = instructorID;
        this.lessons = lessons;
        this.students = students;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    //add lesson from abstract class service
    //edit lesson
    //delete lesson
    //add student
    //remove student
    
}
