package dto;

public class QuestionListDTO {
    private int id;
    private String detail;
    private String suggestion;
    private String subjectName;
    private String lessonName;
    private int status;
    private String media;

    public QuestionListDTO(int id, String detail, String suggestion, String subjectName, String lessonName, int status) {
        this.id = id;
        this.detail = detail;
        this.suggestion = suggestion;
        this.subjectName = subjectName;
        this.lessonName = lessonName;
        this.status = status;
    }

    public QuestionListDTO(int id, String detail, String suggestion, int status, String media) {
        this.id = id;
        this.detail = detail;
        this.suggestion = suggestion;
        this.status = status;
        this.media = media;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public int getStatus() {
        return status;
    }

    public String getMedia() {
        return media;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMedia(String media) {
        this.media = media;
    }
}
