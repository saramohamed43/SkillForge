/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skillforge;

/**
 *
 * @author zmezm
 */
public class StudentManager {
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
   
   
}
       
}
