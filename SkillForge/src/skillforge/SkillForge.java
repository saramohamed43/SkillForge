/*package skillforge;

import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SkillForge {
    
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    private static UserDatabaseManager userDb;
    private static CourseManagment courseManager;
    private static StudentManager studentManager;
    private static InstructorManager instructorManager;
    
    public static void main(String[] args) {
        // Initialize system
        initializeSystem();
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     WELCOME TO SKILLFORGE SYSTEM      ║");
        System.out.println("║   Online Learning Platform Manager    ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        // Main application loop
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                if (currentUser instanceof Student) {
                    showStudentMenu();
                } else if (currentUser instanceof Instructor) {
                    showInstructorMenu();
                }
            }
        }
    }
    
    private static void initializeSystem() {
        courseManager = new CourseManagment(
            "courses.json", 
            new TypeToken<Course>(){}.getType()
        );
        
        userDb = new UserDatabaseManager();
        studentManager = new StudentManager(userDb, courseManager);
        
        instructorManager = new InstructorManager(
            "users.json",
            "courses.json",
            new TypeToken<Instructor>(){}.getType(),
            new TypeToken<Course>(){}.getType()
        );
    }
    
    private static void showLoginMenu() {
        System.out.println("\n─────────────────────────────────────");
        System.out.println("│        AUTHENTICATION MENU        │");
        System.out.println("─────────────────────────────────────");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        System.out.println("3. Exit System");
        System.out.println("─────────────────────────────────────");
        System.out.print("Choose an option: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                handleSignup();
                break;
            case 2:
                handleLogin();
                break;
            case 3:
                System.out.println("\nThank you for using SkillForge!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    private static void handleSignup() {
        System.out.println("\n┌─────────────────────────┐");
        System.out.println("│     USER REGISTRATION    │");
        System.out.println("└─────────────────────────┘");
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        System.out.println("\nSelect role:");
        System.out.println("1. Student");
        System.out.println("2. Instructor");
        System.out.print("Choice: ");
        int roleChoice = getIntInput();
        
        String role = (roleChoice == 1) ? "Student" : "Instructor";
        
        boolean success = UserAuth.signup(username, email, password, role);
        
        if (success) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Registration failed. Email might already exist or invalid data.");
        }
    }
    
    private static void handleLogin() {
        System.out.println("\n┌─────────────────────────┐");
        System.out.println("│         LOGIN           │");
        System.out.println("└─────────────────────────┘");
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        currentUser = UserAuth.login(email, password);
        
        if (currentUser != null) {
            System.out.println("Login successful! Welcome, " + currentUser.getUsername() + "!");
            System.out.println("Role: " + currentUser.getRole());
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }
    
    private static void showStudentMenu() {
        Student student = (Student) currentUser;
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          STUDENT DASHBOARD            ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Welcome, " + student.getUsername() + " (ID: " + student.getUserId() + ")");
        System.out.println("\n1. Browse All Courses");
        System.out.println("2. View My Enrolled Courses");
        System.out.println("3. Enroll in a Course");
        System.out.println("4. View Course Details & Lessons");
        System.out.println("5. Mark Lesson as Completed");
        System.out.println("6. View My Progress");
        System.out.println("7. Logout");
        System.out.println("─────────────────────────────────────");
        System.out.print("Choose an option: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                browseCourses();
                break;
            case 2:
                viewEnrolledCourses(student);
                break;
            case 3:
                enrollInCourse(student);
                break;
            case 4:
                viewCourseDetails(student);
                break;
            case 5:
                markLessonCompleted(student);
                break;
            case 6:
                viewProgress(student);
                break;
            case 7:
                currentUser = null;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    
    private static void browseCourses() {
        List<Course> courses = studentManager.getAllCourses();
        
        System.out.println("\n┌──────────────────────────────────────┐");
        System.out.println("│        ALL AVAILABLE COURSES         │");
        System.out.println("└──────────────────────────────────────┘");
        
        if (courses.isEmpty()) {
            System.out.println("No courses available yet.");
            return;
        }
        
        for (Course course : courses) {
            System.out.println("\n Course ID: " + course.getCourseID());
            System.out.println("   Title: " + course.getCourseTitle());
            System.out.println("   Description: " + course.getCourseDescription());
            System.out.println("   Instructor ID: " + course.getInstructorID());
            System.out.println("   Total Lessons: " + course.getLessons().size());
            System.out.println("   Enrolled Students: " + course.getStudents().size());
        }
    }
    
    private static void viewEnrolledCourses(Student student) {
        List<Course> enrolled = studentManager.getEnrolledCourses(student);
        
        System.out.println("\n┌──────────────────────────────────────┐");
        System.out.println("│         MY ENROLLED COURSES          │");
        System.out.println("└──────────────────────────────────────┘");
        
        if (enrolled.isEmpty()) {
            System.out.println("You are not enrolled in any courses yet.");
            return;
        }
        
        for (Course course : enrolled) {
            List<String> completed = studentManager.getCompletedLessons(student, course);
            int totalLessons = course.getLessons().size();
            double progress = totalLessons > 0 ? (completed.size() * 100.0 / totalLessons) : 0;
            
            System.out.println("\n " + course.getCourseTitle() + " (" + course.getCourseID() + ")");
            System.out.println("   Progress: " + completed.size() + "/" + totalLessons + 
                             " lessons (" + String.format("%.1f", progress) + "%)");
        }
    }
    
    private static void enrollInCourse(Student student) {
        System.out.print("\nEnter Course ID to enroll: ");
        String courseId = scanner.nextLine();
        
        Course course = courseManager.getCourseByID(courseId);
        
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        
        boolean success = studentManager.enrollStudentInCourse(student, course);
        
        if (success) {
            System.out.println("Successfully enrolled in: " + course.getCourseTitle());
        } else {
            System.out.println("Already enrolled or enrollment failed.");
        }
    }
    
    private static void viewCourseDetails(Student student) {
        System.out.print("\nEnter Course ID: ");
        String courseId = scanner.nextLine();
        
        Course course = courseManager.getCourseByID(courseId);
        
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          COURSE DETAILS               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Title: " + course.getCourseTitle());
        System.out.println("ID: " + course.getCourseID());
        System.out.println("Description: " + course.getCourseDescription());
        System.out.println("\nLESSONS:");
        
        if (course.getLessons().isEmpty()) {
            System.out.println("No lessons available yet.");
            return;
        }
        
        for (int i = 0; i < course.getLessons().size(); i++) {
            Lesson lesson = course.getLessons().get(i);
            boolean completed = studentManager.isLessonCompleted(student, course, lesson.getLessonID());
            String status = completed ? "Completed" : "In Progress";
            
            System.out.println("\n" + (i + 1) + ". " + lesson.getLessonTitle() + " [" + status + "]");
            System.out.println("   ID: " + lesson.getLessonID());
            System.out.println("   Content: " + lesson.getLessonContent());
        }
    }
    
    private static void markLessonCompleted(Student student) {
        System.out.print("\nEnter Course ID: ");
        String courseId = scanner.nextLine();
        
        Course course = courseManager.getCourseByID(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        
        System.out.print("Enter Lesson ID to mark as completed: ");
        String lessonId = scanner.nextLine();
        
        Lesson lesson = course.getLesson(lessonId);
        if (lesson == null) {
            System.out.println("Lesson not found.");
            return;
        }
        
        studentManager.markLessonCompleted(student, course, lessonId);
        System.out.println("Lesson marked as completed: " + lesson.getLessonTitle());
    }
    
    private static void viewProgress(Student student) {
        List<Course> enrolled = studentManager.getEnrolledCourses(student);
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         MY LEARNING PROGRESS          ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        if (enrolled.isEmpty()) {
            System.out.println("No enrolled courses yet.");
            return;
        }
        
        for (Course course : enrolled) {
            List<String> completed = studentManager.getCompletedLessons(student, course);
            int totalLessons = course.getLessons().size();
            
            System.out.println("\n" + course.getCourseTitle());
            System.out.println("   Completed Lessons: " + completed.size() + "/" + totalLessons);
            
            if (!completed.isEmpty()) {
                System.out.println("   Completed: ");
                for (String lessonId : completed) {
                    Lesson lesson = course.getLesson(lessonId);
                    if (lesson != null) {
                        System.out.println("     " + lesson.getLessonTitle());
                    }
                }
            }
        }
    }
    
    private static void showInstructorMenu() {
        Instructor instructor = (Instructor) currentUser;
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        INSTRUCTOR DASHBOARD           ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Welcome, " + instructor.getUsername() + " (ID: " + instructor.getUserId() + ")");
        System.out.println("\n1. Create New Course");
        System.out.println("2. View My Courses");
        System.out.println("3. Edit Course");
        System.out.println("4. Add Lesson to Course");
        System.out.println("5. Edit Lesson");
        System.out.println("6. Delete Lesson");
        System.out.println("7. View Enrolled Students");
        System.out.println("8. View All Courses");
        System.out.println("9. Logout");
        System.out.println("─────────────────────────────────────");
        System.out.print("Choose an option: ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                createCourse(instructor);
                break;
            case 2:
                viewMyCourses(instructor);
                break;
            case 3:
                editCourse();
                break;
            case 4:
                addLesson();
                break;
            case 5:
                editLesson();
                break;
            case 6:
                deleteLesson();
                break;
            case 7:
                viewEnrolledStudents();
                break;
            case 8:
                browseCourses();
                break;
            case 9:
                currentUser = null;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    
    private static void createCourse(Instructor instructor) {
        System.out.println("\n┌──────────────────────────────────────┐");
        System.out.println("│         CREATE NEW COURSE            │");
        System.out.println("└──────────────────────────────────────┘");
        
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter Course Description: ");
        String description = scanner.nextLine();
        
        boolean success = instructorManager.createCourse(instructor, courseId, title, description);
        
        if (success) {
            System.out.println("Course created successfully!");
        } else {
            System.out.println("Failed to create course. Check validation errors above.");
        }
    }
    
    private static void viewMyCourses(Instructor instructor) {
        List<Course> myCourses = courseManager.getCoursesByInstructor(instructor.getUserId());
        
        System.out.println("\n┌──────────────────────────────────────┐");
        System.out.println("│           MY COURSES                 │");
        System.out.println("└──────────────────────────────────────┘");
        
        if (myCourses.isEmpty()) {
            System.out.println("You haven't created any courses yet.");
            return;
        }
        
        for (Course course : myCourses) {
            System.out.println("\n " + course.getCourseTitle() + " (" + course.getCourseID() + ")");
            System.out.println("   Description: " + course.getCourseDescription());
            System.out.println("   Lessons: " + course.getLessons().size());
            System.out.println("   Enrolled Students: " + course.getStudents().size());
        }
    }
    
    private static void editCourse() {
        
        System.out.print("Enter Course ID to edit: ");
        String courseId = scanner.nextLine();
        
        Course course = courseManager.getCourseByID(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        
        System.out.println("Current Title: " + course.getCourseTitle());
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        
        System.out.println("Current Description: " + course.getCourseDescription());
        System.out.print("Enter new description: ");
        String newDesc = scanner.nextLine();
        
        instructorManager.editCourse(courseId, newTitle, newDesc);
       
    }
    
    private static void addLesson() {
        
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        
        Course course = courseManager.getCourseByID(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        
        System.out.print("Enter Lesson ID: ");
        String lessonId = scanner.nextLine();
        
        System.out.print("Enter Lesson Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter Lesson Content: ");
        String content = scanner.nextLine();

       
        instructorManager.addLesson(courseId, lessonId, title, content);
           
    }
    
    private static void editLesson() {
        
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        
        Course course = courseManager.getCourseByID(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        
        System.out.print("Enter Lesson ID to edit: ");
        String lessonId = scanner.nextLine();
        
        Lesson lesson = course.getLesson(lessonId);
        if (lesson == null) {
            System.out.println("Lesson not found.");
            return;
        }
        
        System.out.println("Current Title: " + lesson.getLessonTitle());
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        
        System.out.println("Current Content: " + lesson.getLessonContent());
        System.out.print("Enter new content: ");
        String newContent = scanner.nextLine();
        
        
        instructorManager.editLesson(courseId, lessonId, newTitle, newContent);
           
    }
    
    private static void deleteLesson() {
        System.out.print("\nEnter Course ID: ");
        String courseId = scanner.nextLine();
        
        Course course = courseManager.getCourseByID(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        
        System.out.print("Enter Lesson ID to delete: ");
        String lessonId = scanner.nextLine();
        
        instructorManager.deleteLesson(courseId, lessonId);
        System.out.println(" Lesson deleted successfully!");
    }
    
    private static void viewEnrolledStudents() {
        System.out.print("\nEnter Course ID: ");
        String courseId = scanner.nextLine();
        
        Course course = courseManager.getCourseByID(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        
        ArrayList<Student> students = instructorManager.getEnrolledStudents(course);
        
        System.out.println("\n┌──────────────────────────────────────┐");
        System.out.println("│       ENROLLED STUDENTS              │");
        System.out.println("└──────────────────────────────────────┘");
        System.out.println("Course: " + course.getCourseTitle());
        
        if (students.isEmpty()) {
            System.out.println("No students enrolled yet.");
            return;
        }
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            List<String> completed = studentManager.getCompletedLessons(student, course);
            
            System.out.println("\n" + (i + 1) + ". " + student.getUsername());
            System.out.println("   ID: " + student.getUserId());
            System.out.println("   Email: " + student.getEmail());
            System.out.println("   Progress: " + completed.size() + "/" + course.getLessons().size() + " lessons");
        }
    }
    
    private static int getIntInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine());
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}