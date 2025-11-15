package skillforge;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CourseManagment extends JsonDatabaseManager<Course> {

    public CourseManagment(String filename, Type elementType) {
        super(filename, elementType);
    }

    public boolean createCourse(String courseID, String courseTitle, String courseDescription, String instructorID) {
        Course course = new Course(courseID, courseTitle, courseDescription, instructorID);
        return add(course);
    }

    public Course getCourseByID(String id) {
        return getItemById(id);
    }

    public Course browseCoursesByID(String courseID) {
        return getItemById(courseID);
    }

    public boolean removeLesson(String courseID, String lessonID) {
        Course course = getItemById(courseID);
        if (course == null) {
            return false;
        }

        Lesson lessonToRemove = course.getLesson(lessonID);
        if (lessonToRemove == null) {
            return false;
        }

        course.getLessons().remove(lessonToRemove);
        return update(courseID, course);
    }

    public boolean removeStudent(String courseID, String studentID) {
        Course course = getItemById(courseID);
        if (course == null) {
            return false;
        }

        Student studentToRemove = null;
        for (Student s : course.getStudents()) {
            if (s.getUserId().equals(studentID)) {
                studentToRemove = s;
                break;
            }
        }

        if (studentToRemove == null) {
            return false;
        }
        course.getStudents().remove(studentToRemove);
        return update(courseID, course);
    }

    public boolean editCourse(String courseID, String newTitle, String newDescription) {
        Course course = getItemById(courseID);
        if (course == null) {
            return false;
        }

        course.setCourseTitle(newTitle);
        course.setCourseDescription(newDescription);
        return update(courseID, course);
    }

    public boolean addLesson(String courseID, Lesson lesson) {
        Course course = getItemById(courseID);
        if (course == null) {
            return false;
        }

        course.addLesson(lesson);
        return update(courseID, course);
    }

    public List<Course> getCoursesByInstructor(String instructorID) {
        List<Course> allCourses = getAll();
        List<Course> instructorCourses = new ArrayList<>();

        for (Course c : allCourses) {
            if (c.getInstructorID().equals(instructorID)) {
                instructorCourses.add(c);
            }
        }
        return instructorCourses;
    }

    public ArrayList<Student> getEnrolledStudents(Course course) {
        return course.getStudents();
    }

    @Override
    public String getId(Course course) {
        return course.getCourseID();
    }

    @Override
    protected boolean isDuplicate(Course course, List<Course> existingCourses) {
        for (int i = 0; i < existingCourses.size(); i++) {
            if (course.getCourseID().equals(existingCourses.get(i).getCourseID())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getEmail(Course course) {
        throw new UnsupportedOperationException("Course does not have an email");
    }
}
