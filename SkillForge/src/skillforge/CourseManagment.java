package skillforge;
import java.util.ArrayList;

public class CourseManagment {

    public CourseManagement(JsonDatabase<Course> database) {
        super(database);
    }

    public Course findCourse(String courseID) {
        for (Course c : items) {
            if (c.getCourseID().equals(courseID)) {
                return c;
            }
        }
        return null;
    }

    public void addCourse(Course course) {
        add(course); // inherited from Service
    }

    public void removeCourse(Course course) {
        remove(course); // inherited
    }
}

    
}
