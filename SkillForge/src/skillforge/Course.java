package skillforge;

import java.util.ArrayList;

public class Course {

    private String courseID;
    private String courseTitle;
    private String courseDescription;
    private String instructorID;

    private ArrayList<Lesson> lessons;
    private ArrayList<Student> students;

    public Course(String courseID, String courseTitle, String courseDescription, String instructorID) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.instructorID = instructorID;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void editLesson(Lesson lesson, String newID, String newTitle, String newContent, ArrayList<String> newResources) {
        lesson.setLessonID(newID);
        lesson.setLessonTitle(newTitle);
        lesson.setLessonContent(newContent);
        lesson.setOptionalRresources(newResources);
    }

    public void enrollStudent(Student student) {
        students.add(student);
    }

    public String getCourseID() { return courseID; }
    public String getCourseTitle() { return courseTitle; }
    public String getCourseDescription() { return courseDescription; }
    public String getInstructorID() { return instructorID; }
    public ArrayList<Lesson> getLessons() { return lessons; }
    public ArrayList<Student> getStudents() { return students; }

    public void setCourseID(String courseID) { this.courseID = courseID; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
    public void setCourseDescription(String courseDescription) { this.courseDescription = courseDescription; }
    public void setInstructorID(String instructorID) { this.instructorID = instructorID; }
}
