package skillforge;
import java.util.ArrayList;

public class Lesson {
    private String lessonID;
    private String lessonTitle;
    private String lessonContent;
    private ArrayList<String> optionalRresources;

    //instructor with resources
    public Lesson(String lessonID, String lessonTitle, String lessonContent) {
        this.lessonID = lessonID;
        this.lessonTitle = lessonTitle;
        this.lessonContent = lessonContent;
        this.optionalRresources = new ArrayList<>();
    }
    
    public ArrayList<String> getOptionalRresources() {
        return optionalRresources;
    }
    
    public String getLessonID() {
        return lessonID;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public String getLessonContent() {
        return lessonContent;
    }


    public void setOptionalRresources(ArrayList<String> optionalRresources) {
        this.optionalRresources = optionalRresources;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public void setLessonContent(String lessonContent) {
        this.lessonContent = lessonContent;
    }
    
    public void saveProgress(Student student){
        String courseID = 
    }
}
