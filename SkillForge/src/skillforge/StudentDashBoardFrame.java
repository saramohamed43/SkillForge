/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package skillforge;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;


/**
 *
 * @author zmezm
 */
public class StudentDashBoardFrame extends javax.swing.JFrame {
 private StudentManager studentManager;  
    private Student currentStudent;   
private Course currentViewedCourse; 



    /**
     * Creates new form StudentDashBoardFrame
     */
   public StudentDashBoardFrame(StudentManager studentManager, Student student) {
    this.studentManager = studentManager;
    this.currentStudent = student;

    initComponents();
    
    // Setup table columns
    setupTableColumns();

    CardLayout cl = (CardLayout) MainStudentPanel.getLayout();
    cl.show(MainStudentPanel, "card5");

    jLabel1.setText("Welcome, " + student.getUsername() + "!");
    jLabel3.setText("Course Lessons");
    jButton5.setText("View Lessons");
    jButton6.setText("Mark as Complete");

    // Populate tables
    populateAvailableCoursesTable();
    populateEnrolledCoursesTable();
    
    // Enroll button listener
    ClickEnrollBtn.addActionListener(evt -> {
        int selectedRow = AvailableCoursesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String courseId = AvailableCoursesTable.getValueAt(selectedRow, 0).toString();
            Course selectedCourse = studentManager.getAllCourses().stream()
                .filter(c -> c.getCourseID().equals(courseId))
                .findFirst()
                .orElse(null);

            if (selectedCourse != null) {
                boolean enrolled = studentManager.enrollStudentInCourse(currentStudent, selectedCourse);
                if (enrolled) {
                    populateEnrolledCoursesTable();
                    populateAvailableCoursesTable();
                    JOptionPane.showMessageDialog(this, "Successfully enrolled in course!");
                } else {
                    JOptionPane.showMessageDialog(this, "Already enrolled in this course.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a course to enroll.");
        }
    });
    
    // View lessons button listener
    // View lessons button listener
jButton5.addActionListener(evt -> {
    int selectedRow = EnrolledCoursesTable.getSelectedRow();
    if (selectedRow >= 0) {
        String courseId = EnrolledCoursesTable.getValueAt(selectedRow, 0).toString();
        Course selectedCourse = studentManager.getAllCourses().stream()
            .filter(c -> c.getCourseID().equals(courseId))
            .findFirst()
            .orElse(null);

        if (selectedCourse != null) {
            currentViewedCourse = selectedCourse;  // Store current course
            populateLessonsTableWithStatus(selectedCourse);  // NEW METHOD
            
            CardLayout cl2 = (CardLayout) MainStudentPanel.getLayout();
            cl2.show(MainStudentPanel, "card4");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Select a course first to view lessons.");
    }
});
jButton6.addActionListener(evt -> {
    int selectedRow = jTable3.getSelectedRow();
    if (selectedRow >= 0 && currentViewedCourse != null) {
        String lessonId = jTable3.getValueAt(selectedRow, 0).toString();
        String currentStatus = jTable3.getValueAt(selectedRow, 2).toString();
        
        if (currentStatus.equals("❌ Not Completed")) {
            // Use backend method to mark as completed
            studentManager.markLessonCompleted(currentStudent, currentViewedCourse, lessonId);
            
            // Refresh the table
            populateLessonsTableWithStatus(currentViewedCourse);
            JOptionPane.showMessageDialog(this, "Lesson marked as completed!");
        } else {
            JOptionPane.showMessageDialog(this, "This lesson is already completed.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Select a lesson to mark as complete.");
    }
});
   }
   
    private void populateAvailableCoursesTable() {
    DefaultTableModel model = (DefaultTableModel) AvailableCoursesTable.getModel();
    model.setRowCount(0); // clear table first

    for (Course course : studentManager.getAllCourses()) {
        model.addRow(new Object[] {
            course.getCourseID(),
            course.getCourseTitle(),
            course.getCourseDescription(),
            course.getInstructorID()
        });
    }
}
private void populateEnrolledCoursesTable() {
    DefaultTableModel model = (DefaultTableModel) EnrolledCoursesTable.getModel();
    model.setRowCount(0);

    for (Course course : studentManager.getEnrolledCourses(currentStudent)) {
        model.addRow(new Object[] {
            course.getCourseID(),
            course.getCourseTitle(),
            course.getCourseDescription(),
            course.getInstructorID()
        });
    }
}
private void setupTableColumns() {
    // Setup Available Courses Table
    DefaultTableModel availableModel = (DefaultTableModel) AvailableCoursesTable.getModel();
    availableModel.setColumnIdentifiers(new String[]{"Course ID", "Title", "Description", "Instructor ID"});
    
    // Setup Enrolled Courses Table
    DefaultTableModel enrolledModel = (DefaultTableModel) EnrolledCoursesTable.getModel();
    enrolledModel.setColumnIdentifiers(new String[]{"Course ID", "Title", "Description", "Instructor ID"});
    
    // Setup Lessons Table - ADD STATUS COLUMN
    DefaultTableModel lessonModel = (DefaultTableModel) jTable3.getModel();
    lessonModel.setColumnIdentifiers(new String[]{"Lesson ID", "Lesson Title", "Status"});
}
private void populateLessonsTableWithStatus(Course course) {
    DefaultTableModel lessonModel = (DefaultTableModel) jTable3.getModel();
    lessonModel.setRowCount(0);
    
    for (Lesson lesson : course.getLessons()) {
        // Use backend method to check if lesson is completed
        boolean isCompleted = studentManager.isLessonCompleted(currentStudent, course, lesson.getLessonID());
        String status = isCompleted ? "✅ Completed" : "❌ Not Completed";
        
        lessonModel.addRow(new Object[]{
            lesson.getLessonID(), 
            lesson.getLessonTitle(),
            status
        });
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ButtonsPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        MainStudentPanel = new javax.swing.JPanel();
        BrowseCoursesPanel = new javax.swing.JPanel();
        AvailableCoursesLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AvailableCoursesTable = new javax.swing.JTable();
        ClickEnrollBtn = new javax.swing.JButton();
        EnrolledCoursesPanel = new javax.swing.JPanel();
        EnrolledCoursesLbl = new javax.swing.JLabel();
        EnrolledCoursesScroll = new javax.swing.JScrollPane();
        EnrolledCoursesTable = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        WelcomeStudentPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ButtonsPanel.setName("StudentsButtonPanel"); // NOI18N

        jButton1.setText("available courses");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Enrolled Courses");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ButtonsPanelLayout = new javax.swing.GroupLayout(ButtonsPanel);
        ButtonsPanel.setLayout(ButtonsPanelLayout);
        ButtonsPanelLayout.setHorizontalGroup(
            ButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(22, 22, 22))
        );
        ButtonsPanelLayout.setVerticalGroup(
            ButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonsPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(234, 234, 234))
        );

        MainStudentPanel.setName("MainStudentPanel"); // NOI18N
        MainStudentPanel.setLayout(new java.awt.CardLayout());

        BrowseCoursesPanel.setLayout(new java.awt.BorderLayout());

        AvailableCoursesLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AvailableCoursesLbl.setText("Available courses");
        BrowseCoursesPanel.add(AvailableCoursesLbl, java.awt.BorderLayout.PAGE_START);

        AvailableCoursesTable.setAutoCreateRowSorter(true);
        AvailableCoursesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        AvailableCoursesTable.setShowHorizontalLines(true);
        AvailableCoursesTable.setShowVerticalLines(true);
        jScrollPane1.setViewportView(AvailableCoursesTable);

        BrowseCoursesPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        ClickEnrollBtn.setText("Enroll in Course");
        BrowseCoursesPanel.add(ClickEnrollBtn, java.awt.BorderLayout.PAGE_END);

        MainStudentPanel.add(BrowseCoursesPanel, "card2");

        EnrolledCoursesPanel.setLayout(new java.awt.BorderLayout());

        EnrolledCoursesLbl.setText("Enrolled Courses");
        EnrolledCoursesPanel.add(EnrolledCoursesLbl, java.awt.BorderLayout.PAGE_START);

        EnrolledCoursesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        EnrolledCoursesScroll.setViewportView(EnrolledCoursesTable);

        EnrolledCoursesPanel.add(EnrolledCoursesScroll, java.awt.BorderLayout.CENTER);

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        EnrolledCoursesPanel.add(jButton5, java.awt.BorderLayout.PAGE_END);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        EnrolledCoursesPanel.add(jScrollPane2, java.awt.BorderLayout.LINE_START);

        MainStudentPanel.add(EnrolledCoursesPanel, "card3");

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("jLabel3");
        jPanel5.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel5.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jButton6.setText("jButton6");
        jPanel5.add(jButton6, java.awt.BorderLayout.PAGE_END);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jPanel5.add(jScrollPane4, java.awt.BorderLayout.LINE_START);

        MainStudentPanel.add(jPanel5, "card4");

        WelcomeStudentPanel.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("welcome ");
        WelcomeStudentPanel.add(jLabel1, java.awt.BorderLayout.CENTER);

        MainStudentPanel.add(WelcomeStudentPanel, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MainStudentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ButtonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MainStudentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    CardLayout cl = (CardLayout) MainStudentPanel.getLayout();

    cl.show(MainStudentPanel, "card3");       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
// Get the CardLayout from MainStudentPanel
    CardLayout cl = (CardLayout) MainStudentPanel.getLayout();

    // Switch to the BrowseCoursesPanel (card2)
    cl.show(MainStudentPanel, "card2");  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CardLayout cl = (CardLayout) MainStudentPanel.getLayout();
    cl.show(MainStudentPanel, "card4");
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> {
        // Initialize backend
        UserDatabaseManager userDb = new UserDatabaseManager();
        CourseManagment courseDb = new CourseManagment("courses.json", Course.class);
        StudentManager studentManager = new StudentManager(userDb, courseDb);

        // Create or get test student
        Student testStudent = (Student) userDb.getUserByEmail("test@student.com");
        if (testStudent == null) {
            // Create new test student
            UserAuth.signup("TestStudent", "test@student.com", "password123", "Student");
            testStudent = (Student) userDb.getUserByEmail("test@student.com");
        }

        // Create test course with lessons if needed
        Course testCourse = courseDb.getItemById("C001");
        if (testCourse == null) {
            courseDb.createCourse("C001", "Java Programming", "Learn Java basics", "I001");
            testCourse = courseDb.getItemById("C001");
            
            // Add some test lessons
            Lesson lesson1 = new Lesson("L001", "Introduction to Java", "Java basics content");
            Lesson lesson2 = new Lesson("L002", "Variables and Data Types", "Variables content");
            Lesson lesson3 = new Lesson("L003", "Control Structures", "If/else and loops");
            
            testCourse.addLesson(lesson1);
            testCourse.addLesson(lesson2);
            testCourse.addLesson(lesson3);
            courseDb.update("C001", testCourse);
        }

        // Launch the dashboard
        new StudentDashBoardFrame(studentManager, testStudent).setVisible(true);
        
        System.out.println("Dashboard launched successfully!");
        System.out.println("Student: " + testStudent.getUsername());
        System.out.println("Enrolled courses: " + testStudent.getEnrolledCourses().size());
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AvailableCoursesLbl;
    private javax.swing.JTable AvailableCoursesTable;
    private javax.swing.JPanel BrowseCoursesPanel;
    private javax.swing.JPanel ButtonsPanel;
    private javax.swing.JButton ClickEnrollBtn;
    private javax.swing.JLabel EnrolledCoursesLbl;
    private javax.swing.JPanel EnrolledCoursesPanel;
    private javax.swing.JScrollPane EnrolledCoursesScroll;
    private javax.swing.JTable EnrolledCoursesTable;
    private javax.swing.JPanel MainStudentPanel;
    private javax.swing.JPanel WelcomeStudentPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
