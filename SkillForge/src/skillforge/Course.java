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
        if (courseID == null || courseID.trim().isEmpty()) {
            throw new IllegalArgumentException("Course ID cannot be empty");
        }
        if (!courseID.matches("^[A-Za-z0-9]+$")) {
            throw new IllegalArgumentException("Course ID must contain only letters and numbers");
        }
        if (courseTitle == null || courseTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Course title cannot be empty");
        }
        if (courseDescription == null || courseDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Course description cannot be empty");
        }
        if (instructorID == null || instructorID.trim().isEmpty()) {
            throw new IllegalArgumentException("Instructor ID cannot be empty");
        }
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.instructorID = instructorID;
        this.lessons = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public Lesson getLesson(String lessonID) {
        if (lessonID == null || lessonID.trim().isEmpty()) {
            throw new IllegalArgumentException("lesson ID cannot be empty");
         }
        for (int i = 0; i < lessons.size(); i++) {
            if (lessonID.equals(lessons.get(i).getLessonID())) {
                return lessons.get(i);
            }
        }
        return null;
    }

    public void addLesson(Lesson lesson) {
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson cannot be null");
        }
        // Check for duplicate lesson ID
        if (getLesson(lesson.getLessonID()) != null) {
            throw new IllegalArgumentException("Lesson with ID " + lesson.getLessonID() + " already exists");
        }
        lessons.add(lesson);
    }

    public boolean editLesson(String lessonID, String newTitle, String newContent, ArrayList<String> newResources) {
        Lesson lesson = getLesson(lessonID);
        if (lesson == null) {
            return false;
        }

        lesson.setLessonTitle(newTitle);
        lesson.setLessonContent(newContent);
        lesson.setOptionalResources(newResources);
        return true;
    }

    public void enrollStudent(Student student) {
        students.add(student);
    }

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
}
