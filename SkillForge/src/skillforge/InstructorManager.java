/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skillforge;

import java.lang.reflect.Type;
import java.util.List;

public class InstructorManager extends JsonDatabaseManager<Instructor> {

    private final CourseManager courseManager; // manages courses/lessons

    public InstructorManager(String usersFilePath, String coursesFilePath, Type userType, Type courseType) {
        super(usersFilePath, userType);
        this.courseManager = new CourseManager(coursesFilePath, courseType);
    }

    @Override
    public String getId(Instructor item) {
        return item.getUserId();
    }

    @Override
    protected boolean isDuplicate(Instructor item, List<Instructor> existingItems) {
    for (int i = 0; i < existingItems.size(); i++) {
        Instructor u = existingItems.get(i);
        if (u.getEmail().equalsIgnoreCase(item.getEmail())) {
            return true; 
        }
    }
    return false;
}

    @Override
    public String getEmail(Instructor item) {
        return item.getEmail();
    }

    // Optional: list all instructors
    public List<Instructor> getAllInstructors() {
        return read().stream()
                .filter(u -> "instructor".equalsIgnoreCase(u.getRole()))
                .toList();
    }

    public boolean createCourse(Instructor instructor, String courseId, String title, String description) {
        if (!"instructor".equalsIgnoreCase(instructor.getRole())) return false;

        Course course = new Course(courseId, title, description, instructor.getUserId());
        boolean saved = courseManager.add(course);
        if (saved) {
            instructor.addCourse(courseId);
            update(instructor.getUserId(), instructor); // save updated instructor
        }
        return saved;
    }

    public boolean editCourse(String courseId, String newTitle, String newDescription) {
        Course course = courseManager.find(courseId);
        if (course == null) return false;

        course.setTitle(newTitle);
        course.setDescription(newDescription);
        return courseManager.update(courseId, course);
    }

    public void addLesson(String courseId, String lessonId, String title, String content) {
        Lesson lesson = new Lesson(lessonId, title, content);
        courseManager.addLesson(courseId, lesson);
    }

    public void editLesson(String courseId, String lessonId, String newTitle, String newContent) {
        Course course = courseManager.find(courseId);
        if (course == null) return;

        List<Lesson> lessons = course.getLessons();
for (int i = 0; i < lessons.size(); i++) {
    Lesson lesson = lessons.get(i);
    if (lesson.getLessonId().equals(lessonId)) {
        lesson.setTitle(newTitle);
        lesson.setContent(newContent);
        lessons.set(i, lesson);
        courseManager.update(courseId, course);
        return;
    }
    }

    public void deleteLesson(String courseId, String lessonId) {
        courseManager.deleteLesson(courseId, lessonId);
    }

    public List<String> getEnrolledStudents(String courseId) {
        return courseManager.getEnrolledStudents(courseId);
    }
}
