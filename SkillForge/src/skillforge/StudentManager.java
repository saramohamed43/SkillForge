/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skillforge;

/**
 *
 * @author zmezm
 */
public class StudentManager  {
   private final JsonDatabaseManager<Student> studentDb;
    private final JsonDatabaseManager<Course> courseDb;

    public StudentManager(JsonDatabaseManager<Student> studentDb, JsonDatabaseManager<Course> courseDb) {
        this.studentDb = studentDb;
        this.courseDb = courseDb;
}

public List<Course> getAllCourses(){
return courseDb.getAll;
}
 public List<Course> getEnrolledCourses(Student student) {
        List<Course> allCourses = courseDb.getAll();
        List<Course> enrolled = new ArrayList<>();

        for (Course c : allCourses) {
            if (student.getEnrolledCourses().contains(c.getCourseId())) {
                enrolled.add(c);
            }
        }
        return enrolled;
    }
 public boolean enrollStudentInCourse(Student student, Course course) {
        if (student.getEnrolledCourses().contains(course.getCourseId())) {
            return false; 
        }

        student.getEnrolledCourses().add(course.getCourseId());
        studentDb.update(student.getUserId(), student);

        course.getStudents().add(student.getUserId());
        courseDb.update(course.getCourseId(), course);

        return true;
    }

  public void markLessonCompleted(Student student, Course course, String lessonId) {
        student.getProgress().computeIfAbsent(course.getCourseId(), k -> new ArrayList<>());

        List<String> completedLessons = student.getProgress().get(course.getCourseId());
        if (!completedLessons.contains(lessonId)) {
            completedLessons.add(lessonId);
            studentDb.update(student.getUserId(), student);
        }
    }
    public List<String> getCompletedLessons(Student student, Course course) {
        return student.getProgress().getOrDefault(course.getCourseId(), new ArrayList<>());
    }

    public boolean isLessonCompleted(Student student, Course course, String lessonId) {
        List<String> completedLessons = getCompletedLessons(student, course);
        return completedLessons.contains(lessonId);
    }

    
    public Optional<Student> getStudentById(String studentId) {
        Student s = studentDb.find(studentId);
        return s != null ? Optional.of(s) : Optional.empty();
    }
}

