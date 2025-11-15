package skillforge;

import java.lang.reflect.Type;
import java.util.List;

public class CourseManagment extends JsonDatabaseManager<Course> {
    public CourseManagment(String filename, Type elementType){
        super(filename, elementType);
    }
    
    public boolean createCourse(String courseID, String courseTitle, String courseDescription, String instructorID){
        Course course = new Course(courseID, courseTitle, courseDescription, instructorID);
        return add(course);
    }
    
    public Course browseCoursesByID(Course course){
        return find(course.getCourseID());
    }

    public boolean removeLesson(Lesson lesson){
        return deleteById(lesson.getLessonID());
    }
    
    public boolean removeStudent(Student student){
        return deleteById(student.getStudentId());
    }
    
    @Override
    public String getId(Course course){
        return course.getCourseID();
    }
    
    @Override
    protected boolean isDuplicate(Course course, List<Course> existingCourses){
        for(int i = 0; i < existingCourses.size(); i++){
            if(course.getCourseID().equals(existingCourses.get(i).getCourseID())){
                return true;
            }
        }
        return false;
    }
}
