/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skillforge;

/**
 *
 * @author zmezm
 */
public class StudentManager extends {
    private final ServiceDatabase serviceDatabase;
    

public StudentManager(ServiceDatabase serviceDatabase){
this.serviceDatabase=serviceDatbase;
}

public List<Course> getAllCourses(){
return serviceDatabase.loadAllCourses;
}
public List<Course> getEnrolledCourses(Student student){
    List<Course> allCourses = serviceDatabase.loadAllCourses;
    List<Course> enrolled =new ArrayList<>();
    for(Course c:allCourses){
        if (student.getEnrolledCourses().contains(c.getCourseID()))
            enrolled.add(c);
        
    }
    return enrolled;
}
public boolean enrollStudentInCourse(Student student,Course course){
   if (student.getEnrolledCourses.contains(course.getCourseID())) 
       return false;
   
       student.getEnrolledCourses().add(course.getCourseId());
        serviceDatabase.updateStudent(student);
        course.getStudents().add(student.getUserId());
        serviceDatabase.updateCourse(course);
        return true;
}
  public void markLessonCompleted(Student student, Course course, String lessonId) {
        student.getProgress().computeIfAbsent(course.getCourseId(), k -> new ArrayList<>());

        List<String> completedLessons = student.getProgress().get(course.getCourseId());
        if (!completedLessons.contains(lessonId)) {
            completedLessons.add(lessonId);
            serviceDatabase.updateStudent(student);
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
        return serviceDatabase.getStudentById(studentId);
    }
}

