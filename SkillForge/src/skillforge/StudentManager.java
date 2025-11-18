/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skillforge;

/**
 *
 * @author zmezm
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StudentManager {

    private final UserDatabaseManager studentDb;
    private final JsonDatabaseManager<Course> courseDb;

    public StudentManager(UserDatabaseManager studentDb, JsonDatabaseManager<Course> courseDb) {
        this.studentDb = studentDb;
        this.courseDb = courseDb;
    }

    public List<Course> getAllCourses() {
        return courseDb.getAll();
    }

    public List<Course> getEnrolledCourses(Student student) {
        List<Course> allCourses = courseDb.getAll();
        List<Course> enrolled = new ArrayList<>();

        for (Course c : allCourses) {

            if (student.getEnrolledCourses().contains(c.getCourseID())) {
                enrolled.add(c);
            }
        }
        return enrolled;
    }

    public boolean enrollStudentInCourse(Student student, Course course) {
        if (student.getEnrolledCourses().contains(course.getCourseID())) {
            return false;
        }

        student.getEnrolledCourses().add(course.getCourseID());
        studentDb.update(student.getUserId(), student);

        course.enrollStudent(student);
        courseDb.update(course.getCourseID(), course);

        return true;
    }

    public void markLessonCompleted(Student student, Course course, String lessonId) {

        student.getProgress().computeIfAbsent(course.getCourseID(), k -> new ArrayList<>());

        List<String> completedLessons = student.getProgress().get(course.getCourseID());

        if (!completedLessons.contains(lessonId)) {
            completedLessons.add(lessonId);
            studentDb.update(student.getUserId(), student);
        }
    }

    public List<String> getCompletedLessons(Student student, Course course) {
    // Step 1: Get all the student's progress
    Map<String, List<String>> progress = student.getProgress();
    
    // Step 2: Get completed lessons for THIS course
    List<String> courseProgress = progress.get(course.getCourseID());
    
    // Step 3: If student hasn't started this course, return empty list
    if (courseProgress == null) {
        return new ArrayList<>();
    }
    
    // Step 4: Get all valid lesson IDs from the course
    List<String> validLessonIds = new ArrayList<>();
    for (Lesson lesson : course.getLessons()) {
        validLessonIds.add(lesson.getLessonID());
    }
    
    // Step 5: Keep only completed lessons that still exist in the course
    List<String> result = new ArrayList<>();
    for (String lessonId : courseProgress) {
        // Check if this lesson still exists in the course
        if (validLessonIds.contains(lessonId)) {
            result.add(lessonId);
        }
    }
    studentDb.update(student.getUserId(), student);
    return result;
}

    public boolean isLessonCompleted(Student student, Course course, String lessonId) {
        List<String> completedLessons = getCompletedLessons(student, course);
        return completedLessons.contains(lessonId);
    }

    public Optional<Student> getStudentById(String studentId) {
        Student s = studentDb.getStudentById(studentId); 
        return s != null ? Optional.of(s) : Optional.empty();
    }
    public double getCoursePercentage(Student student, Course course) {
    int totalLessons = course.getLessons().size();
    if (totalLessons == 0) {
        return 0.0;
    }
    List<String> completedLessons = getCompletedLessons(student, course);
    int completedCount = completedLessons.size();
   
    return (completedCount * 100.0) / totalLessons;
}
    
}
