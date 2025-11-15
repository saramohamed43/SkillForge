package skillforge;

import java.util.ArrayList;

public class Lesson {

    private String lessonID;
    private String lessonTitle;
    private String lessonContent;
    private ArrayList<String> optionalResources;

    public Lesson(String lessonID, String lessonTitle, String lessonContent) {
        this.lessonID = lessonID;
        this.lessonTitle = lessonTitle;
        this.lessonContent = lessonContent;
        this.optionalResources = new ArrayList<>();
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

    public ArrayList<String> getOptionalResources() {
        return optionalResources;
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

    public void setOptionalResources(ArrayList<String> optionalResources) {
        this.optionalResources = optionalResources;
    }

    @Override
    public String toString() {
        return lessonTitle;
    }
}
