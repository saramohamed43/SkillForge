/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skillforge;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class InstructorManager extends JsonDatabaseManager<Instructor> {

    private final CourseManagment courseManager;

    public InstructorManager(String usersFilePath, String coursesFilePath, Type userType, Type courseType) {
        super(usersFilePath, userType);
        this.courseManager = new CourseManagment(coursesFilePath, courseType);
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

    public List<Instructor> getAllInstructors() {
        List<Instructor> all = read();
        List<Instructor> instructors = new java.util.ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if ("instructor".equalsIgnoreCase(all.get(i).getRole())) {
                instructors.add(all.get(i));
            }
        }
        return instructors;
    }

    public boolean createCourse(Instructor instructor, String courseId, String title, String description) {
        if (!"instructor".equalsIgnoreCase(instructor.getRole())) {
            return false;
        }

        try {
            Course course = new Course(courseId, title, description, instructor.getUserId());
            boolean saved = courseManager.add(course);
            if (saved) {
                instructor.addCreatedCourses(courseId);
                update(instructor.getUserId(), instructor);
            }
            return saved;
        } catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
            return false;
        }
    }

    public boolean editCourse(String courseId, String newTitle, String newDescription) {
        Course course = courseManager.getItemById(courseId);
        if (course == null) {
            return false;
        }

        try {
            course.setCourseTitle(newTitle);
            course.setCourseDescription(newDescription);
            return courseManager.update(courseId, course);
        } catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
            return false;
        }
    }

    public boolean addLesson(String courseId, String lessonId, String title, String content) {
        Course course = courseManager.getItemById(courseId);
        if (course == null) {
            return false;
        }

        try {
            Lesson lesson = new Lesson(lessonId, title, content);
            course.addLesson(lesson);
            return courseManager.update(courseId, course);
        } catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
            return false;
        }
    }

    public boolean editLesson(String courseId, String lessonId, String newTitle, String newContent) {
        Course course = courseManager.getItemById(courseId);
        if (course == null) {
            return false;
        }

        List<Lesson> lessons = course.getLessons();
        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            if (lesson.getLessonID().equals(lessonId)) {
                try {
                    lesson.setLessonTitle(newTitle);
                    lesson.setLessonContent(newContent);
                    lessons.set(i, lesson);
                    return courseManager.update(courseId, course);
                } catch (IllegalArgumentException e) {
                    System.err.println("Validation Error: " + e.getMessage());
                    return false;
                }
            }
        }
        return false;
    }

    public void deleteLesson(String courseId, String lessonId) {
        courseManager.removeLesson(courseId, lessonId);
    }

    public ArrayList<Student> getEnrolledStudents(Course course) {
        return courseManager.getEnrolledStudents(course);
    }
}
