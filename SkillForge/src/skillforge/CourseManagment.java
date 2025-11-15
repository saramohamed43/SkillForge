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
    private ArrayList<CourseManagment> courses = new ArrayList<>();
    
    public CourseManagment(String courseID, String courseTitle, String courseDescription, String instructorID) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.instructorID = instructorID;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student){
        students.add(student);
    }
    
    public void addCourse(CourseManagment course){
        courses.add(course);
    }
    
    public void addLesson(Lesson lesson){
        lessons.add(lesson);
    }
    
    public Lesson createLesson(String lessonID, String lessonTitle, String lessonContent){
        Lesson lesson = new Lesson(lessonID, lessonTitle, lessonContent);
        return lesson;
    }
    
    public void editLesson(Lesson lesson, String lessonID, String lessonTitle, String lessonContent, ArrayList<String> resources){
        lesson.setLessonID(lessonID);
        lesson.setLessonTitle(lessonTitle);
       lesson.setLessonContent(lessonContent);
       lesson.setOptionalRresources(resources);
    }
    
    public void enrollStudent(Student student){
        students.add(student);
    }
    
    public CourseManagment browseCourses(String courseID){
        return find(courseID);
    }
    
    //remove iteam
    
   //save
    
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

    
}
