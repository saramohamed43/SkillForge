/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package skillforge;
import java.awt.CardLayout;
import java.util.List;
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
private javax.swing.JFrame parentFrame;


    /**
     * Creates new form StudentDashBoardFrame
     */
   public StudentDashBoardFrame(StudentManager studentManager, Student student,javax.swing.JFrame parentFrame) {
    this.studentManager = studentManager;
    this.currentStudent = student;
this.parentFrame=parentFrame;
    initComponents();
    setupTableColumns();
    AvailableCoursesTable.setFillsViewportHeight(true);
    EnrolledCoursesTable.setFillsViewportHeight(true);
    jTable3.setFillsViewportHeight(true);
     AvailableCoursesTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    EnrolledCoursesTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

    CardLayout cl = (CardLayout) MainStudentPanel.getLayout();
    cl.show(MainStudentPanel, "card5");

    jLabel1.setText("Welcome, " + student.getUsername() + "!");
    jLabel3.setText("Course Lessons");
    jButton5.setText("View Lessons");
    jButton6.setText("Mark as Complete");

    populateAvailableCoursesTable();
    populateEnrolledCoursesTable();




EnrolledCoursesTable.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int selectedRow = EnrolledCoursesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String courseId = EnrolledCoursesTable.getValueAt(selectedRow, 0).toString();
            Course selectedCourse = studentManager.getAllCourses().stream()
                .filter(c -> c.getCourseID().equals(courseId))
                .findFirst()
                .orElse(null);
            
           
        }
    }
});
AvailableCoursesTable.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int selectedRow = AvailableCoursesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String courseId = AvailableCoursesTable.getValueAt(selectedRow, 0).toString();
            Course selectedCourse = studentManager.getAllCourses().stream()
                .filter(c -> c.getCourseID().equals(courseId))
                .findFirst()
                .orElse(null);

           
        }
    }
});

   }
   
   private void populateAvailableCoursesTable() {
    DefaultTableModel model = (DefaultTableModel) AvailableCoursesTable.getModel();
    model.setRowCount(0);

    for (Course course : studentManager.getAllCourses()) {
        model.addRow(new Object[]{
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

    for (Course c : studentManager.getEnrolledCourses(currentStudent)) {

        double percentage = studentManager.getCoursePercentage(currentStudent, c);

        model.addRow(new Object[]{
            c.getCourseID(),
            c.getCourseTitle(),
             c.getCourseDescription(),
            c.getInstructorID(),
            percentage + "%"
        });
    }
}
private void populateLessonsTableWithStatus(Course course) {
    DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
    model.setRowCount(0);

    for (Lesson lesson : course.getLessons()) {

        boolean completed = studentManager.isLessonCompleted(currentStudent, course, lesson.getLessonID());
        String status = completed ? "✔ Completed" : "❌ Not Completed";

        model.addRow(new Object[]{
            lesson.getLessonID(),
            lesson.getLessonTitle(),
            lesson.getLessonContent(),
            status
        });
    }
}


private void setupTableColumns() {

   
    DefaultTableModel availableModel = (DefaultTableModel) AvailableCoursesTable.getModel();
    availableModel.setColumnIdentifiers(new String[]{
        "Course ID", "Title", "Description", "Instructor"
    });

 
    DefaultTableModel enrolledModel = (DefaultTableModel) EnrolledCoursesTable.getModel();
    enrolledModel.setColumnIdentifiers(new String[]{
        "Course ID", "Title","Description", "Instructor", "Completion %"
    });

  
    DefaultTableModel lessonModel = (DefaultTableModel) jTable3.getModel();
    lessonModel.setColumnIdentifiers(new String[]{
        "Lesson ID", "Title", "Content", "Status"
    });
}





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        ButtonsPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
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

        jButton3.setText("back to home");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                .addGroup(ButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(22, 22, 22))
        );
        ButtonsPanelLayout.setVerticalGroup(
            ButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonsPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(144, 144, 144)
                .addComponent(jButton3)
                .addGap(67, 67, 67))
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
        ClickEnrollBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClickEnrollBtnActionPerformed(evt);
            }
        });
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
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, java.awt.BorderLayout.PAGE_END);

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
    }// </editor-fold>                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         

    CardLayout cl = (CardLayout) MainStudentPanel.getLayout();
    cl.show(MainStudentPanel, "card3");       
    }                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    
    CardLayout cl = (CardLayout) MainStudentPanel.getLayout();
    cl.show(MainStudentPanel, "card2");  
    }                                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       int selectedRow = EnrolledCoursesTable.getSelectedRow();
    if (selectedRow >= 0) { 
        String courseId = EnrolledCoursesTable.getValueAt(selectedRow, 0).toString();
        Course selectedCourse = studentManager.getAllCourses().stream()
            .filter(c -> c.getCourseID().equals(courseId))
            .findFirst()
            .orElse(null);

        if (selectedCourse != null) {
            currentViewedCourse = selectedCourse;
            populateLessonsTableWithStatus(selectedCourse);

            CardLayout cl2 = (CardLayout) MainStudentPanel.getLayout();
            cl2.show(MainStudentPanel, "card4");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Select a course first to view lessons.");
    }
    }                                        

    private void ClickEnrollBtnActionPerformed(java.awt.event.ActionEvent evt) {                                               
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
    }                                              

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
int selectedRow = jTable3.getSelectedRow();

    if (selectedRow >= 0 && currentViewedCourse != null) {

        String lessonId = jTable3.getValueAt(selectedRow, 0).toString();
        String currentStatus = jTable3.getValueAt(selectedRow, 3).toString(); // FIXED

        if (currentStatus.equals("❌ Not Completed")) {

            studentManager.markLessonCompleted(currentStudent, currentViewedCourse, lessonId);

            populateLessonsTableWithStatus(currentViewedCourse);
            populateEnrolledCoursesTable();  

            JOptionPane.showMessageDialog(this, "Lesson marked as completed!");

        } else {
            JOptionPane.showMessageDialog(this, "This lesson is already completed.");
        }

    } else {
        JOptionPane.showMessageDialog(this, "Select a lesson to mark as complete.");
    }      
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
        "Are you sure you want to log out?",
        "Confirm Logout",
        javax.swing.JOptionPane.YES_NO_OPTION);

    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
        this.dispose(); 
        parentFrame.setVisible(true);
        if (parentFrame instanceof LoginFrame) {
            ((LoginFrame) parentFrame).showPanel("mainCard");
        }
    }
    }                                        

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify                     
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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    // End of variables declaration                   
}